package com.example.pifinity.repository;

import com.example.pifinity.entity.VirtualBankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VirtualBankCardRepository extends JpaRepository<VirtualBankCard,Integer> {




}
