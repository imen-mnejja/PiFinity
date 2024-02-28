package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.Formation;
import com.example.pifinity.entity.JobOffer;
import com.example.pifinity.entity.Partner;
import com.example.pifinity.entity.Promotion;
import com.example.pifinity.repository.JobOfferRepo;
import com.example.pifinity.repository.PartnerRepo;
import com.example.pifinity.serviceInterface.IJobOfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobOfferServiceImpl implements IJobOfferService {
    JobOfferRepo jobOfferRepo;
    PartnerRepo partnerRepo;

    @Override
    public List<JobOffer> findAllJobOffers() {
        return jobOfferRepo.findAll();
    }

    public List<JobOffer> findJobOffersBySkill(String skill) {
        return jobOfferRepo.findBySkill(skill);
    }

    @Override
    public JobOffer addJobOffer(Long partnerid , JobOffer jobOffer) {
        Partner partner = partnerRepo.findById(partnerid)
                .orElseThrow(() -> new IllegalArgumentException("Partenaire non trouvé avec l'ID : " + partnerid));
        jobOffer.setPartner(partner);
        return jobOfferRepo.save(jobOffer);
    }

    @Override
    public JobOffer updateJobOffer(Long jobid, JobOffer updatedJobOffer) {
        JobOffer existingJobOffer = findByIdJobOffer(jobid);
        existingJobOffer.setTitre(updatedJobOffer.getTitre());
        existingJobOffer.setSkills(updatedJobOffer.getSkills());
        existingJobOffer.setDescription(updatedJobOffer.getDescription());
            return jobOfferRepo.save(existingJobOffer);
        }

    @Override
    public JobOffer findByIdJobOffer(Long jobid) {
        return jobOfferRepo.findById(jobid) .orElseThrow(() -> new IllegalArgumentException("Partner not found with ID: " + jobid));
    }

    @Override
    public void deleteJobOffer(Long jobid) {
        jobOfferRepo.deleteById(jobid);
    }
}
