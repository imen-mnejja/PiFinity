package com.example.pifinity.serviceInterface;

import com.example.pifinity.entity.Advertising;
import com.example.pifinity.entity.Evaluation;

import java.util.List;

public interface IAdvertisingService {
    List<Advertising> findAllAdvertisings();
    //Advertising addAdvertising(Advertising advertising);
    Advertising updateAdvertising (Long pubid ,Advertising advertising);
    Advertising findByIdAdvertising (Long pubid);
    void deleteAdvertising(Long pubid);
    Advertising addAdvertising(Long partnerid, Advertising advertising);
}
