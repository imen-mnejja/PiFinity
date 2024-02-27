package com.example.pifinity.repository;

import com.example.pifinity.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepo extends JpaRepository <Partner, Long> {
    @Query("SELECT p FROM Partner p WHERE p.bankAccount.RIB = :ribDestination")
    Partner findByBankAccount_RIB(@Param("ribDestination") int ribDestination);
}
