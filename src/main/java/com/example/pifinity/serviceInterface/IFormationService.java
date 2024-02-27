package com.example.pifinity.serviceInterface;

import com.example.pifinity.entity.Formation;
import com.example.pifinity.entity.Partner;

import java.util.List;

public interface IFormationService {
    List<Formation> findAllFormations();
    Formation addFormation(Formation formation);
    Formation updateFormation (Long formationid ,Formation formation);
    Formation findByIdFormation (Long formationid);
    void deleteFormation(Long formationid);
}
