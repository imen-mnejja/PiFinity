package com.example.pifinity.controller;

import com.example.pifinity.entity.*;
import com.example.pifinity.serviceImpl.PartnerServiceImpl;
import com.example.pifinity.serviceImpl.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/qrcode")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;
    @Autowired
    PartnerServiceImpl partnerService;

   @GetMapping("/{partnerid}")
   public ResponseEntity<byte[]> generateQRCodeForPartner(@PathVariable Long partnerid) {
       Partner partner = partnerService.findByIdPartner(partnerid);
       if (partner == null) {
           return ResponseEntity.notFound().build();
       }
       String qrText = generateQRContent(partner); // Méthode pour générer le contenu du code QR
       try {
           byte[] qrCode = qrCodeService.generateQRCode(qrText);
           HttpHeaders headers = new HttpHeaders();
           headers.setContentType(MediaType.IMAGE_PNG);
           return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
/*
   private String generateQRContent(Partner partner) {
        // Construisez ici le contenu du code QR en utilisant les informations du partenaire
        // Par exemple, vous pourriez concaténer le nom, l'ID, les formations, etc.
        return "Contenu du code QR pour le partenaire " + partner.getName()+ "domaine "+partner.getDomain()+
                "Nombre de transactions: "+partner.getNbtransaction()+"Bénéfice net:" +partner.getBeneficenet()
                +"promotion"+partner.getPromotions()+"rbb"+partner.getBankAccount();

   }*/





   private String generateQRContent(Partner partner) {
       StringBuilder qrContent = new StringBuilder();
       // Ajoutez des vérifications pour les valeurs null avant de les ajouter à la chaîne de contenu
       if (partner.getName() != null) {
           qrContent.append("Nom: ").append(partner.getName()).append("\n");
       } else {
           qrContent.append("Nom: N/A\n");
       }
       if (partner.getDomain() != null) {
           qrContent.append("Domaine: ").append(partner.getDomain()).append("\n");
       } else {
           qrContent.append("Domaine: N/A\n");
       }
       if (partner.getNbtransaction() != null) {
           qrContent.append("Nombre de transactions: ").append(partner.getNbtransaction()).append("\n");
       } else {
           qrContent.append("Nombre de transactions: N/A\n");
       }
       if (partner.getBeneficenet() != null) {
           qrContent.append("Bénéfice net: ").append(partner.getBeneficenet()).append("\n");
       } else {
           qrContent.append("Bénéfice net: N/A\n");
       }
       if (partner.getChiffredaffaires() != null) {
           qrContent.append("Chiffre d'affaires: ").append(partner.getChiffredaffaires()).append("\n");
       } else {
           qrContent.append("Chiffre d'affaires: N/A\n");
       }
       if (partner.getChargesfixes() != null) {
           qrContent.append("Charges fixes: ").append(partner.getChargesfixes()).append("\n");
       } else {
           qrContent.append("Charges fixes: N/A\n");
       }


       // Informations sur les formations
       qrContent.append("Formations:\n");
       if (partner.getFormations() != null) {
           for (Formation formation : partner.getFormations()) {
               qrContent.append("- Titre: ");
               if (formation.getTitre() != null) {
                   qrContent.append(formation.getTitre());
               } else {
                   qrContent.append("N/A");
               }
               qrContent.append(", Sujet: ");
               if (formation.getSujet() != null) {
                   qrContent.append(formation.getSujet());
               } else {
                   qrContent.append("N/A");
               }
               qrContent.append("\n");
           }
       } else {
           qrContent.append("Aucune formation disponible\n");
       }

       // Informations sur les promotions
       qrContent.append("Promotions:\n");
       if (partner.getPromotions() != null) {
           for (Promotion promotion : partner.getPromotions()) {
               qrContent.append("- Titre: ").append(promotion.getPromo()).append(", Description: ").append(promotion.getDescrption()).append("\n");
           }
       } else {
           qrContent.append("Aucune promotion disponible\n");
       }

       // Informations sur les offres d'emploi
       qrContent.append("Offres d'emploi:\n");
       if (partner.getJobOffers() != null) {
           for (JobOffer jobOffer : partner.getJobOffers()) {
               qrContent.append("- Titre: ").append(jobOffer.getTitre()).append(", Description: ").append(jobOffer.getDescription()).append("\n");
           }
       } else {
           qrContent.append("Aucune offre d'emploi disponible\n");
       }

       // Informations sur le compte bancaire
       if (partner.getBankAccount() != null) {
           qrContent.append("Compte bancaire:\n");
           qrContent.append("- RIB: ").append(partner.getBankAccount().getRIB()).append(", Date de création: ").append(partner.getBankAccount().getDateCreation()).append("\n");
       } else {
           qrContent.append("Aucun compte bancaire disponible\n");
       }

       // Informations sur la publicité
       if (partner.getAdvertising() != null) {
           qrContent.append("Vous avez une publicité gratuite sur notre plateforme\n");
       } else if (partner.getNbtransaction() < 2) {
           qrContent.append("Votre nombre de transactions est inférieur à 2, donc vous n'avez pas de publicité\n");
       }
       return qrContent.toString();
   }




}
