package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.Partner;
import com.example.pifinity.entity.Promotion;
import com.example.pifinity.repository.PartnerRepo;
import com.example.pifinity.repository.PromotionRepo;
import com.example.pifinity.serviceInterface.IPromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements IPromotionService {
    PromotionRepo promotionRepo;
    PartnerRepo partnerRepo;

    @Override
    public List<Promotion> findAllPromotions() {
        return promotionRepo.findAll();
    }

    @Override
    public Promotion addPromotion(Long partnerid, Promotion promotion) {
        // Récupération du partenaire associé à l'ID spécifié
        Partner partner = partnerRepo.findById(partnerid)
                .orElseThrow(() -> new IllegalArgumentException("Partenaire non trouvé avec l'ID : " + partnerid));

        // Calcul du pourcentage de réduction en fonction du chiffre d'affaires du partenaire
        double reductionPercentage;
        Long chiffreAffaires = partner.getChiffredaffaires();
        if (chiffreAffaires < 50) {
            reductionPercentage = 0.20; // -20%
        } else if (chiffreAffaires >= 50 && chiffreAffaires < 90) {
            reductionPercentage = 0.50; // -50%
        } else {
            reductionPercentage = 0.70; // -70%
        }

        // Mise à jour de la promotion avec le pourcentage de réduction
        promotion.setPromo(String.valueOf(reductionPercentage * 100) + "%");

        // Attribution du partenaire à la promotion
        promotion.setPartner(partner);

        // Enregistrement de la promotion mise à jour dans la base de données
        return promotionRepo.save(promotion);
    }


    @Override
    public Promotion updatePromotion(Long promoid, Promotion updatedPromotion) {
        Promotion existingPromotion = findByIdPromotion(promoid);
        existingPromotion.setDescrption(updatedPromotion.getDescrption());
      //  existingPromotion.setPromo(updatedPromotion.getPromo());
        existingPromotion.setCateg(updatedPromotion.getCateg());
        //existingPromotion.getPartner(updatedPromotion.getPartner());
        return promotionRepo.save(existingPromotion);

    }

    @Override
    public Promotion findByIdPromotion(Long promoid) {
        return promotionRepo.findById(promoid)
                .orElseThrow(() -> new IllegalArgumentException("promo not found with ID: " + promoid));
    }
    @Override
    public void deletePromotion(Long promoid) {
        promotionRepo.deleteById(promoid);
    }
}
