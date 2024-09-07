package com.example.recrutement.controller;

import com.example.recrutement.dto.EntretienDto;
import com.example.recrutement.dto.FailedOrSuccessDTO;
import com.example.recrutement.dto.ResponseDTO;
import com.example.recrutement.dto.ResponseGeneraleDTO;
import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.Entretien;
import com.example.recrutement.repository.EntretienRepository;
import com.example.recrutement.service.EmailService;
import com.example.recrutement.service.EntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})

@RestController
@RequestMapping("/entretiens")
public class EntretienController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EntretienService entretienService;

    @Autowired
    private EntretienRepository entretienRepository;

   /* @GetMapping
    public ResponseEntity<ResponseDTO> getAllEntretiens() {
        try {
            List<Entretien> entretiens = entretienService.findAll();
            ResponseDTO response = new ResponseDTO("success", "Liste des entretiens récupérée avec succès", entretiens);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO response = new ResponseDTO("failed", "Impossible de récupérer la liste des entretiens", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

*/
   private static final Logger LOGGER = Logger.getLogger(DemandeController.class.getName());

    @GetMapping
   public ResponseEntity<ResponseDTO> getAllEntretiens() {
       try {
           List<Entretien> entretiens = entretienService.findAll();
           ResponseDTO response = new ResponseDTO("success", "Liste des entretiens récupérée avec succès", entretiens);
           return new ResponseEntity<>(response, HttpStatus.OK);
       } catch (Exception e) {
           // Log l'exception pour un diagnostic ultérieur
           System.out.println("Erreur lors de la récupération de la liste des entretiens"+ e);
           ResponseDTO response = new ResponseDTO("failed", "Impossible de récupérer la liste des entretiens", null);
           return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGeneraleDTO> updateEntretien(@PathVariable Long id, @RequestBody EntretienDto entretienDetails) {
        try {
            Optional<Entretien> optionalEntretien = entretienRepository.findById(id);
            if (optionalEntretien.isPresent()) {
                Entretien entretien = optionalEntretien.get();

                // Mettre à jour chaque champ de l'entretien avec les détails fournis s'ils ne sont pas nuls
                if (entretienDetails.getCandidatId() != null) {
                    entretien.setCandidatId(entretienDetails.getCandidatId());
                }
                if (entretienDetails.getTypeEntretien() != null) {
                    entretien.setTypeEntretien(entretienDetails.getTypeEntretien());
                }
                if (entretienDetails.getNiveauEntretien() != null) {
                    entretien.setNiveauEntretien(entretienDetails.getNiveauEntretien());
                }
                if (entretienDetails.getDateEntretien() != null) {
                    entretien.setDateEntretien(entretienDetails.getDateEntretien());
                }
                if (entretienDetails.getHoraire() != null) {
                    entretien.setHoraire(entretienDetails.getHoraire());
                }
                if (entretienDetails.getLieu() != null) {
                    entretien.setLieu(entretienDetails.getLieu());
                }
                if (entretienDetails.getEtat() != null) {
                    entretien.setEtat(entretienDetails.getEtat());
                }
                if (entretienDetails.getEvaluateur1() != null) {
                    entretien.setEvaluateur1(entretienDetails.getEvaluateur1());
                }
                if (entretienDetails.getEvaluateur2() != null) {
                    entretien.setEvaluateur2(entretienDetails.getEvaluateur2());
                }
                if (entretienDetails.getEvaluateur3() != null) {
                    entretien.setEvaluateur3(entretienDetails.getEvaluateur3());
                }
                if (entretienDetails.getFailedOrSuccess() != null) {
                    entretien.setFailedOrSuccess(entretienDetails.getFailedOrSuccess());
                }

                entretienRepository.save(entretien);

                ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "Entretien mis à jour avec succès");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Entretien non trouvé");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Impossible de mettre à jour l'entretien");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/notify")
    public ResponseEntity<ResponseGeneraleDTO> notifyCandidate(@PathVariable Long id, @RequestBody FailedOrSuccessDTO failedOrSuccessDTO) {
        try {
            Optional<Entretien> optionalEntretien = entretienRepository.findById(id);
            if (optionalEntretien.isPresent()) {
                Entretien entretien = optionalEntretien.get();
                String message = "Bonjour " + entretien.getCandidatNom()+ entretien.getCandidatPrenom() + ",\n\n";
                entretien.setFailedOrSuccess(failedOrSuccessDTO.getstatus());
                entretienRepository.save(entretien);

                if (entretien.getFailedOrSuccess()) {
                    message += "Nous vous informons que votre entretien est prévu pour le " + entretien.getDateEntretien() + ".\n";
                    message += "Type d'entretien : " + entretien.getTypeEntretien() + "\n";
                    message += "Horaire : " + entretien.getHoraire() + "\n";
                    message += "Lieu : " + entretien.getLieu() + "\n\n";
                    message += "Félicitations, vous avez réussi l'entretien.";
                } else {
                    message += "Nous regrettons de vous informer que vous n'avez pas réussi la selection des cv .";
                }

                // Envoyer l'email
                emailService.sendEmail(entretien.getEmail(), "selections des cv ", message);

                ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "Email envoyé avec succès");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Entretien non trouvé");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Impossible d'envoyer l'email");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{id}/succesOrfalidRecrutement")
    public ResponseEntity<ResponseGeneraleDTO> succesOrfalidRecrutement(@PathVariable Long id, @RequestBody FailedOrSuccessDTO failedOrSuccessDTO) {
        try {
            Optional<Entretien> optionalEntretien = entretienRepository.findById(id);
            if (optionalEntretien.isPresent()) {
                Entretien entretien = optionalEntretien.get();

                String message = "Bonjour " + entretien.getCandidatNom()+ entretien.getCandidatPrenom() + ",\n\n";
                entretien.setFailedOrSuccessEntretient(failedOrSuccessDTO.getstatus());
                entretienRepository.save(entretien);

                if (entretien.isFailedOrSuccessEntretient()) {
                    message += "Félicitations, vous avez réussi l'entretien.";
                } else {
                    message += "Nous regrettons de vous informer que vous n'avez pas réussi l'entretien.";
                }

                // Envoyer l'email
                emailService.sendEmail(entretien.getEmail(), "Résultat de l'entretien", message);

                ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "Email envoyé avec succès");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Entretien non trouvé");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Impossible d'envoyer l'email");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGeneraleDTO> deletEntretient(@PathVariable Long id) {
        Optional<Entretien> optionalEntretien = entretienRepository.findById(id);
        if (optionalEntretien.isPresent()) {
            Entretien entretien = optionalEntretien.get();

            entretienService.deletEntretien(entretien.getId());
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "entretien deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "entretien not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public Entretien getEntretientById(@PathVariable Long id) {
        return entretienService.getEntretientById(id);
    }

}
