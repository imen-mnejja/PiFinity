package com.example.pifinity.repository;

import com.example.pifinity.entity.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisingRepo extends JpaRepository<Advertising,Long > {
}
