package com.example.pifinity.repository;

import com.example.pifinity.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepo extends JpaRepository<JobOffer, Long> {
    @Query("SELECT j FROM JobOffer j WHERE j.skills LIKE %:skill%")
    List<JobOffer> findBySkill(@Param("skill") String skill);

}
