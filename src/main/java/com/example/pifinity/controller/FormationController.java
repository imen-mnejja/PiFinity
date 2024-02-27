package com.example.pifinity.controller;

import com.example.pifinity.entity.Formation;
import com.example.pifinity.entity.Partner;
import com.example.pifinity.serviceInterface.IFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formation")
public class FormationController {
    @Autowired
    private IFormationService formationService;
    @GetMapping()
    public List<Formation> findAllFormations(){

        return formationService.findAllFormations();}

    @GetMapping("/{formationid}")
    public Formation findByIdFormation(@PathVariable("formationid") Long formationid){
        return formationService.findByIdFormation(formationid);
    }

    @PostMapping()
    public Formation addFormation(@RequestBody Formation formation) {
        return formationService.addFormation(formation);
    }

    @PutMapping("/{formationid}")
    public Formation updateFormation(@PathVariable Long formationid , @RequestBody Formation formation) {
        return formationService.updateFormation(formationid , formation);
    }

    @DeleteMapping("/{formationid}")
    public void deleteFormation(@PathVariable("formationid") Long formationid) {
        formationService.deleteFormation(formationid);
    }
}

