package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.BankAccount;
import com.example.pifinity.entity.Partner;
import com.example.pifinity.entity.Transaction;

import com.example.pifinity.repository.PartnerRepo;
import com.example.pifinity.repository.TransactionRepository;
import com.example.pifinity.serviceInterface.ITransactionService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {


    //IVirtualBankCardService virtualBankCardService;
    TransactionRepository transactionRepository;
EmailService emailService;
    private PartnerRepo partnerRepo;
    @Override
    public List<Transaction> retrieveAllTransaction() {
        return transactionRepository.findAll();
    }

   /* @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }*/


    public void addTransactionAndUpdateNbTransactions(Transaction transaction) {
        // Ajout de la transaction à la base de données
        transactionRepository.save(transaction);

        // Récupération du partenaire associé à la transaction
        int ribDestination = transaction.getRIB_D();
        Partner partner = partnerRepo.findByBankAccount_RIB(ribDestination);

        // Incrémentation du nombre de transactions du partenaire si trouvé
        if (partner != null) {
            int currentNbTransactions = partner.getNbtransaction() != null ? partner.getNbtransaction() : 0;
            int newNbTransactions = currentNbTransactions + 1;
            partner.setNbtransaction(newNbTransactions);
            // Mise à jour du partenaire dans la base de données
            partnerRepo.save(partner);


           if (newNbTransactions >= 5) {
                // Envoyer un e-mail pour informer que le seuil de transactions est atteint
                String subject = "Seuil de transactions atteint";
                String text = "Le partenaire avec l'ID " + partner.getPartnerid() + " a atteint le seuil de 5 transactions.";
                emailService.sendEmail("manar.boukhris@esprit.tn", subject, text);
           }

        }
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
