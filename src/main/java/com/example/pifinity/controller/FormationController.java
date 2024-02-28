package com.example.pifinity.controller;

import com.example.pifinity.entity.Formation;
import com.example.pifinity.serviceInterface.IFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/{partnerid}")
    public ResponseEntity<Formation> addFormation(@PathVariable Long partnerid, @RequestBody Formation formation) {
        Formation addedformation= formationService.addFormation(partnerid,formation);
        return ResponseEntity.ok().body(addedformation);
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

