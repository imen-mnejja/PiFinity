package com.example.pifinity.controller;



import com.example.pifinity.entity.Transaction;
import com.example.pifinity.serviceInterface.ITransactionService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {


    //@Autowired
    private ITransactionService transactionService;

    @GetMapping("/alltransaction")
    public List<Transaction> findAllTransaction() {

        return transactionService.retrieveAllTransaction();
    }

    @GetMapping("/{idtransaction}")
    public Transaction findByIdTransaction(@PathVariable("idtransaction") int idtransaction) {
        return transactionService.retrieveTransaction(idtransaction);
    }

    @DeleteMapping("/delete/{idtransaction}")
    public void deleteBankAccount(@PathVariable("idtransaction") int idtransaction) {
        transactionService.deleteTransaction(idtransaction);
    }

    /*   @PostMapping
       public Transaction addTransaction(@RequestBody Transaction transaction) {
           return transactionService.addTransaction(transaction);
       }
   */
    @PostMapping("/add")
    public ResponseEntity<String> addTransactionAndUpdateNbTransactions(@RequestBody Transaction transaction) {
        try {
            transactionService.addTransactionAndUpdateNbTransactions(transaction);
            return ResponseEntity.ok("Transaction ajoutée avec succès et nombre de transactions du partenaire mis à jour.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de l'ajout de la transaction et de la mise à jour du nombre de transactions du partenaire.");
        }
    }

}
