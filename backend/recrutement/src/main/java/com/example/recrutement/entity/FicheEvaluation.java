package com.example.recrutement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class FicheEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;
    private String evaluateur;
    private Date dateDeEvaluation;

    // Attributs pour les notes administratives
    private Integer noteAge ;
    private Integer noteAdressePersonnelle;
    private Integer noteNiveauEtude;
    private Integer noteSpecialiteDemandee;
    private Integer noteParcoursEducatif;
    private Integer noteAutresExigences;
    private Integer noteDisponibilite;
    private Integer noteTypeContrat;

    // Attributs pour les notes techniques
    private Integer noteExperience =0;
    private Integer noteNaturePostes =0;
    private Integer noteStages =0;
    private Integer noteFormations =0;
    private Integer noteActivitesAssociatives =0;
    private Integer noteCompetencesTechniquesSavoir =0;
    private Integer noteCompetencesTechniquesSavoirFaire =0;

    // Attributs pour les notes comportementales
    private Integer noteComportementPonctualite =0;
    private Integer noteComportementPreparationEntretien =0;
    private Integer noteComportementConnaisanceEntreprise =0;
    private Integer noteComportementExpressionPriseParole =0;
    private Integer noteComportementCapaciteCommuniquer =0;
    private Integer noteComportementConfianceEnSoi =0;
    private Integer noteComportementEcoute =0;
    private Integer noteComportementMotivation =0;

    // Attribut pour la note globale
    private Integer noteGlobale =0 ;
    private String commentaire ="" ;
    private String PoidsFort ="";
    private String PoidsFaible ="";

    // Getters et setters


    public String getEvaluateur() {
        return evaluateur;
    }

    public void setEvaluateur(String evaluateur) {
        this.evaluateur = evaluateur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getPoidsFort() {
        return PoidsFort;
    }

    public void setPoidsFort(String poidsFort) {
        PoidsFort = poidsFort;
    }

    public String getPoidsFaible() {
        return PoidsFaible;
    }

    public void setPoidsFaible(String poidsFaible) {
        PoidsFaible = poidsFaible;
    }

    public Integer getNoteAge() {
        return noteAge;
    }

    public void setNoteAge(Integer noteAge) {
        this.noteAge = noteAge;
    }

    public Date getDateDeEvaluation() {
        return dateDeEvaluation;
    }

    public void setDateDeEvaluation(Date dateDeEvaluation) {
        this.dateDeEvaluation = dateDeEvaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Integer getNoteAdressePersonnelle() {
        return noteAdressePersonnelle;
    }

    public void setNoteAdressePersonnelle(Integer noteAdressePersonnelle) {
        this.noteAdressePersonnelle = noteAdressePersonnelle;
    }



    public Integer getNoteNiveauEtude() {
        return noteNiveauEtude;
    }

    public void setNoteNiveauEtude(Integer noteNiveauEtude) {
        this.noteNiveauEtude = noteNiveauEtude;
    }

    public Integer getNoteSpecialiteDemandee() {
        return noteSpecialiteDemandee;
    }

    public void setNoteSpecialiteDemandee(Integer noteSpecialiteDemandee) {
        this.noteSpecialiteDemandee = noteSpecialiteDemandee;
    }

    public Integer getNoteParcoursEducatif() {
        return noteParcoursEducatif;
    }

    public void setNoteParcoursEducatif(Integer noteParcoursEducatif) {
        this.noteParcoursEducatif = noteParcoursEducatif;
    }

    public Integer getNoteAutresExigences() {
        return noteAutresExigences;
    }

    public void setNoteAutresExigences(Integer noteAutresExigences) {
        this.noteAutresExigences = noteAutresExigences;
    }

    public Integer getNoteDisponibilite() {
        return noteDisponibilite;
    }

    public void setNoteDisponibilite(Integer noteDisponibilite) {
        this.noteDisponibilite = noteDisponibilite;
    }

    public Integer getNoteTypeContrat() {
        return noteTypeContrat;
    }

    public void setNoteTypeContrat(Integer noteTypeContrat) {
        this.noteTypeContrat = noteTypeContrat;
    }

    public Integer getNoteExperience() {
        return noteExperience;
    }

    public void setNoteExperience(Integer noteExperience) {
        this.noteExperience = noteExperience;
    }

    public Integer getNoteNaturePostes() {
        return noteNaturePostes;
    }

    public void setNoteNaturePostes(Integer noteNaturePostes) {
        this.noteNaturePostes = noteNaturePostes;
    }

    public Integer getNoteStages() {
        return noteStages;
    }

    public void setNoteStages(Integer noteStages) {
        this.noteStages = noteStages;
    }

    public Integer getNoteFormations() {
        return noteFormations;
    }

    public void setNoteFormations(Integer noteFormations) {
        this.noteFormations = noteFormations;
    }

    public Integer getNoteActivitesAssociatives() {
        return noteActivitesAssociatives;
    }

    public void setNoteActivitesAssociatives(Integer noteActivitesAssociatives) {
        this.noteActivitesAssociatives = noteActivitesAssociatives;
    }

    public Integer getNoteCompetencesTechniquesSavoir() {
        return noteCompetencesTechniquesSavoir;
    }

    public void setNoteCompetencesTechniquesSavoir(Integer noteCompetencesTechniquesSavoir) {
        this.noteCompetencesTechniquesSavoir = noteCompetencesTechniquesSavoir;
    }

    public Integer getNoteCompetencesTechniquesSavoirFaire() {
        return noteCompetencesTechniquesSavoirFaire;
    }

    public void setNoteCompetencesTechniquesSavoirFaire(Integer noteCompetencesTechniquesSavoirFaire) {
        this.noteCompetencesTechniquesSavoirFaire = noteCompetencesTechniquesSavoirFaire;
    }

    public Integer getNoteComportementPonctualite() {
        return noteComportementPonctualite;
    }

    public void setNoteComportementPonctualite(Integer noteComportementPonctualite) {
        this.noteComportementPonctualite = noteComportementPonctualite;
    }

    public Integer getNoteComportementPreparationEntretien() {
        return noteComportementPreparationEntretien;
    }

    public void setNoteComportementPreparationEntretien(Integer noteComportementPreparationEntretien) {
        this.noteComportementPreparationEntretien = noteComportementPreparationEntretien;
    }

    public Integer getNoteComportementConnaisanceEntreprise() {
        return noteComportementConnaisanceEntreprise;
    }

    public void setNoteComportementConnaisanceEntreprise(Integer noteComportementConnaisanceEntreprise) {
        this.noteComportementConnaisanceEntreprise = noteComportementConnaisanceEntreprise;
    }

    public Integer getNoteComportementExpressionPriseParole() {
        return noteComportementExpressionPriseParole;
    }

    public void setNoteComportementExpressionPriseParole(Integer noteComportementExpressionPriseParole) {
        this.noteComportementExpressionPriseParole = noteComportementExpressionPriseParole;
    }

    public Integer getNoteComportementCapaciteCommuniquer() {
        return noteComportementCapaciteCommuniquer;
    }

    public void setNoteComportementCapaciteCommuniquer(Integer noteComportementCapaciteCommuniquer) {
        this.noteComportementCapaciteCommuniquer = noteComportementCapaciteCommuniquer;
    }

    public Integer getNoteComportementConfianceEnSoi() {
        return noteComportementConfianceEnSoi;
    }

    public void setNoteComportementConfianceEnSoi(Integer noteComportementConfianceEnSoi) {
        this.noteComportementConfianceEnSoi = noteComportementConfianceEnSoi;
    }

    public Integer getNoteComportementEcoute() {
        return noteComportementEcoute;
    }

    public void setNoteComportementEcoute(Integer noteComportementEcoute) {
        this.noteComportementEcoute = noteComportementEcoute;
    }

    public Integer getNoteComportementMotivation() {
        return noteComportementMotivation;
    }

    public void setNoteComportementMotivation(Integer noteComportementMotivation) {
        this.noteComportementMotivation = noteComportementMotivation;
    }

    public Integer getNoteGlobale() {
        return noteGlobale;
    }

    public void setNoteGlobale(Integer noteGlobale) {
        this.noteGlobale = noteGlobale;
    }


}
