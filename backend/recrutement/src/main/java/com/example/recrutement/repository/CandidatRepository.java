package com.example.recrutement.repository;

import com.example.recrutement.entity.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
}
