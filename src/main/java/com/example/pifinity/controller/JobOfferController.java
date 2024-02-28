package com.example.pifinity.controller;

import com.example.pifinity.entity.JobOffer;
import com.example.pifinity.serviceImpl.JobOfferServiceImpl;
import com.example.pifinity.serviceInterface.IJobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobOfferController {
    @Autowired
    private IJobOfferService jobOfferService;
    @Autowired
    private JobOfferServiceImpl jobOfferServicee;

    @GetMapping()
    public List<JobOffer> findAllJobOffers(){
        return jobOfferService.findAllJobOffers();
    }

    @GetMapping("/{jobid}")
    public JobOffer findByIdJobOffer(@PathVariable("jobid") Long jobid){
        return jobOfferService.findByIdJobOffer(jobid);
    }

    @PostMapping("/{partnerid}")
    public ResponseEntity<JobOffer> addJobOffer(@PathVariable Long partnerid , @RequestBody JobOffer jobOffer) {
        JobOffer addedJob= jobOfferService.addJobOffer(partnerid,jobOffer);
        return ResponseEntity.ok().body(addedJob);
    }

    @PutMapping("/{jobid}")
    public JobOffer updateJobOffer(@PathVariable Long jobid , @RequestBody JobOffer jobOffer) {
        return jobOfferService.updateJobOffer(jobid , jobOffer);
    }

    @DeleteMapping("/{jobid}")
    public void deleteJobOffer(@PathVariable("jobid") Long jobid) {
        jobOfferService.deleteJobOffer(jobid);
    }

    @GetMapping("/search/{skill}")
    public ResponseEntity<Object> searchJobOffersBySkill(@PathVariable String skill) {
        try {
            List<JobOffer> jobOffers = jobOfferServicee.findJobOffersBySkill(skill);
            if (!jobOffers.isEmpty()) {
                return new ResponseEntity<>(jobOffers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Désolé, nous n'avons pas trouvé d'offres pour le skill spécifié.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
