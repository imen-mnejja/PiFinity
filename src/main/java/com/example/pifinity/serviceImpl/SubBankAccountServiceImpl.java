package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.BankAccount;
import com.example.pifinity.entity.SubBankAccount;
import com.example.pifinity.entity.Transaction;
import com.example.pifinity.repository.SubBankAccountRepository;
import com.example.pifinity.serviceInterface.IBankAccountService;
import com.example.pifinity.serviceInterface.ISubBankAccountService;
import com.example.pifinity.serviceInterface.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class SubBankAccountServiceImpl implements ISubBankAccountService {

    SubBankAccountRepository subBankAccountRepository;
    IBankAccountService bankAccountService;
   // ITransactionService transactionService;


/*
    @Override
    public double calcule(int id){

        List<Transaction> transactions = transactionService.retrieveAllTransactionbySubAccount(id);
        SubBankAccount subBankAccount= retrieveSubBankAccount(id);
        float goneamount=0;
        float recieveamount=0;
        float currentBalance = subBankAccount.getSolde();
        float firstamount;
        for (Transaction transaction : transactions) {
            if (transaction.getRIB_S() == subBankAccount.getIdSubBankAccount()) {
                goneamount += transaction.getAmount();
            }
            if (transaction.getRIB_D() == subBankAccount.getIdSubBankAccount()) {
                recieveamount += transaction.getAmount();
            }
        }

        double totalInterest = 0;
        int totalDays = 0;

        firstamount= currentBalance-goneamount+recieveamount;
        for (Transaction transaction : transactions) {
            LocalDateTime transactionDate = transaction.getDateTransaction();
            LocalDate startDate = LocalDate.of(transactionDate.getYear(), 1, 1);
            int days = (int) ChronoUnit.DAYS.between(startDate, transactionDate.plusDays(1)); // Adding 1 to include the transaction date
            double dailyInterest = (firstamount * 0.03 * days) / 360; // Assuming 360-day year
            totalInterest += dailyInterest;
            currentBalance += transaction.getAmount(); // Update the current balance
            totalDays += days; // Update total days
        }
        System.out.println("Total interest: " + totalInterest);
        System.out.println("Total days: " + totalDays);

        return totalInterest;


    }

*/
    @Override
    public List<SubBankAccount> retrieveAllSubBankAccount() {
        return subBankAccountRepository.findAll();
    }

    @Override
    public SubBankAccount addSubBankAccount(int rib, SubBankAccount subBankAccount) {

        BankAccount bankAccount ;
        bankAccount=bankAccountService.retrieveBankAccount(rib);
        if (bankAccount != null) {

            subBankAccount.setBankAccount(bankAccount);
            subBankAccount.setDateCreation(LocalDate.now());



        } else {
            throw new RuntimeException("BankAccount with RIB " + rib + " not found.");
        }

        return subBankAccountRepository.save(subBankAccount);
    }





    @Override
    public SubBankAccount updateSubBankAccount(int id, SubBankAccount subBankAccount) {

        SubBankAccount existingSubBankAccount =retrieveSubBankAccount(id);
        existingSubBankAccount.setSolde(subBankAccount.getSolde());
        existingSubBankAccount.setGoals(subBankAccount.getGoals());

        return subBankAccountRepository.save(existingSubBankAccount);



    }

    @Override
    public SubBankAccount retrieveSubBankAccount(int id) {
        return subBankAccountRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSubBankAccount(int id) {
        subBankAccountRepository.deleteById(id);
    }


    public List<SubBankAccount> retrieveAllSubBankAccountByOrder() {
        return subBankAccountRepository.getAllbyOrder();
    }





}
