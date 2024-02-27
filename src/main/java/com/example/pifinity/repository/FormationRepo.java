package com.example.pifinity.repository;

import com.example.pifinity.entity.Formation;
import com.example.pifinity.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepo extends JpaRepository<Formation, Long> {
}
