package com.example.recrutement.repository;

import com.example.recrutement.entity.CvData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvDataRepository extends JpaRepository<CvData, Long> {

    CvData findByCandidatId(Long candidatId);
}