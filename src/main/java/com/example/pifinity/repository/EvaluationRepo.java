package com.example.pifinity.repository;

import com.example.pifinity.entity.Evaluation;
import com.example.pifinity.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepo extends JpaRepository<Evaluation,Long > {

}
