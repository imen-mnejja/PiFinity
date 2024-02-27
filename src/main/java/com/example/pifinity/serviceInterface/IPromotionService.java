package com.example.pifinity.serviceInterface;

import com.example.pifinity.entity.Partner;
import com.example.pifinity.entity.Promotion;

import java.util.List;

public interface IPromotionService {
    List<Promotion> findAllPromotions();
    Promotion addPromotion(Long partnerid, Promotion promotion);
    Promotion updatePromotion (Long promoid ,Promotion promotion);
    Promotion findByIdPromotion (Long promoid);
    void deletePromotion(Long promoid);
}
