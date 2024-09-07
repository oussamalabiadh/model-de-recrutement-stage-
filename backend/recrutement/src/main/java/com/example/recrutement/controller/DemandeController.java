package com.example.recrutement.controller;

import com.example.recrutement.dto.*;
import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.Demande;
import com.example.recrutement.entity.notificationDemande;
import com.example.recrutement.repository.DemandeRepository;
import com.example.recrutement.service.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"*"})

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailNotificationService emailNotificationService;
    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private CandidatService candidatService;
    @Autowired
    private NotificationService notificationService;

    public String emailValidateur ;
    // Endpoint pour récupérer toutes les demandes
    @GetMapping
    public ResponseEntity<ResponseDemandeDTO> getAllDemande() {
        List<Demande> demandes = demandeRepository.findAll();
        if (!demandes.isEmpty()) {
            ResponseDemandeDTO response;
            response = new ResponseDemandeDTO("success", "demandes retrieved successfully", demandes);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseDemandeDTO response = new ResponseDemandeDTO("failed", "No demandes found",  null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour récupérer une demande par son ID
    @GetMapping("/{id}")
    public Demande getDemandeById(@PathVariable Long id) {
        return demandeService.getDemandeById(id);
    }

    // Endpoint pour créer une nouvelle demande
    private static final Logger LOGGER = Logger.getLogger(DemandeController.class.getName());

    @PostMapping
    public ResponseEntity<ResponseGeneraleDTO> createDemande(@RequestBody demandeRequestDTO demandeRequest) {
        try {
            // Vérification des données
            if (demandeRequest.getPoste() == null || demandeRequest.getCoderFf() == null || demandeRequest.getNbDePersonnes() == null ||
                    demandeRequest.getAgeDemandeeMin() == null || demandeRequest.getAgeDemandeeMax() == null || demandeRequest.getNiveauDInstructionMin() == null ||
                    demandeRequest.getNiveauDInstructionMax() == null || demandeRequest.getDiplome() == null || demandeRequest.getExperienceRequise() == null ||
                    demandeRequest.getMotifDeRecrutement() == null || demandeRequest.getDemandeur() == null || demandeRequest.getAutresConditions() == null ||
                    demandeRequest.getCategory() == null || demandeRequest.getFonctionAOcuper() == null || demandeRequest.getSociete() == null ||
                    demandeRequest.getZoneUniteMachine() == null || demandeRequest.getCentreDeGestion() == null || demandeRequest.getNiveauDEtude() == null ||
                    demandeRequest.getInterneOuExterne() == null) {

                ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Missing data in DemandeRequestDTO");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Convertir DemandeRequestDTO en Demande
            Demande demande = new Demande();
            demande.setPoste(demandeRequest.getPoste());
            demande.setCoderFf(demandeRequest.getCoderFf());
            demande.setNbDePersonnes(demandeRequest.getNbDePersonnes());
            demande.setAgeDemandeeMin(demandeRequest.getAgeDemandeeMin());
            demande.setAgeDemandeeMax(demandeRequest.getAgeDemandeeMax());
            demande.setNiveauDInstructionMin(demandeRequest.getNiveauDInstructionMin());
            demande.setNiveauDInstructionMax(demandeRequest.getNiveauDInstructionMax());
            demande.setDiplome(demandeRequest.getDiplome());
            demande.setExperienceRequise(demandeRequest.getExperienceRequise());
            demande.setMotifDeRecrutement(demandeRequest.getMotifDeRecrutement());
            demande.setDemandeur(demandeRequest.getDemandeur());
            demande.setAutresConditions(demandeRequest.getAutresConditions());
            demande.setCategory(demandeRequest.getCategory());
            demande.setFonctionAOcuper(demandeRequest.getFonctionAOcuper());
            demande.setSociete(demandeRequest.getSociete());
            demande.setZoneUniteMachine(demandeRequest.getZoneUniteMachine());
            demande.setCentreDeGestion(demandeRequest.getCentreDeGestion());
            demande.setNiveauDEtude(demandeRequest.getNiveauDEtude());
            demande.setInterneOuExterne(demandeRequest.getInterneOuExterne());
            demande.setDateDeLaDemande(new Date());

            // Sauvegarder la demande
            Demande savedDemande = demandeRepository.save(demande);
            notificationDemande nDemande = new notificationDemande("une demande a eté ajouter ",savedDemande.getDemandeur(),savedDemande.getPoste(),savedDemande.getDateDeLaDemande() );
            notificationService.saveNotificationDemande(nDemande);

            ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "Demande created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de la création de la demande : " + e.getMessage());
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "Failed to create demande");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Endpoint pour mettre à jour une demande existante par son ID
    @PutMapping("/{id}")
    public Demande updateDemande(@PathVariable Long id, @RequestBody Demande demande) {
        demande.setNumeroDeDemande(id);
        return demandeService.updateDemande(demande);
    }

    // Endpoint pour supprimer une demande par son ID
    @DeleteMapping("/{id}")
    public void deleteDemande(@PathVariable Long id) {
        demandeService.deleteDemande(id);
    }

    // Endpoint pour envoyer une demande de validation par email au validateur
    @GetMapping("/{id}/envoyer-validation")
    public ResponseGeneraleDTO envoyerValidationAuValidateur(@PathVariable Long id, @RequestParam String emailValidateur) throws MessagingException {
        // Récupérer la demande par son ID
        Demande demande = demandeService.getDemandeById(id);

        if (demande == null) {
            return new ResponseGeneraleDTO("failed","Email de validation n'est pas envoyé ");
        }
         this.emailValidateur = emailValidateur;
        // Envoyer les détails de la demande au validateur par email
        emailService.envoyerEmailValidation(emailValidateur, demande);

        return new ResponseGeneraleDTO("success","Email de validation envoyé avec succès au validateur.");
    }

    // Endpoint pour traiter la réponse de validation du validateur
    @PostMapping("/{id}/traiter-reponse-validation")
    public String traiterReponseValidation(
            @PathVariable Long id,
            @RequestParam("validation") String validationStr,
            @RequestParam("commentaire") String commentaire
    ) {
        // Convertir la valeur de validation en Boolean
        Boolean decision = validationStr.equalsIgnoreCase("oui");

        // Récupérer d'autres détails de la demande si nécessaire à partir de la base de données
        Demande demande = demandeService.getDemandeById(id);
        if (demande == null) {
            return "<html><body><div style='text-align: center;'><h1 style='color: red;'>Failed</h1><p style='font-weight: bold;'>Réponse de validation n'est pas traitée</p></div></body></html>";
        }

        demande.setValidation(decision);
        demande.setCommentaireValidateur(commentaire);
        demande.setDateDeValidation(new Date());
        // Enregistrer dans la base de données
        demandeRepository.save(demande);

        // Exemple de récupération de valeurs de la demande (à adapter selon votre structure)
        String numeroDemande = demande.getNumeroDeDemande().toString();
        String nomDemandeur = demande.getDemandeur();
        String poste = demande.getPoste();
        Date dateDemande = demande.getDateDeLaDemande();
        Date dateDeValidation = demande.getDateDeValidation();

        // Envoyer la notification par email au validateur et enregistrer la notification
            emailNotificationService.sendValidationNotification(emailValidateur, id, validationStr, commentaire, numeroDemande, nomDemandeur, poste, dateDemande, dateDeValidation);

        // Répondre avec un message de confirmation
        return "<html><body><div style='text-align: center;'><h1 style='color: green;'>Success</h1><p style='font-weight: bold;'>Email de validation traitée avec succès</p></div></body></html>";
    }



    @PostMapping("/{demandeId}/ajouter-candidat")
    public ResponseEntity<ResponseDemandeDTO> ajouterCandidat(@PathVariable Long demandeId, @RequestParam Long candidatId) {
        ResponseDemandeDTO response = demandeService.ajouterCandidat(demandeId, candidatId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/filterByDate")
    public ResponseEntity<ResponseDemandeDTO> filterByDateDeLaDemande(
            @RequestParam(value = "dateDeLaDemande", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDeLaDemande) {
        List<Demande> filteredDemandes = demandeService.filterByDateDeLaDemande(dateDeLaDemande);
        return createResponse(filteredDemandes);
    }

    @GetMapping("/filterByPoste")
    public ResponseEntity<ResponseDemandeDTO> filterByPoste(@RequestParam(value = "poste", required = false) String poste) {
        List<Demande> filteredDemandes = demandeService.filterByPoste(poste);
        return createResponse(filteredDemandes);
    }

    @GetMapping("/filterByDiplome")
    public ResponseEntity<ResponseDemandeDTO> filterByDiplome(@RequestParam(value = "diplome", required = false) String diplome) {
        List<Demande> filteredDemandes = demandeService.filterByDiplome(diplome);
        return createResponse(filteredDemandes);
    }

    @GetMapping("/filterByDemandeur")
    public ResponseEntity<ResponseDemandeDTO> filterByDemandeur(@RequestParam(value = "demandeur", required = false) String demandeur) {
        List<Demande> filteredDemandes = demandeService.filterByDemandeur(demandeur);
        return createResponse(filteredDemandes);
    }

    @GetMapping("/filterByCategory")
    public ResponseEntity<ResponseDemandeDTO> filterByCategory(@RequestParam(value = "category", required = false) String category) {
        List<Demande> filteredDemandes = demandeService.filterByCategory(category);
        return createResponse(filteredDemandes);
    }

    @GetMapping("/filterByInterneOuExterne")
    public ResponseEntity<ResponseDemandeDTO> filterByInterneOuExterne(@RequestParam(value = " ", required = false) String interneOuExterne) {
        List<Demande> filteredDemandes = demandeService.filterByInterneOuExterne(interneOuExterne);
        return createResponse(filteredDemandes);
    }

    private ResponseEntity<ResponseDemandeDTO> createResponse(List<Demande> filteredDemandes) {
        if (!filteredDemandes.isEmpty()) {
            ResponseDemandeDTO response = new ResponseDemandeDTO("success", "Filtered demandes retrieved successfully", filteredDemandes);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseDemandeDTO response = new ResponseDemandeDTO("failed", "No demandes found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
