package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.Advertising;
import com.example.pifinity.entity.Evaluation;
import com.example.pifinity.entity.Partner;
import com.example.pifinity.repository.AdvertisingRepo;
import com.example.pifinity.repository.PartnerRepo;
import com.example.pifinity.serviceInterface.IAdvertisingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdvertisingServiceImpl implements IAdvertisingService {
    AdvertisingRepo advertisingRepo;
PartnerRepo partnerRepo;
    @Override
    public List<Advertising> findAllAdvertisings() {
        return advertisingRepo.findAll();
    }

    //@Override
   // public Advertising addAdvertising(Advertising advertising) {
       // return advertisingRepo.save(advertising);
   // }
    @Override
    public Advertising addAdvertising(Long partnerid, Advertising advertising) {
        // Récupération du partenaire associé à l'ID spécifié
        Partner partner = partnerRepo.findById(partnerid)
                .orElseThrow(() -> new IllegalArgumentException("Partenaire non trouvé avec l'ID : " + partnerid));

        // Vérification du nombre de transactions du partenaire
        if (partner.getNbtransaction() != null && partner.getNbtransaction() >= 2) {
            advertising.setPartner(partner);
            Advertising savedAdvertising = advertisingRepo.save(advertising);

            // Mise à jour de l'attribut advertising du partenaire
            partner.setAdvertising(savedAdvertising);
            partnerRepo.save(partner);

            return savedAdvertising;
        } else {
            throw new IllegalArgumentException("Le partenaire avec l'ID : " + partnerid + " doit avoir au moins 2 transactions pour ajouter de la publicité.");
        }
    }
    @Override
    public Advertising updateAdvertising(Long pubid, Advertising updatedAdvertising) {
        Advertising existingAdvertising = findByIdAdvertising(pubid);
        existingAdvertising.setDescription(updatedAdvertising.getDescription());
        existingAdvertising.setType(updatedAdvertising.getType());
        existingAdvertising.setTitre(updatedAdvertising.getTitre());
        return advertisingRepo.save(updatedAdvertising);
    }

    @Override
    public Advertising findByIdAdvertising(Long pubid) {
        return advertisingRepo.findById(pubid)
                .orElseThrow(() -> new IllegalArgumentException("Evaliation not found with ID: " + pubid));
    }

    @Override
    public void deleteAdvertising(Long pubid) {
advertisingRepo.deleteById(pubid);
    }
}
