package com.example.pifinity.controller;


import com.example.pifinity.entity.VirtualBankCard;
import com.example.pifinity.serviceInterface.IVirtualBankCardService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/virtualbankcard")
@AllArgsConstructor
public class VirtualBankCardController {


    //@Autowired
    private IVirtualBankCardService virtualBankCardService;

    @GetMapping("/allvirtualbankcard")
    public List<VirtualBankCard> findAllVirtualbankCard(){

        return virtualBankCardService.retrieveAllVirtualBankCard();}

    @GetMapping("/{idvirtualbankcard}")
    public VirtualBankCard findByIdVirtualBankCard(@PathVariable("idvirtualbankcard") int NumCard){
        return virtualBankCardService.retrieveVirtualBankCard(NumCard);}


    @PostMapping("/addvirtualbankcard/{rib}")
    public VirtualBankCard addVirtualBankCard(@PathVariable int rib,@RequestBody VirtualBankCard virtualBankCard) {
        return virtualBankCardService.addVirtualBankCard(rib,virtualBankCard);
    }


    @DeleteMapping("/delete/{idvirtualbankcard}")
    public void deleteVirtualBankCard(@PathVariable("idvirtualbankcard") int idvirtualbankcard) {
        virtualBankCardService.deleteVirtualBankCard(idvirtualbankcard);
    }




}
