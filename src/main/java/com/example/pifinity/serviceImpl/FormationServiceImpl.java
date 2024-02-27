package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.Formation;
import com.example.pifinity.entity.Partner;
import com.example.pifinity.repository.FormationRepo;
import com.example.pifinity.serviceInterface.IFormationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements IFormationService {
    FormationRepo formationRepo;

    @Override
    public List<Formation> findAllFormations() {
        return formationRepo.findAll();
    }

    @Override
    public Formation addFormation(Formation formation) {
        return formationRepo.save(formation);
    }

    @Override
    public Formation updateFormation(Long formationid, Formation updatedFormation) {
        Formation existingFormation = findByIdFormation(formationid);
        existingFormation.setSujet(updatedFormation.getSujet());
        existingFormation.setType(updatedFormation.getType());
        existingFormation.setTitre(updatedFormation.getTitre());
        existingFormation.setCategorie(updatedFormation.getCategorie());
        return formationRepo.save(existingFormation);
    }
    @Override
    public Formation findByIdFormation(Long formationid) {
        return formationRepo.findById(formationid)
                .orElseThrow(() -> new IllegalArgumentException("Partner not found with ID: " + formationid));
    }

    @Override
    public void deleteFormation(Long formationid) {
        formationRepo.deleteById(formationid);}
}
