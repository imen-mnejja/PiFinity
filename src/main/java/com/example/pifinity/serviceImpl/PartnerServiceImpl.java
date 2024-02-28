package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.Partner;
import com.example.pifinity.repository.PartnerRepo;
import com.example.pifinity.serviceInterface.IPartnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PartnerServiceImpl implements IPartnerService {
    PartnerRepo partnerRepo;
EmailService emailService;
    @Override
    public List<Partner> findAllPartners() {
        return partnerRepo.findAll();
    }
   // @Override
   // public Partner addPartner(Partner partner) {
      //  return partnerRepo.save(partner);
  //  }
    @Override
    public Partner addPartner(Partner partner) {
        //partner.setNbtransaction(0);
        // Calcul du seuil de rentabilité
        double breakEvenPoint = (double) partner.getChargesfixes() / partner.getChiffredaffaires();

        // Vérification si le seuil de rentabilité dépasse 20
        if (breakEvenPoint > 20) {

            // Accepter le partenaire et l'enregistrer
            Partner savedPartner = partnerRepo.save(partner);

            // Envoyer un e-mail de notification
            String subject = "Nouveau partenaire ajouté";
            String text = "Un nouveau partenaire a été ajouté : " + partner.getName();
            emailService.sendEmail("manar.boukhris@esprit.tn", subject, text);

            return savedPartner;
        } else {
            // Refuser l'ajout du partenaire car le seuil de rentabilité est inférieur ou égal à 20
            throw new IllegalArgumentException("Le seuil de rentabilité est inférieur ou égal à 20. Le partenaire ne peut pas être ajouté.");
        }
    }

    @Override
    public Partner updatePartner(Long partnerid , Partner updatedPartner) {
        Partner existingPartner = findByIdPartner(partnerid);
        existingPartner.setName(updatedPartner.getName());
        existingPartner.setDomain(updatedPartner.getDomain());
        existingPartner.setAdvertising(updatedPartner.getAdvertising());
        //existingPartner.setNbtransaction(updatedPartner.getNbtransaction());
        return partnerRepo.save(existingPartner);
    }
    @Override
    public Partner findByIdPartner(Long partnerid) {
        return partnerRepo.findById(partnerid)
                .orElseThrow(() -> new IllegalArgumentException("Partner not found with ID: " + partnerid));
    }
    @Override
    public void deletePartner(Long partnerid) {
         partnerRepo.deleteById(partnerid);
    }

}
