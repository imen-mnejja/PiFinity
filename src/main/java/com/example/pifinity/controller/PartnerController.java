package com.example.pifinity.controller;

import com.example.pifinity.entity.Partner;
import com.example.pifinity.serviceInterface.IPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partner")
public class PartnerController {
//linterface
@Autowired
private IPartnerService partnerService;

    @GetMapping()
    public List<Partner> findAllPartners(){

        return partnerService.findAllPartners();}

    @GetMapping("/{partnerid}")
    public Partner findByIdPartner(@PathVariable("partnerid") Long partnerid){
        return partnerService.findByIdPartner(partnerid);
    }

    //@PostMapping()
    //public Partner addPartner(@RequestBody Partner partner) {
       // return partnerService.addPartner(partner);
    //}

    @PostMapping()
    public ResponseEntity<Object> addPartner(@RequestBody Partner partner) {
        try {
            // Calcul du seuil de rentabilité
            double breakEvenPoint = (double) partner.getChargesfixes() / partner.getChiffredaffaires();

            // Vérification si le seuil de rentabilité dépasse 50
            if (breakEvenPoint > 20) {
                // Ajout du partenaire s'il dépasse le seuil de rentabilité
                Partner newPartner = partnerService.addPartner(partner);
                return  new ResponseEntity<>(newPartner, HttpStatus.CREATED);
            } else {
                // Refus de l'ajout du partenaire car le seuil de rentabilité est inférieur ou égal à 50
                return new ResponseEntity<>("Le seuil de rentabilité est inférieur ou égal à 50. Le partenaire ne peut pas être ajouté.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{partnerid}")
    public Partner updatePartner(@PathVariable Long partnerid , @RequestBody Partner partner) {
        return partnerService.updatePartner(partnerid , partner);
    }

    @DeleteMapping("/delete/{partnerid}")
    public void deletePartner(@PathVariable("partnerid") Long partnerid) {
        partnerService.deletePartner(partnerid);
    }

}
