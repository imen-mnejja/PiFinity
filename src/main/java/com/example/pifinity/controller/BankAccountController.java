package com.example.pifinity.controller;


import com.example.pifinity.entity.BankAccount;
import com.example.pifinity.entity.SubBankAccount;
import com.example.pifinity.serviceInterface.IBankAccountService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankaccount")
@AllArgsConstructor
public class BankAccountController {



    private IBankAccountService bankAccountService;
    @GetMapping("/allbankaccount")
    public List<BankAccount> findAllBankAccount(){

        return bankAccountService.retrieveAllBankAccount();}

    @GetMapping("/{bankaccountrib}")
    public BankAccount findByRibBankAccount(@PathVariable("bankaccountrib") int rib){
        return bankAccountService.retrieveBankAccount(rib);}

    @PostMapping("/addbankaccount")
    public BankAccount addBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.addBankAccount(bankAccount);
    }

    @PutMapping("/update/{bankaccountrib}")
    public BankAccount updateBankAccount(@PathVariable int bankaccountrib , @RequestBody BankAccount bankAccount) {
        return bankAccountService.updateBankAccount(bankaccountrib , bankAccount);
    }

    @DeleteMapping("/delete/{bankaccountrib}")
    public void deleteBankAccount(@PathVariable("bankaccountrib") int bankaccountrib) {
        bankAccountService.deleteBankAccount(bankaccountrib);
    }

    @GetMapping("/allbankaccountbyorder")
    public List<BankAccount> findAllBankAccountByOrder(){

        return bankAccountService.retrieveAllBankAccountByOrder();}




    }





