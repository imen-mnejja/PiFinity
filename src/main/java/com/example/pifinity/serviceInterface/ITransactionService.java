package com.example.pifinity.serviceInterface;


import com.example.pifinity.entity.Transaction;

import java.util.List;

public interface ITransactionService {
    List<Transaction> retrieveAllTransaction();

   //Transaction addTransaction(Transaction transaction);

    void deleteTransaction(int idtransaction);
    Transaction retrieveTransaction(int idtransaction);

    void addTransactionAndUpdateNbTransactions(Transaction transaction);




}
