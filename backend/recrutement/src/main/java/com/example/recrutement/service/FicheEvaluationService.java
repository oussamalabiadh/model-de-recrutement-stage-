package com.example.recrutement.service;

import com.example.recrutement.dto.CandidatComparatifDTO;
import com.example.recrutement.dto.CandidatEvaluationSummary;
import com.example.recrutement.dto.ResponseGeneraleDTO;
import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.Demande;
import com.example.recrutement.entity.FicheEvaluation;
import com.example.recrutement.entity.NotificationEvalution;
import com.example.recrutement.repository.CandidatRepository;
import com.example.recrutement.repository.DemandeRepository;
import com.example.recrutement.repository.FicheEvaluationRepository;
import com.example.recrutement.repository.NotificationEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FicheEvaluationService {

    private final FicheEvaluationRepository ficheEvaluationRepository;
    private final CandidatRepository candidatRepository;
    private final DemandeRepository demandeRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public FicheEvaluationService(FicheEvaluationRepository ficheEvaluationRepository,
                                  CandidatRepository candidatRepository,
                                  DemandeRepository demandeRepository,
                                  JavaMailSender mailSender) {
        this.ficheEvaluationRepository = ficheEvaluationRepository;
        this.candidatRepository = candidatRepository;
        this.demandeRepository = demandeRepository;
        this.mailSender = mailSender;
    }



    public FicheEvaluation getEvaluationByCandidatIdAndEvaluationId(Long candidatId, Long evaluationId) {
        Optional<FicheEvaluation> optionalEvaluation = ficheEvaluationRepository.findByIdAndCandidatId(evaluationId, candidatId);
        if (optionalEvaluation.isPresent()) {
            return optionalEvaluation.get();
        } else {
            return null; // or any other handling you prefer
        }
    }
    public ResponseGeneraleDTO saveEvaluation(Long candidatId, Long demandeId, FicheEvaluation evaluation) {
        Candidat candidat = candidatRepository.findById(candidatId).orElse(null);
        if (candidat == null) {
            return new ResponseGeneraleDTO("failed", "Candidat not found with id: " + candidatId);
        }

        Demande demande = demandeRepository.findById(demandeId).orElse(null);
        if (demande == null) {
            return new ResponseGeneraleDTO("failed", "Demande not found with id: " + demandeId);
        }

        evaluation.setCandidat(candidat);
        evaluation.setDemande(demande);

        // Vérifier si toutes les notes sont non null
        if (evaluation.getNoteAdressePersonnelle() != null &&
                evaluation.getNoteParcoursEducatif() != null &&
                evaluation.getNoteNiveauEtude() != null &&
                evaluation.getNoteSpecialiteDemandee() != null &&
                evaluation.getNoteAutresExigences() != null &&
                evaluation.getNoteDisponibilite() != null &&
                evaluation.getNoteTypeContrat() != null &&
                evaluation.getNoteExperience() != null &&
                evaluation.getNoteNaturePostes() != null &&
                evaluation.getNoteStages() != null &&
                evaluation.getNoteFormations() != null &&
                evaluation.getNoteActivitesAssociatives() != null &&
                evaluation.getNoteCompetencesTechniquesSavoir() != null &&
                evaluation.getNoteCompetencesTechniquesSavoirFaire() != null &&
                evaluation.getNoteComportementPonctualite() != null &&
                evaluation.getNoteComportementPreparationEntretien() != null &&
                evaluation.getNoteComportementConnaisanceEntreprise() != null &&
                evaluation.getNoteComportementExpressionPriseParole() != null &&
                evaluation.getNoteComportementCapaciteCommuniquer() != null &&
                evaluation.getNoteComportementConfianceEnSoi() != null &&
                evaluation.getNoteComportementEcoute() != null &&
                evaluation.getNoteComportementMotivation() != null) {

            // Calculer la note globale
            Integer noteGlobale = calculateGlobalNote(evaluation);
            evaluation.setNoteGlobale(noteGlobale);
        }
        evaluation.setDateDeEvaluation(new Date());
        ficheEvaluationRepository.save(evaluation);

        return new ResponseGeneraleDTO("success", "Fiche d'évaluation enregistrée avec succès");
    }

    public Integer calculateGlobalNote(FicheEvaluation evaluation) {
        Integer S = evaluation.getNoteAdressePersonnelle() +
                evaluation.getNoteParcoursEducatif() +
                evaluation.getNoteNiveauEtude() +
                evaluation.getNoteSpecialiteDemandee() +
                evaluation.getNoteAutresExigences() +
                evaluation.getNoteDisponibilite() +
                evaluation.getNoteTypeContrat() +
                evaluation.getNoteExperience() +
                evaluation.getNoteNaturePostes() +
                evaluation.getNoteStages() +
                evaluation.getNoteFormations() +
                evaluation.getNoteActivitesAssociatives() +
                evaluation.getNoteCompetencesTechniquesSavoir() +
                evaluation.getNoteCompetencesTechniquesSavoirFaire() +
                evaluation.getNoteComportementPonctualite() +
                evaluation.getNoteComportementPreparationEntretien() +
                evaluation.getNoteComportementConnaisanceEntreprise() +
                evaluation.getNoteComportementExpressionPriseParole() +
                evaluation.getNoteComportementCapaciteCommuniquer() +
                evaluation.getNoteComportementConfianceEnSoi() +
                evaluation.getNoteComportementEcoute() +
                evaluation.getNoteComportementMotivation();
        return S ;
    }

    public List<FicheEvaluation> getAllEvaluations() {
        return ficheEvaluationRepository.findAll();
    }

    public FicheEvaluation getEvaluationById(Long evaluationId) {
        return ficheEvaluationRepository.findById(evaluationId).orElse(null);
    }

    public List<FicheEvaluation> getEvaluationsByCandidatId(Long candidatId) {
        return ficheEvaluationRepository.findByCandidatId(candidatId);
    }
    public CandidatEvaluationSummary getEvaluationsSummaryByCandidatId(Long candidatId) {
        List<FicheEvaluation> evaluations = ficheEvaluationRepository.findByCandidatId(candidatId);
        int numberOfFiles = evaluations.size();
        int totalGlobalNote = evaluations.stream().mapToInt(FicheEvaluation::getNoteGlobale).sum();

        return new CandidatEvaluationSummary(candidatId, numberOfFiles, totalGlobalNote);
    }

    public void deleteFiche(Long id) {
       ficheEvaluationRepository.deleteById(id);
    }

        // Existing code

    public List<CandidatComparatifDTO> getComparativeTableByDemandeId(Long demandeId) {
        // Récupérer la demande par son ID
        Demande demande = demandeRepository.findById(demandeId).orElse(null);
        if (demande == null) {
            return Collections.emptyList(); // Ou gérer l'erreur selon les besoins
        }

        // Récupérer toutes les fiches d'évaluation associées à cette demande
        List<FicheEvaluation> evaluations = ficheEvaluationRepository.findByDemandeId(demandeId);

        // Récupérer les candidats associés à ces fiches d'évaluation
        Map<Candidat, List<FicheEvaluation>> evaluationsByCandidat = evaluations.stream()
                .collect(Collectors.groupingBy(FicheEvaluation::getCandidat));

        // Transformer chaque groupe de fiches d'évaluation par candidat en CandidatComparatifDTO
        List<CandidatComparatifDTO> comparativeTable = evaluationsByCandidat.entrySet().stream()
                .map(entry -> {
                    Candidat candidat = entry.getKey();
                    List<FicheEvaluation> candidateEvaluations = entry.getValue();

                    // Calculer le nombre de fiches et la note globale moyenne
                    int numberOfFiles = candidateEvaluations.size();
                    double averageNoteGlobale = candidateEvaluations.stream()
                            .mapToInt(FicheEvaluation::getNoteGlobale)
                            .average()
                            .orElse(0.0);

                    // Créer un nouvel objet CandidatComparatifDTO avec les informations nécessaires
                    return new CandidatComparatifDTO(
                            candidat.getId(),
                            candidat.getNom(),
                            candidat.getPrenom(),
                            candidat.getDiplome(),
                            candidat.getAge(),
                            candidat.getSex(),
                            demande.getPoste(),
                            averageNoteGlobale,
                            numberOfFiles
                    );
                })
                // Trier les candidats par leur note globale moyenne en ordre croissant
                .sorted(Comparator.comparingDouble(CandidatComparatifDTO::getNoteGlobaleMoyenne))
                // Collecter les résultats dans une liste
                .collect(Collectors.toList());

        // Retourner la liste des candidats triés
        return comparativeTable;
    }
    }


