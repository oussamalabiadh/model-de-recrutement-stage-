package com.example.recrutement.service;

import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.FicheEvaluation;
import com.example.recrutement.repository.CandidatRepository;
import com.example.recrutement.repository.CvDataRepository;
import com.example.recrutement.repository.FicheEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private CvDataRepository cvDataRepository;
    @Autowired
    private FicheEvaluationRepository ficheEvaluationRepository;

    public void deleteCandidat(Long id) {
        // Check if the candidate has any evaluation records
        List<FicheEvaluation> evaluations = ficheEvaluationRepository.findByCandidatId(id);
        if (evaluations != null && !evaluations.isEmpty()) {
            // Delete all evaluation records associated with the candidate
            ficheEvaluationRepository.deleteAll(evaluations);
        }
        // Delete CV data and candidate
        cvDataRepository.deleteById(id);
        candidatRepository.deleteById(id);
    }

    public Candidat getCandidatById(Long id) {
        return candidatRepository.findById(id).orElse(null);
    }
}
