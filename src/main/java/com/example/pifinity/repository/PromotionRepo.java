package com.example.pifinity.repository;

import com.example.pifinity.entity.Partner;
import com.example.pifinity.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Long> {
}
