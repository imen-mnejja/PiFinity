package com.example.pifinity.serviceInterface;

import com.example.pifinity.entity.Partner;

import java.util.List;


public interface IPartnerService {
    List<Partner> findAllPartners();
    Partner addPartner(Partner partner);
    Partner updatePartner (Long partnerid ,Partner partner);
    Partner findByIdPartner (Long partnerid);
    void deletePartner(Long partnerid);

}
