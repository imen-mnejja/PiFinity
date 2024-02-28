package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.BankAccount;
import com.example.pifinity.entity.Transaction;
import com.example.pifinity.entity.VirtualBankCard;
import com.example.pifinity.repository.TransactionRepository;
import com.example.pifinity.serviceInterface.IBankAccountService;
import com.example.pifinity.serviceInterface.ITransactionService;
import com.example.pifinity.serviceInterface.IVirtualBankCardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
@AllArgsConstructor

public class TransactionServiceImpl implements ITransactionService {


    IVirtualBankCardService virtualBankCardService;
    IBankAccountService bankAccountService;
    TransactionRepository transactionRepository;
    SmsServiceImpl s;
    public static int a;
    public static int aa;
    public static float bb;
    @Override
    public List<Transaction> retrieveAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> retrieveAllTransactionbyBankAccount(int id) {

        BankAccount ba=bankAccountService.retrieveBankAccount(id);
        return transactionRepository.findAllBy(ba.getRIB());
    }

    @Override
    public int calculatetransaction(int rib_s, int rib_d) {
        return transactionRepository.calculateTransaction(rib_s,rib_d);
    }


    @Override
    public Transaction addt(int numcard,int password ,int cvv,Transaction transaction) throws MessagingException {
    VirtualBankCard virtualBankCard = virtualBankCardService.retrieveVirtualBankCard(numcard);
    if (virtualBankCard == null) {
        throw new RuntimeException("Virtual bank card with numcard " + numcard + " not found.");
    }

    if (virtualBankCard.getCvv() != cvv || virtualBankCard.getPassword() != password) {
        throw new RuntimeException("Wrong card details provided.");
    }

    float currentBalance = virtualBankCard.getBankAccount().getSolde();
    if (currentBalance < transaction.getAmount()) {
        throw new RuntimeException("Insufficient funds.");
    }


    transaction.setVirtualBankCard(virtualBankCard);
    transaction.setRIB_S(virtualBankCard.getBankAccount().getRIB());
    transaction.setDateTransaction(LocalDate.now());


    float weeklyLimit = 0;
    switch (virtualBankCard.getTypecard()) {
        case silver:
            weeklyLimit = 700;
            break;
        case gold:
            weeklyLimit = 1700;
            break;
        case premieum:
            // No limit for premium cards
            break;
    }

    // Check if the transaction amount exceeds the weekly limit
    float weeklyTransactionAmount = transactionRepository.calculateWeeklyTransactionAmount(virtualBankCard.getNumCard(), LocalDate.now(),LocalDate.now());
    if (weeklyLimit > 0 && weeklyTransactionAmount + transaction.getAmount() > weeklyLimit) {
        throw new RuntimeException("Transaction amount exceeds the weekly limit for this card type.");
    }

        a=confirmTransaction();

        return transaction;
}



    @Override
    public  int splittransaction(int code, int amount) throws MessagingException {


        List<String> recipients = Arrays.asList("ncib.fedi@esprit.tn", "manar.boukhris@esprit.tn", "imen.mnejja@esprit.tn","ncib.yasmine@esprit.tn");



       float b=(float)amount/recipients.size();
        String subject="your code to split transaction";
        for (String recipient : recipients) {
            String body = "Your code to confirm the split transaction: " + code + ", your part is " + b + " dinar";
            sendEmail(recipient, subject, body);

        }
        aa=code;
        bb=b;
        return  aa;

    }

    @Override
    public Transaction confirmsplittransaction(int a ,Transaction t,int numcard){

        VirtualBankCard virtualBankCard = virtualBankCardService.retrieveVirtualBankCard(numcard);
        t.setAmount(bb);
      if(aa==a) {
          float currentBalance = virtualBankCard.getBankAccount().getSolde();
          t.setDateTransaction(LocalDate.now());
          t.setVirtualBankCard(virtualBankCard);
          virtualBankCard.getBankAccount().setSolde(currentBalance - t.getAmount());
      }
        return  transactionRepository.save(t);
    }




    @Override
    public Transaction addTransaction(int numcard, int password, int confirmpassword,int cvv, Transaction transaction) throws MessagingException {


        VirtualBankCard virtualBankCard = virtualBankCardService.retrieveVirtualBankCard(numcard);
        float currentBalance = virtualBankCard.getBankAccount().getSolde();
        int confirmedPassword = a;
        if (confirmpassword != a) {
            throw new RuntimeException("Passwords do not match. Expected: " + confirmedPassword + ", Actual: " + confirmpassword);
        }


        virtualBankCard.getBankAccount().setSolde(currentBalance - transaction.getAmount());
        return transactionRepository.save(addt(numcard,password,cvv,transaction));
    }





    @Override
    public int confirmTransaction() throws MessagingException {
        int a = (int) (Math.random() * 1000);
       /* String smsNumber = "+21623519100"; // Replace with the actual phone number
        String smsMessage = "please confirm your transaction ";

        String status = s.sendSms(smsNumber, smsMessage, a);*/

        String to="ncib.fedi@esprit.tn";
        String subject="confirm transaction";
        String body=  "Your code to confirm transaction: " + a;
        sendEmail( to,  subject,  body);
        return a;
    }






    private void sendEmail(String to, String subject, String body) throws MessagingException {
        // Send an email
        // ...
        String from = "techwork414@gmail.com";
        String password = "pacrvzlvscatwwkb";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        //   message.setText(body);
        message.setContent(body, "text/html");

        Transport.send(message);
    }




    @Override
    public void deleteTransaction(int idtransaction) {
        transactionRepository.deleteById(idtransaction);
    }


    @Override
    public Transaction retrieveTransaction(int idtransaction) {
        return transactionRepository.findById(idtransaction).orElse(null);
    }


}
