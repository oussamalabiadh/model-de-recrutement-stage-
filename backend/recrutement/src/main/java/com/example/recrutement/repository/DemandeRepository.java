package com.example.recrutement.repository;

import com.example.recrutement.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
    @Query("SELECT d FROM Demande d WHERE :dateDeLaDemande IS NULL OR d.dateDeLaDemande = :dateDeLaDemande")
    List<Demande> findByDateDeLaDemande(@Param("dateDeLaDemande") Date dateDeLaDemande);

    @Query("SELECT d FROM Demande d WHERE :poste IS NULL OR d.poste = :poste")
    List<Demande> findByPoste(@Param("poste") String poste);

    @Query("SELECT d FROM Demande d WHERE :diplome IS NULL OR d.diplome = :diplome")
    List<Demande> findByDiplome(@Param("diplome") String diplome);

    @Query("SELECT d FROM Demande d WHERE :demandeur IS NULL OR d.demandeur = :demandeur")
    List<Demande> findByDemandeur(@Param("demandeur") String demandeur);

    @Query("SELECT d FROM Demande d WHERE :category IS NULL OR d.category = :category")
    List<Demande> findByCategory(@Param("category") String category);

    @Query("SELECT d FROM Demande d WHERE :interneOuExterne IS NULL OR d.interneOuExterne = :interneOuExterne")
    List<Demande> findByInterneOuExterne(@Param("interneOuExterne") String interneOuExterne);
}
