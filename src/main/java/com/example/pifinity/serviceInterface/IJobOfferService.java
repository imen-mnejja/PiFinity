package com.example.pifinity.serviceInterface;

import com.example.pifinity.entity.JobOffer;
import com.example.pifinity.entity.Partner;

import java.util.List;

public interface IJobOfferService {
    List<JobOffer> findAllJobOffers();
    JobOffer addJobOffer(Long partnerid ,JobOffer jobOffer);
    JobOffer updateJobOffer (Long jobid ,JobOffer jobOffer);
    JobOffer findByIdJobOffer (Long jobid);
    void deleteJobOffer(Long jobid);

}
