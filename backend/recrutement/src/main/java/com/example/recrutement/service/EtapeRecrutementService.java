package com.example.recrutement.service;

import com.example.recrutement.dto.EtapeDTO;
import com.example.recrutement.dto.EtapeListDTO;
import com.example.recrutement.entity.Demande;
import com.example.recrutement.entity.EtapeRecrutement;
import com.example.recrutement.repository.DemandeRepository;
import com.example.recrutement.repository.EtapeRecrutementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EtapeRecrutementService {
    @Autowired
    private EtapeRecrutementRepository etapeRepository;

    @Autowired
    private DemandeRepository demandeRepository;

    public EtapeDTO<EtapeRecrutement> createEtape(Long demandeId, EtapeRecrutement etape) {
        EtapeDTO<EtapeRecrutement> response = new EtapeDTO<>();
        Optional<Demande> optionalDemande = demandeRepository.findById(demandeId);

        if (optionalDemande.isPresent()) {
            etape.setDemande(optionalDemande.get());
            EtapeRecrutement savedEtape = etapeRepository.save(etape);
            response.setState("success");
            response.setMessage("Etape created successfully");
            response.setData(savedEtape);
        } else {
            response.setState("failed");
            response.setMessage("Demande not found");
            response.setData(null);
        }
        return response;
    }

    public EtapeDTO<EtapeRecrutement> updateEtape(Long etapeId, EtapeRecrutement updatedEtape) {
        EtapeDTO<EtapeRecrutement> response = new EtapeDTO<>();
        Optional<EtapeRecrutement> optionalEtape = etapeRepository.findById(etapeId);

        if (optionalEtape.isPresent()) {
            EtapeRecrutement etape = optionalEtape.get();
            etape.setCreationProfileDePoste(updatedEtape.getCreationProfileDePoste());
            etape.setDiffusionDeLAnnonce(updatedEtape.getDiffusionDeLAnnonce());
            etape.setTriDesCvs(updatedEtape.getTriDesCvs());
            etape.setPremierEntretien(updatedEtape.getPremierEntretien());
            etape.setCommunicationDeCvRetenus(updatedEtape.getCommunicationDeCvRetenus());
            etape.setInvitationDesCandidats(updatedEtape.getInvitationDesCandidats());
            etape.setRealisationDesEntretiens(updatedEtape.getRealisationDesEntretiens());
            etape.setCommunicationDesResultats(updatedEtape.getCommunicationDesResultats());
            etape.setValidationDesCandidats(updatedEtape.getValidationDesCandidats());
            etape.setPropositionDeRecrutement(updatedEtape.getPropositionDeRecrutement());
            etape.setDateDeCloture(updatedEtape.getDateDeCloture());
            etape.setRealisationDuContratAdministratif(updatedEtape.getRealisationDuContratAdministratif());
            etape.setRealisationDuPlan(updatedEtape.getRealisationDuPlan());
            etape.setMatriculeDeRecrutement(updatedEtape.getMatriculeDeRecrutement());
            etape.setNomDeRecrutement(updatedEtape.getNomDeRecrutement());
            etape.setDateDeDemarrage(updatedEtape.getDateDeDemarrage());
            etape.setDureeEnMois(updatedEtape.getDureeEnMois());
            etape.setDateDeLEvaluation(updatedEtape.getDateDeLEvaluation());
            etape.setNoteDeLEvaluation(updatedEtape.getNoteDeLEvaluation());
            etape.setValidationDuCandidatDansLaPoste(updatedEtape.getValidationDuCandidatDansLaPoste());
            etape.setCommentaire(updatedEtape.getCommentaire());
            etape.setDureeDeReponse(updatedEtape.getDureeDeReponse());
            etape.setNbEntretients(updatedEtape.getNbEntretients());
            EtapeRecrutement savedEtape = etapeRepository.save(etape);
            response.setState("success");
            response.setMessage("Etape updated successfully");
            response.setData(savedEtape);
        } else {
            response.setState("failed");
            response.setMessage("Etape not found");
            response.setData(null);
        }
        return response;
    }

    public EtapeDTO<List<EtapeRecrutement>> getAllEtapes() {
        EtapeDTO<List<EtapeRecrutement>> response = new EtapeDTO<>();
        try {
            List<EtapeRecrutement> etapes = etapeRepository.findAll();
            response.setState("success");
            response.setMessage("Etapes retrieved successfully");
            response.setData(etapes);
        } catch (Exception e) {
            response.setState("failed");
            response.setMessage(e.getMessage());
            response.setData(null);
        }
        return response;
    }

    public EtapeDTO<EtapeRecrutement> getEtapeById(Long id) {
        EtapeDTO<EtapeRecrutement> response = new EtapeDTO<>();
        Optional<EtapeRecrutement> optionalEtape = etapeRepository.findById(id);

        if (optionalEtape.isPresent()) {
            response.setState("success");
            response.setMessage("Etape retrieved successfully");
            response.setData(optionalEtape.get()); // Ici, on renvoie directement l'objet au lieu de le convertir en liste
        } else {
            response.setState("failed");
            response.setMessage("Etape not found");
            response.setData(null);
        }
        return response;
    }


    public EtapeDTO<Void> deleteEtape(Long id) {
        EtapeDTO<Void> response = new EtapeDTO<>();
        if (etapeRepository.existsById(id)) {
            etapeRepository.deleteById(id);
            response.setState("success");
            response.setMessage("Etape deleted successfully");
            response.setData(null);
        } else {
            response.setState("failed");
            response.setMessage("Etape not found");
            response.setData(null);
        }
        return response;
    }

    public EtapeListDTO getEtapesByDemandeId(Long demandeId) {
        EtapeListDTO response = new EtapeListDTO();
        Optional<Demande> optionalDemande = demandeRepository.findById(demandeId);

        if (optionalDemande.isPresent()) {
            List<EtapeRecrutement> etapes = etapeRepository.findByDemande(optionalDemande.get());
            response.setState("success");
            response.setMessage("Etapes retrieved successfully");
            response.setData(etapes);
        } else {
            response.setState("failed");
            response.setMessage("Demande not found");
            response.setData(null);
        }
        return response;
    }
}

