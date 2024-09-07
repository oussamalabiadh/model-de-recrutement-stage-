package com.example.recrutement.repository;

import com.example.recrutement.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.recrutement.entity.EtapeRecrutement;

import java.util.List;

public interface EtapeRecrutementRepository extends JpaRepository<EtapeRecrutement, Long> {
    List<EtapeRecrutement> findByDemande(Demande demande);

}
