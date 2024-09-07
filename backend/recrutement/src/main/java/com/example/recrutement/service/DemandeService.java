package com.example.recrutement.service;


import com.example.recrutement.Exception.CandidatNotFoundException;
import com.example.recrutement.Exception.DemandeNotFoundException;
import com.example.recrutement.Exception.TooManyCandidatesException;
import com.example.recrutement.dto.ResponseDemandeDTO;
import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.Demande;
import com.example.recrutement.entity.FicheEvaluation;
import com.example.recrutement.repository.CandidatRepository;
import com.example.recrutement.repository.DemandeRepository;
import com.example.recrutement.repository.FicheEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})

@Service
public class DemandeService {
    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private FicheEvaluationRepository ficheEvaluationRepository;

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }


    public Demande getDemandeById(Long id) {
        return demandeRepository.findById(id)
                .orElseThrow(() -> new DemandeNotFoundException("Demande non trouvée pour l'ID : " + id));
    }

    public Demande updateDemande(Demande demande) {
        Long id = demande.getNumeroDeDemande(); // Supposons que votre entité Demande a une propriété id

        // Vérifier si la demande existe déjà dans la base de données
        if (demandeRepository.existsById(id)) {
            // Si elle existe, effectuer la mise à jour
            return demandeRepository.save(demande);
        } else {
            // Si elle n'existe pas, lancer une exception DemandeNotFoundException
            throw new DemandeNotFoundException("La demande avec l'ID " + id + " n'existe pas. Impossible de mettre à jour.");
        }
    }
    @Transactional
    public void deleteDemande(Long id) {
        // Recherche de la Demande par ID
        Optional<Demande> demandeOptional = demandeRepository.findById(id);
        if (demandeOptional.isPresent()) {
            // Si la Demande existe
            Demande demande = demandeOptional.get();
            List<FicheEvaluation> fichesEvaluationOptional = ficheEvaluationRepository.findByDemandeId(demande.getNumeroDeDemande());

            // Suppression de l'association un-à-un avec un candidat
            if (!fichesEvaluationOptional.isEmpty()) {
                // Suppression des fiches d'évaluation liées au candidat
                List<FicheEvaluation> fichesEvaluation = ficheEvaluationRepository.findByDemandeId(demande.getNumeroDeDemande());
                for (FicheEvaluation ficheEvaluation : fichesEvaluation) {
                    ficheEvaluationRepository.delete(ficheEvaluation);
                }
                // Suppression du candidat
                candidatRepository.delete(demande.getCandidatId());
                demande.setCandidatId(null);
            }

            // Suppression de la demande
            demandeRepository.deleteById(id);
        }
    }




    public ResponseDemandeDTO ajouterCandidat(Long demandeId, Long candidatId) {
        Optional<Demande> demandeOptional = demandeRepository.findById(demandeId);
        Optional<Candidat> candidatOptional = candidatRepository.findById(candidatId);

        if (demandeOptional.isEmpty() || candidatOptional.isEmpty()) {
            ResponseDemandeDTO response = new ResponseDemandeDTO();
            response.setState("error");
            response.setMessage("Demande ou Candidat non trouvé");
            return response;
        }

        Demande demande = demandeOptional.get();
        Candidat candidat = candidatOptional.get();

        if (demande.getCandidats().contains(candidat)) {
            ResponseDemandeDTO response = new ResponseDemandeDTO();
            response.setState("failed");
            response.setMessage("Ce candidat est déjà ajouté pour cette demande");
            response.setData(null);
            return response;
        }

        int nbCandidatsActuels = demande.getCandidats().size();
        int nbPersonnes = demande.getNbDePersonnes();

        if (nbCandidatsActuels >= nbPersonnes) {
            ResponseDemandeDTO response = new ResponseDemandeDTO();
            response.setState("failed");
            response.setMessage("Le nombre maximum de candidats a été atteint pour cette demande");
            response.setData(null);
            return response;
        }

        demande.getCandidats().add(candidat);
        candidat.setDemande(demande);
        candidatRepository.save(candidat);
        nbCandidatsActuels = demande.getCandidats().size(); // Mettre à jour le nombre de candidats actuels après l'ajout

        if (nbCandidatsActuels == nbPersonnes) {
            demande.setEtat("cloturée 100%");
        } else if (nbCandidatsActuels >0) {
            demande.setEtat("cloturée " + ((nbCandidatsActuels * 100) / nbPersonnes) + "%");
        }

        Demande savedDemande = demandeRepository.save(demande);

        // Préparez les données à retourner dans la réponse
        List<Demande> demandeList = new ArrayList<>();
        demandeList.add(savedDemande);

        ResponseDemandeDTO response = new ResponseDemandeDTO();
        response.setState("success");
        response.setMessage("Candidat ajouté avec succès");
        response.setData(demandeList); // Retourne une liste contenant la demande mise à jour
        return response;
    }
    public List<Demande> filterByDateDeLaDemande(Date dateDeLaDemande) {
        return demandeRepository.findByDateDeLaDemande(dateDeLaDemande);
    }

    public List<Demande> filterByPoste(String poste) {
        return demandeRepository.findByPoste(poste);
    }

    public List<Demande> filterByDiplome(String diplome) {
        return demandeRepository.findByDiplome(diplome);
    }

    public List<Demande> filterByDemandeur(String demandeur) {
        return demandeRepository.findByDemandeur(demandeur);
    }

    public List<Demande> filterByCategory(String category) {
        return demandeRepository.findByCategory(category);
    }

    public List<Demande> filterByInterneOuExterne(String interneOuExterne) {
        return demandeRepository.findByInterneOuExterne(interneOuExterne);
    }
}


