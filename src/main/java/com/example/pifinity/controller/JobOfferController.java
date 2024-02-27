package com.example.pifinity.controller;

import com.example.pifinity.entity.JobOffer;
import com.example.pifinity.serviceInterface.IJobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobOfferController {
    @Autowired
    private IJobOfferService jobOfferService;

    @GetMapping()
    public List<JobOffer> findAllJobOffers(){
        return jobOfferService.findAllJobOffers();
    }

    @GetMapping("/{jobid}")
    public JobOffer findByIdJobOffer(@PathVariable("jobid") Long jobid){
        return jobOfferService.findByIdJobOffer(jobid);
    }

    @PostMapping()
    public JobOffer addJobOffer(@RequestBody JobOffer jobOffer) {
        return jobOfferService.addJobOffer(jobOffer);
    }

    @PutMapping("/{jobid}")
    public JobOffer updateJobOffer(@PathVariable Long jobid , @RequestBody JobOffer jobOffer) {
        return jobOfferService.updateJobOffer(jobid , jobOffer);
    }

    @DeleteMapping("/{jobid}")
    public void deleteJobOffer(@PathVariable("jobid") Long jobid) {
        jobOfferService.deleteJobOffer(jobid);
    }


}
