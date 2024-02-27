package com.example.pifinity.controller;

import com.example.pifinity.entity.Evaluation;
import com.example.pifinity.entity.JobOffer;
import com.example.pifinity.serviceInterface.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private IEvaluationService evaluationService;
    @GetMapping()
    public List<Evaluation> findAllEvaluations(){
        return evaluationService.findAllEvaluations();
    }

    @GetMapping("/{evalid}")
    public Evaluation findByIdEvaluation(@PathVariable("evalid") Long evalid){
        return evaluationService.findByIdEvaluation(evalid);
    }

    @PostMapping()
    public Evaluation addEvaluation(@RequestBody Evaluation evaluation) {
        return evaluationService.addEvaluation(evaluation);
    }

    @PutMapping("/{evalid}")
    public Evaluation updateEvaluation(@PathVariable Long evalid , @RequestBody Evaluation evaluation) {
        return evaluationService.updateEvaluation(evalid , evaluation);
    }

    @DeleteMapping("/{evalid}")
    public void deleteEvaluation(@PathVariable("evalid") Long evalid) {
        evaluationService.deleteEvaluation(evalid);
    }

}
