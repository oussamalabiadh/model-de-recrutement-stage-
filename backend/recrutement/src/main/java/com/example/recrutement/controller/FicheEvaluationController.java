package com.example.recrutement.controller;

import com.example.recrutement.dto.CandidatEvaluationSummary;
import com.example.recrutement.dto.ResponseGeneraleDTO;
import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.FicheEvaluation;
import com.example.recrutement.repository.FicheEvaluationRepository;
import com.example.recrutement.service.EmailNotificationService;
import com.example.recrutement.service.EmailService;
import com.example.recrutement.service.FicheEvaluationService;
import com.example.recrutement.service.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})

@RestController
@RequestMapping("/api/evaluations")
public class FicheEvaluationController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private FicheEvaluationService ficheEvaluationService;

    @Autowired
    private FicheEvaluationRepository ficheEvaluationRepository;
    @Autowired
    private EmailNotificationService emailNotificationService;
    @Autowired
    private NotificationService notificationService;

    public String emailEvaluateur;

    // Endpoint pour envoyer une fiche d'évaluation par email à l'évaluateur

    @GetMapping("/{id}/envoyer-evaluateur")
    public ResponseGeneraleDTO envoyerEmailEvaluateur(@PathVariable Long id, @RequestParam String emailEvaluateur) throws MessagingException {
        // Récupérer la fiche d'évaluation par son ID
        FicheEvaluation ficheEvaluation = ficheEvaluationService.getEvaluationById(id);

        if (ficheEvaluation == null) {
            return new ResponseGeneraleDTO("failed", "Email de fiche d'évaluation n'est pas envoyé ");
        }
        this.emailEvaluateur = emailEvaluateur;
        // Envoyer les détails de la fiche d'évaluation à l'évaluateur par email
        emailService.envoyerEmailEvaluation(emailEvaluateur, ficheEvaluation);

        return new ResponseGeneraleDTO("success", "Email de fiche d'évaluation envoyé avec succès à l'évaluateur.");
    }
    @PreAuthorize("hasRole('ROLE_EVALUATOR')")

    // Endpoint pour traiter la réponse d'évaluation de l'évaluateur
    @PostMapping("/{id}/traiter-reponse-evaluation")
    public String traiterReponseEvaluation(
            @PathVariable Long id,
            @RequestParam(name = "noteAdressePersonnelle") int noteAdressePersonnelle,
            @RequestParam(name = "noteNiveauEtude") int noteNiveauEtude,
            @RequestParam(name = "noteSpecialiteDemandee") int noteSpecialiteDemandee,
            @RequestParam(name = "noteParcoursEducatif") int noteParcoursEducatif,
            @RequestParam(name = "noteAutresExigences") int noteAutresExigences,
            @RequestParam(name = "noteDisponibilite") int noteDisponibilite,
            @RequestParam(name = "noteTypeContrat") int noteTypeContrat,
            @RequestParam(name = "noteExperience") int noteExperience,
            @RequestParam(name = "noteNaturePostes") int noteNaturePostes,
            @RequestParam(name = "noteStages") int noteStages,
            @RequestParam(name = "noteFormations") int noteFormations,
            @RequestParam(name = "noteActivitesAssociatives") int noteActivitesAssociatives,
            @RequestParam(name = "noteCompetencesTechniquesSavoir") int noteCompetencesTechniquesSavoir,
            @RequestParam(name = "noteCompetencesTechniquesSavoirFaire") int noteCompetencesTechniquesSavoirFaire,
            @RequestParam(name = "noteComportementPonctualite") int noteComportementPonctualite,
            @RequestParam(name = "noteComportementPreparationEntretien") int noteComportementPreparationEntretien,
            @RequestParam(name = "noteComportementConnaissanceEntreprise") int noteComportementConnaissanceEntreprise,
            @RequestParam(name = "noteComportementExpressionPriseParole") int noteComportementExpressionPriseParole,
            @RequestParam(name = "noteComportementCapaciteCommuniquer") int noteComportementCapaciteCommuniquer,
            @RequestParam(name = "noteComportementConfianceEnSoi") int noteComportementConfianceEnSoi,
            @RequestParam(name = "noteComportementEcoute") int noteComportementEcoute,
            @RequestParam(name = "noteComportementMotivation") int noteComportementMotivation,
            @RequestParam(name="noteAge") int noteAge,
            @RequestParam(name="commentaire") String commentaire ,
            @RequestParam(name ="poidsFort") String poidsFort,
            @RequestParam(name="poidsFaible") String poidsFaible,
            @RequestParam(name="evaluateur") String evaluateur

    ) {
        // Récupérer la fiche d'évaluation par son ID
        FicheEvaluation ficheEvaluation = ficheEvaluationService.getEvaluationById(id);
        if (ficheEvaluation == null) {
            return "<html><body><div style='text-align: center;'><h1 style='color: red;'>Failed</h1><p style='font-weight: bold;'>Réponse d'évaluation n'est pas traitée</p></div></body></html>";
        }

        ficheEvaluation.setDateDeEvaluation(new Date());
        // Mettre à jour les notes administratives
        ficheEvaluation.setNoteAdressePersonnelle(noteAdressePersonnelle);
        ficheEvaluation.setNoteNiveauEtude(noteNiveauEtude);
        ficheEvaluation.setNoteSpecialiteDemandee(noteSpecialiteDemandee);
        ficheEvaluation.setNoteParcoursEducatif(noteParcoursEducatif);
        ficheEvaluation.setNoteAutresExigences(noteAutresExigences);
        ficheEvaluation.setNoteDisponibilite(noteDisponibilite);
        ficheEvaluation.setNoteTypeContrat(noteTypeContrat);
       ficheEvaluation.setEvaluateur(evaluateur);
        // Mettre à jour les notes techniques
        ficheEvaluation.setNoteExperience(noteExperience);
        ficheEvaluation.setNoteNaturePostes(noteNaturePostes);
        ficheEvaluation.setNoteStages(noteStages);
        ficheEvaluation.setNoteFormations(noteFormations);
        ficheEvaluation.setNoteActivitesAssociatives(noteActivitesAssociatives);
        ficheEvaluation.setNoteCompetencesTechniquesSavoir(noteCompetencesTechniquesSavoir);
        ficheEvaluation.setNoteCompetencesTechniquesSavoirFaire(noteCompetencesTechniquesSavoirFaire);

        // Mettre à jour les notes comportementales
        ficheEvaluation.setNoteComportementPonctualite(noteComportementPonctualite);
        ficheEvaluation.setNoteComportementPreparationEntretien(noteComportementPreparationEntretien);
        ficheEvaluation.setNoteComportementConnaisanceEntreprise(noteComportementConnaissanceEntreprise);
        ficheEvaluation.setNoteComportementExpressionPriseParole(noteComportementExpressionPriseParole);
        ficheEvaluation.setNoteComportementCapaciteCommuniquer(noteComportementCapaciteCommuniquer);
        ficheEvaluation.setNoteComportementConfianceEnSoi(noteComportementConfianceEnSoi);
        ficheEvaluation.setNoteComportementEcoute(noteComportementEcoute);
        ficheEvaluation.setNoteComportementMotivation(noteComportementMotivation);
        ficheEvaluation.setNoteGlobale(ficheEvaluationService.calculateGlobalNote(ficheEvaluation));
        ficheEvaluation.setPoidsFaible(poidsFaible);
         ficheEvaluation.setPoidsFort(poidsFort);
         ficheEvaluation.setCommentaire(commentaire);

        // Enregistrer dans la base de données
        ficheEvaluationRepository.save(ficheEvaluation);

        // Exemple de récupération de valeurs de la demande (à adapter selon votre structure)
        String numeroDemande = ficheEvaluation.getDemande().getNumeroDeDemande().toString();
        String nomCandidat = ficheEvaluation.getCandidat().getNom();
        String prenomCandidat = ficheEvaluation.getCandidat().getPrenom();
        Integer noteGlobal = ficheEvaluationService.calculateGlobalNote(ficheEvaluation);
        byte[] pdf = generatePdfFromFicheEvaluation(ficheEvaluation);


        // Envoyer la notification par email à l'évaluateur et enregistrer la notification
        emailNotificationService.envoyerNotificationEvaluation(emailEvaluateur, ficheEvaluation,pdf);

        // Répondre avec un message de confirmation
        return "<html><body><div style='text-align: center;'><h1 style='color: green;'>Success</h1><p style='font-weight: bold;'>Réponse d'évaluation traitée avec succès</p><p style='font-weight: bold; color: red;'>Note Globale est : "+noteGlobal+" </p></div></body></html>";
    }

    @PostMapping
    public ResponseGeneraleDTO saveEvaluation(@RequestParam Long candidatId, @RequestParam Long demandeId, @RequestBody FicheEvaluation evaluation) {
        return ficheEvaluationService.saveEvaluation(candidatId, demandeId, evaluation);
    }

    @GetMapping
    public List<FicheEvaluation> getAllEvaluations() {
        return ficheEvaluationService.getAllEvaluations();
    }

    @GetMapping("/{id}")
    public FicheEvaluation getEvaluationById(@PathVariable Long id) {
        return ficheEvaluationService.getEvaluationById(id);
    }

    @GetMapping("/candidat/{candidatId}")
    public List<FicheEvaluation> getEvaluationsByCandidatId(@PathVariable Long candidatId) {
        return ficheEvaluationService.getEvaluationsByCandidatId(candidatId);
    }

    @GetMapping("/CandidatEvaluationSummary/candidat/{candidatId}")
    public CandidatEvaluationSummary getEvaluationsSummaryByCandidatId(@PathVariable Long candidatId) {
        return ficheEvaluationService.getEvaluationsSummaryByCandidatId(candidatId);
    }
    @GetMapping("/candidat/{candidatId}/evaluation/{evaluationId}")
    public ResponseEntity<FicheEvaluation> getEvaluationByCandidatIdAndEvaluationId(@PathVariable Long candidatId, @PathVariable Long evaluationId) {
        FicheEvaluation evaluation = ficheEvaluationService.getEvaluationByCandidatIdAndEvaluationId(candidatId, evaluationId);
        if (evaluation != null) {
            return ResponseEntity.ok(evaluation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // or any other handling you prefer
        }
    }


    private byte[] generatePdfFromFicheEvaluation(FicheEvaluation ficheEvaluation) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                // Adding title
                contentStream.showText("Évaluation de la Demande #" + ficheEvaluation.getDemande().getNumeroDeDemande());
                contentStream.newLine();
                contentStream.newLine();

                // General information
                contentStream.showText("Candidat: " + ficheEvaluation.getCandidat().getNom() + " " + ficheEvaluation.getCandidat().getPrenom());
                contentStream.newLine();
                contentStream.showText("Date de l'évaluation: " + ficheEvaluation.getDateDeEvaluation().toString());
                contentStream.newLine();
                contentStream.showText("Poste: " + ficheEvaluation.getDemande().getPoste());
                contentStream.newLine();
                contentStream.showText("Demandeur: " + ficheEvaluation.getDemande().getDemandeur());
                contentStream.newLine();
                contentStream.showText("Score: " + ficheEvaluation.getNoteGlobale());
                contentStream.newLine();
                contentStream.newLine();

                // Administrative notes
                contentStream.showText("Notes Administratives:");
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.showText("Adresse Personnelle: " + ficheEvaluation.getNoteAdressePersonnelle());
                contentStream.newLine();
                contentStream.showText("Niveau d'Étude: " + ficheEvaluation.getNoteNiveauEtude());
                contentStream.newLine();
                

                contentStream.endText();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        byte[] pdfData = notificationService.getPdfDataById(id);
        if (pdfData == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fiche_evaluation_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGeneraleDTO> deletefiche(@PathVariable Long id) {
        Optional<FicheEvaluation> optionalFicheEvaluation = ficheEvaluationRepository.findById(id);
        if (optionalFicheEvaluation.isPresent()) {
            FicheEvaluation ficheEvaluation = optionalFicheEvaluation.get();

            ficheEvaluationService.deleteFiche(ficheEvaluation.getId());
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "ficheEvaluation deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "ficheEvaluation not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
