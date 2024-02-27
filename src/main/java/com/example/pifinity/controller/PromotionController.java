package com.example.pifinity.controller;

import com.example.pifinity.entity.Partner;
import com.example.pifinity.entity.Promotion;
import com.example.pifinity.serviceInterface.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promo")
public class PromotionController {
    @Autowired
    private IPromotionService promotionService;

    @GetMapping()
    public List<Promotion> findAllPromotions(){
        return promotionService.findAllPromotions();
    }

    @GetMapping("/{promoid}")
    public Promotion findByIdPromotion(@PathVariable("promoid") Long promoid){
        return promotionService.findByIdPromotion(promoid);
    }

    @PostMapping("/{partnerid}")
    public ResponseEntity<Promotion> addPromotion(@PathVariable Long partnerid, @RequestBody Promotion promotion) {
        Promotion addedPromotion = promotionService.addPromotion(partnerid, promotion);
        return ResponseEntity.ok().body(addedPromotion);
    }

    @PutMapping("/{promoid}")
    public Promotion updatePromotion(@PathVariable Long promoid , @RequestBody Promotion promotion) {
        return promotionService.updatePromotion(promoid , promotion);
    }

    @DeleteMapping("/{promoid}")
    public void deletePromotion(@PathVariable("promoid") Long promoid) {
        promotionService.deletePromotion(promoid);
    }

// ELI B CLE ETRANG YETFAS5OU KAHAW
    //LO5RIN KOL CHAY MRG

}
