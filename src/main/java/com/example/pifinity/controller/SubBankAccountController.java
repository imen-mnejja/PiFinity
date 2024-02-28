package com.example.pifinity.controller;



import com.example.pifinity.entity.SubBankAccount;

import com.example.pifinity.serviceInterface.ISubBankAccountService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subbankaccount")
@AllArgsConstructor
public class SubBankAccountController {


    private ISubBankAccountService subBankAccountService;

    @GetMapping("/allsubbankaccount")
    public List<SubBankAccount> findAllSubBankAccount(){

        return subBankAccountService.retrieveAllSubBankAccount();}

    @GetMapping("/{subbankaccountid}")
    public SubBankAccount findByIdSubBankAccount(@PathVariable("bankaccountid") int id){
        return subBankAccountService.retrieveSubBankAccount(id);}


    @PostMapping("/addsubbankaccount")
    public SubBankAccount addSubBankAccount(@RequestBody SubBankAccount subbankAccount) {
        return subBankAccountService.addSubBankAccount(subbankAccount);
    }


    @PutMapping("/update/{subbankaccountid}")
    public SubBankAccount updateSubBankAccount(@PathVariable int subbankaccountid , @RequestBody SubBankAccount subbankAccount) {
        return subBankAccountService.updateSubBankAccount(subbankaccountid , subbankAccount);
    }

    @DeleteMapping("/delete/{subbankaccountid}")
    public void deleteBankAccount(@PathVariable("subbankaccountid") int subbankaccountid) {
        subBankAccountService.deleteSubBankAccount(subbankaccountid);
    }


    @GetMapping("/allsubbankaccountbyorder")
    public List<SubBankAccount> findAllSubBankAccountByOrder(){

        return subBankAccountService.retrieveAllSubBankAccountByOrder();}



}
