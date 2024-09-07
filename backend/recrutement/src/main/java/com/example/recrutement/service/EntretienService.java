package com.example.recrutement.service;

import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.Entretien;
import com.example.recrutement.entity.FicheEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.recrutement.repository.CandidatRepository;
import com.example.recrutement.repository.EntretienRepository;
import java.util.List;
import java.util.Optional;

@Service
public class EntretienService {


    @Autowired
    private EntretienRepository entretienRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    public void deletEntretien(Long id) {
        // Récupérer l'entretien par ID
        Optional<Entretien> entretienOptional = entretienRepository.findById(id);
            Entretien entretien = entretienOptional.get();
            // Récupérer le candidat associé à l'entretien
            Optional<Candidat> candidatOptional = candidatRepository.findById(entretien.getCandidatId());

            // Si le candidat existe, mettre à jour l'entretien téléphonique à false
            candidatOptional.ifPresent(candidat -> {
                candidat.setEntretienTelephonique(false);
                candidatRepository.save(candidat);
            });

            // Supprimer l'entretien
            entretienRepository.deleteById(id);
    }




    public List<Entretien> findAll() {
        return entretienRepository.findAll();
    }
    public Entretien getEntretientById(Long id) {
        return entretienRepository.findById(id).orElse(null);
    }

}
