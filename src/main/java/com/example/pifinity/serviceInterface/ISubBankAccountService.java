package com.example.pifinity.serviceInterface;


import com.example.pifinity.entity.SubBankAccount;

import java.util.List;

public interface ISubBankAccountService {
    List<SubBankAccount> retrieveAllSubBankAccount();

    SubBankAccount addSubBankAccount(SubBankAccount subBankAccount);

    SubBankAccount updateSubBankAccount(int id,SubBankAccount subBankAccount);

    SubBankAccount retrieveSubBankAccount(int id);

    void deleteSubBankAccount(int id);

    List<SubBankAccount> retrieveAllSubBankAccountByOrder();



}
