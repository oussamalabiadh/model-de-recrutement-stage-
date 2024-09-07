package com.example.recrutement.repository;

import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.Entretien;
import com.example.recrutement.entity.FicheEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntretienRepository extends JpaRepository<Entretien, Long> {
    // Méthodes personnalisées si nécessaire
//
}
