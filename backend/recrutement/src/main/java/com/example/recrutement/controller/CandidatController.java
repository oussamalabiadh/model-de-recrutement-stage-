package com.example.recrutement.controller;

import com.example.recrutement.dto.CandidatRequestDTO;
import com.example.recrutement.dto.ResponseDataDTO;
import com.example.recrutement.dto.ResponseGeneraleDTO;
import com.example.recrutement.entity.*;
import com.example.recrutement.repository.CandidatRepository;
import com.example.recrutement.repository.CvDataRepository;
import com.example.recrutement.repository.EntretienRepository;
import com.example.recrutement.service.CandidatService;
import com.example.recrutement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/candidats")
public class CandidatController {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private CvDataRepository cvDataRepository;

    @Autowired
    private EntretienRepository entretienRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private CandidatService candidatService;
    private static final Logger LOGGER = Logger.getLogger(DemandeController.class.getName());

    @GetMapping
    public ResponseEntity<ResponseDataDTO> getAllCandidats() {
        List<Candidat> candidats = candidatRepository.findAll();
        if (!candidats.isEmpty()) {
            ResponseDataDTO response;
            response = new ResponseDataDTO("success", "Candidats retrieved successfully", candidats);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseDataDTO response = new ResponseDataDTO("failed", "No candidats found",  null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseGeneraleDTO> createCandidat(@RequestBody CandidatRequestDTO candidatRequest) {
        try {
            Candidat candidat = new Candidat();
            candidat.setNom(candidatRequest.getNom());
            candidat.setPrenom(candidatRequest.getPrenom());
            candidat.setAge(candidatRequest.getAge());
            candidat.setSex(candidatRequest.getSex());
            candidat.setRegion(candidatRequest.getRegion());
            candidat.setEtude(candidatRequest.getEtude());
            candidat.setDiplome(candidatRequest.getDiplome());
            candidat.setExperience(candidatRequest.getExperience());
            candidat.setTelephone(candidatRequest.getTelephone());
            candidat.setEmail(candidatRequest.getEmail());
            candidat.setRecommendationsDePoste(candidatRequest.getRecommendationsDePoste());
            candidat.setCommentaire(candidatRequest.getCommentaire());
            candidat.setDateDePostule(new Date());
            candidat.setEntretienTelephonique(false);

            // Vérification des données
            if ((candidatRequest.getNom() == null) || (candidatRequest.getPrenom() == null) || Objects.isNull(candidatRequest.getAge())  ||
                    (candidatRequest.getSex() == null) || (candidatRequest.getRegion() == null) || (candidatRequest.getEtude() == null) ||
                    (candidatRequest.getDiplome() == null) || (candidatRequest.getExperience() == null) || (candidatRequest.getCv() == null)|| (candidatRequest.getRecommendationsDePoste()== null) ) {
                ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Missing data in CandidatRequestDTO");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            Candidat savedCandidat = candidatRepository.save(candidat);

            CvData cvData = new CvData();
            cvData.setCv((candidatRequest.getCv()));
            cvData.setCandidat(savedCandidat);
            notificationCv cv= new notificationCv("un candidat Postule ",savedCandidat.getNom(),savedCandidat.getPrenom(),savedCandidat.getDateDePostule());
            cvDataRepository.save(cvData);
            notificationService.saveNotificationCv(cv);
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "Candidat created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Failed to create candidat");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public Candidat getCandidatById(@PathVariable Long id) {
        return candidatService.getCandidatById(id);
    }
    @GetMapping("/{candidatId}/cv")
    public ResponseEntity<byte[]> downloadCv(@PathVariable Long candidatId) {
        CvData cvData = cvDataRepository.findByCandidatId(candidatId);
        if (cvData != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv-" + candidatId + ".pdf")
                    .body(cvData.getCv());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint pour créer une nouvelle demande
    @PutMapping("/{id}/entretien-ok")
    public ResponseEntity<ResponseGeneraleDTO> markEntretienAsOk(@PathVariable Long id) {
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        if (optionalCandidat.isPresent()) {
            Candidat candidat = optionalCandidat.get();
            candidat.setEntretienTelephonique(true);
            candidatRepository.save(candidat);

            Entretien entretien = new Entretien();
            entretien.setDateDePostule(new Date());
            entretien.setCandidatNom(candidat.getNom());
            entretien.setCandidatPrenom(candidat.getPrenom());
            entretien.setCandidatId(candidat.getId());
            entretien.setDateDePostule(candidat.getDateDePostule());
            entretien.setAdresse(candidat.getRegion());
            entretien.setAge(candidat.getAge());
            entretien.setEmail(candidat.getEmail());
            entretien.setExperience(candidat.getExperience());
            entretien.setNiveauDetude(candidat.getEtude());
            entretien.setSpecialite(candidat.getDiplome());
            entretien.setTelephone(candidat.getTelephone());
            entretienRepository.save(entretien);

            ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "Entretien marked as OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Candidat not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGeneraleDTO> deleteCandidat(@PathVariable Long id) {
        Optional<Candidat> optionalCandidat = candidatRepository.findById(id);
        if (optionalCandidat.isPresent()) {
            Candidat candidat = optionalCandidat.get();

            candidatService.deleteCandidat(candidat.getId());
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "Candidat deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Candidat not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
