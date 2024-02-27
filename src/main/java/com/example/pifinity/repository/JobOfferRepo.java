package com.example.pifinity.repository;

import com.example.pifinity.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepo extends JpaRepository<JobOffer, Long> {

}
