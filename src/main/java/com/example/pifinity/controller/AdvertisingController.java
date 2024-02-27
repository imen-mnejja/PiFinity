package com.example.pifinity.controller;

import com.example.pifinity.entity.Advertising;
import com.example.pifinity.entity.Evaluation;
import com.example.pifinity.serviceInterface.IAdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pub")
public class AdvertisingController {
    @Autowired
    private IAdvertisingService advertisingService;
    @GetMapping()
    public List<Advertising> findAllAdvertisings(){
        return advertisingService.findAllAdvertisings();
    }

    @GetMapping("/{pubid}")
    public Advertising findByIdAdvertising(@PathVariable("pubid") Long pubid){
        return advertisingService.findByIdAdvertising(pubid);
    }

    @PostMapping("/{partnerid}/add")
    public ResponseEntity<Object> addAdvertisingToPartner(@PathVariable Long partnerid, @RequestBody Advertising advertising) {
        try {
            Advertising newAdvertising = advertisingService.addAdvertising(partnerid, advertising);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAdvertising);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de l'ajout de la publicité.");
        }
    }

    @PutMapping("/{pubid}")
    public Advertising updateAdvertising(@PathVariable Long pubid , @RequestBody Advertising advertising) {
        return advertisingService.updateAdvertising(pubid , advertising);
    }

    @DeleteMapping("/{pubid}")
    public void deleteAdvertising(@PathVariable("pubid") Long pubid) {
        advertisingService.deleteAdvertising(pubid);
    }

}
