package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.Evaluation;
import com.example.pifinity.entity.JobOffer;
import com.example.pifinity.repository.EvaluationRepo;
import com.example.pifinity.serviceInterface.IEvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class EvaluationServiceImpl implements IEvaluationService {
    EvaluationRepo evaluationRepo;

    @Override
    public List<Evaluation> findAllEvaluations() {
        return evaluationRepo.findAll();
    }

    @Override
    public Evaluation addEvaluation(Evaluation evaluation) {
        return evaluationRepo.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluation(Long evalid, Evaluation updatedEvaluation) {

        Evaluation existingEvaluation = findByIdEvaluation(evalid);
            existingEvaluation.setDescription(updatedEvaluation.getDescription());
            existingEvaluation.setRating(updatedEvaluation.getRating());
            //existingEvaluation.setFormation(updatedEvaluation.getFormation());
            return evaluationRepo.save(existingEvaluation);

    }

    @Override
    public Evaluation findByIdEvaluation(Long evalid) {
        return evaluationRepo.findById(evalid)
                .orElseThrow(() -> new IllegalArgumentException("Evaliation not found with ID: " + evalid));
    }

    @Override
    public void deleteEvaluation(Long evalid) {
        evaluationRepo.deleteById(evalid);
    }
}
