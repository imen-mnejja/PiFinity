package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.SubBankAccount;
import com.example.pifinity.repository.SubBankAccountRepository;
import com.example.pifinity.serviceInterface.ISubBankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubBankAccountServiceImpl implements ISubBankAccountService {

    SubBankAccountRepository subBankAccountRepository;


    @Override
    public List<SubBankAccount> retrieveAllSubBankAccount() {
        return subBankAccountRepository.findAll();
    }

    @Override
    public SubBankAccount addSubBankAccount(SubBankAccount subBankAccount) {
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
