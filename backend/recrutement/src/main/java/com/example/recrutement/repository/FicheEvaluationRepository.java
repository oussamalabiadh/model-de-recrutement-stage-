package com.example.recrutement.repository;

import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.Demande;
import com.example.recrutement.entity.FicheEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FicheEvaluationRepository extends JpaRepository<FicheEvaluation, Long> {
    List<FicheEvaluation> findByCandidatId(Long candidatId);


    Optional<FicheEvaluation> findByIdAndCandidatId(Long id, Long candidatId);
    @Query("SELECT fe FROM FicheEvaluation fe JOIN fe.demande d WHERE d.numeroDeDemande = :numeroDeDemande")

    List<FicheEvaluation> findByDemandeId(Long numeroDeDemande);
}
