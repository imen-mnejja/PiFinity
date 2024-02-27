package com.example.pifinity.serviceInterface;



import com.example.pifinity.entity.Evaluation;

import java.util.List;

public interface IEvaluationService {
    List<Evaluation> findAllEvaluations();
    Evaluation addEvaluation(Evaluation evaluation);
    Evaluation updateEvaluation (Long evalid ,Evaluation evaluation);
    Evaluation findByIdEvaluation (Long evalid);
    void deleteEvaluation(Long evalid);
}
