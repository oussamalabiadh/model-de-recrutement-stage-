package com.example.recrutement.controller;

import com.example.recrutement.dto.CandidatComparatifDTO;
import com.example.recrutement.service.FicheEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comparative")
public class ComparativeController {

    private final FicheEvaluationService ficheEvaluationService;

    @Autowired
    public ComparativeController(FicheEvaluationService ficheEvaluationService) {
        this.ficheEvaluationService = ficheEvaluationService;
    }

        @GetMapping("/demande/{demandeId}")
        public List<CandidatComparatifDTO> getComparativeTableByDemandeId(@PathVariable Long demandeId) {
            return ficheEvaluationService.getComparativeTableByDemandeId(demandeId);
        }
}
