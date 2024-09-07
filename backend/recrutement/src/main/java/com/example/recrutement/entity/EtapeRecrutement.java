package com.example.recrutement.entity;
import jakarta.persistence.*;
import java.util.Date;
@Entity
public class EtapeRecrutement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtape;

    @ManyToOne
    @JoinColumn(name = "numero_de_demande")
    private Demande demande;
    private Boolean creationProfileDePoste;
    private Boolean diffusionDeLAnnonce;
    private Boolean triDesCvs;
    private Boolean premierEntretien;
    private Boolean communicationDeCvRetenus;
    private Boolean invitationDesCandidats;
    private Boolean realisationDesEntretiens;
    private Boolean communicationDesResultats;
    private Boolean validationDesCandidats;
    private Boolean propositionDeRecrutement;
    private Date dateDeCloture;
    private Boolean realisationDuContratAdministratif;
    private Boolean realisationDuPlan;
    private String matriculeDeRecrutement;
    private String nomDeRecrutement;
    private Date dateDeDemarrage;
    private Integer dureeEnMois;
    private Date dateDeLEvaluation;
    private Integer noteDeLEvaluation;
    private Boolean validationDuCandidatDansLaPoste;
    private String commentaire;

    private Integer tauxAvancement;
    private Integer DureeDeReponse;
    private Integer NbEntretients;

    // Getters et setters

    @PrePersist
    @PreUpdate
    private void calculateTauxAvancement() {
        int totalSteps = 14; // Total number of steps excluding tauxAvancement, DureeDeReponse, NbEntretients
        int completedSteps = 0;

        if (Boolean.TRUE.equals(creationProfileDePoste)) completedSteps++;
        if (Boolean.TRUE.equals(diffusionDeLAnnonce)) completedSteps++;
        if (Boolean.TRUE.equals(triDesCvs)) completedSteps++;
        if (Boolean.TRUE.equals(premierEntretien)) completedSteps++;
        if (Boolean.TRUE.equals(communicationDeCvRetenus)) completedSteps++;
        if (Boolean.TRUE.equals(invitationDesCandidats)) completedSteps++;
        if (Boolean.TRUE.equals(realisationDesEntretiens)) completedSteps++;
        if (Boolean.TRUE.equals(communicationDesResultats)) completedSteps++;
        if (Boolean.TRUE.equals(validationDesCandidats)) completedSteps++;
        if (Boolean.TRUE.equals(propositionDeRecrutement)) completedSteps++;
        if (Boolean.TRUE.equals(realisationDuContratAdministratif)) completedSteps++;
        if (Boolean.TRUE.equals(realisationDuPlan)) completedSteps++;
        if (dateDeCloture != null) completedSteps++;
        if (dateDeDemarrage != null) completedSteps++;

        this.tauxAvancement = (completedSteps * 100) / totalSteps;
    }

    public Integer getTauxAvancement() {
        return tauxAvancement;
    }

    public void setTauxAvancement(Integer tauxAvancement) {
        this.tauxAvancement = tauxAvancement;
    }

    public Integer getDureeDeReponse() {
        return DureeDeReponse;
    }

    public void setDureeDeReponse(Integer dureeDeReponse) {
        DureeDeReponse = dureeDeReponse;
    }

    public Integer getNbEntretients() {
        return NbEntretients;
    }

    public void setNbEntretients(Integer nbEntretients) {
        NbEntretients = nbEntretients;
    }

    public Long getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(Long idEtape) {
        this.idEtape = idEtape;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Boolean getCreationProfileDePoste() {
        return creationProfileDePoste;
    }

    public void setCreationProfileDePoste(Boolean creationProfileDePoste) {
        this.creationProfileDePoste = creationProfileDePoste;
    }

    public Boolean getDiffusionDeLAnnonce() {
        return diffusionDeLAnnonce;
    }

    public void setDiffusionDeLAnnonce(Boolean diffusionDeLAnnonce) {
        this.diffusionDeLAnnonce = diffusionDeLAnnonce;
    }

    public Boolean getTriDesCvs() {
        return triDesCvs;
    }

    public void setTriDesCvs(Boolean triDesCvs) {
        this.triDesCvs = triDesCvs;
    }

    public Boolean getPremierEntretien() {
        return premierEntretien;
    }

    public void setPremierEntretien(Boolean premierEntretien) {
        this.premierEntretien = premierEntretien;
    }

    public Boolean getCommunicationDeCvRetenus() {
        return communicationDeCvRetenus;
    }

    public void setCommunicationDeCvRetenus(Boolean communicationDeCvRetenus) {
        this.communicationDeCvRetenus = communicationDeCvRetenus;
    }

    public Boolean getInvitationDesCandidats() {
        return invitationDesCandidats;
    }

    public void setInvitationDesCandidats(Boolean invitationDesCandidats) {
        this.invitationDesCandidats = invitationDesCandidats;
    }

    public Boolean getRealisationDesEntretiens() {
        return realisationDesEntretiens;
    }

    public void setRealisationDesEntretiens(Boolean realisationDesEntretiens) {
        this.realisationDesEntretiens = realisationDesEntretiens;
    }

    public Boolean getValidationDesCandidats() {
        return validationDesCandidats;
    }

    public void setValidationDesCandidats(Boolean validationDesCandidats) {
        this.validationDesCandidats = validationDesCandidats;
    }

    public Boolean getCommunicationDesResultats() {
        return communicationDesResultats;
    }

    public void setCommunicationDesResultats(Boolean communicationDesResultats) {
        this.communicationDesResultats = communicationDesResultats;
    }

    public Boolean getPropositionDeRecrutement() {
        return propositionDeRecrutement;
    }

    public void setPropositionDeRecrutement(Boolean propositionDeRecrutement) {
        this.propositionDeRecrutement = propositionDeRecrutement;
    }

    public Date getDateDeCloture() {
        return dateDeCloture;
    }

    public void setDateDeCloture(Date dateDeCloture) {
        this.dateDeCloture = dateDeCloture;
    }

    public Boolean getRealisationDuContratAdministratif() {
        return realisationDuContratAdministratif;
    }

    public void setRealisationDuContratAdministratif(Boolean realisationDuContratAdministratif) {
        this.realisationDuContratAdministratif = realisationDuContratAdministratif;
    }

    public Boolean getRealisationDuPlan() {
        return realisationDuPlan;
    }

    public void setRealisationDuPlan(Boolean realisationDuPlan) {
        this.realisationDuPlan = realisationDuPlan;
    }

    public String getMatriculeDeRecrutement() {
        return matriculeDeRecrutement;
    }

    public void setMatriculeDeRecrutement(String matriculeDeRecrutement) {
        this.matriculeDeRecrutement = matriculeDeRecrutement;
    }

    public String getNomDeRecrutement() {
        return nomDeRecrutement;
    }

    public void setNomDeRecrutement(String nomDeRecrutement) {
        this.nomDeRecrutement = nomDeRecrutement;
    }

    public Date getDateDeDemarrage() {
        return dateDeDemarrage;
    }

    public void setDateDeDemarrage(Date dateDeDemarrage) {
        this.dateDeDemarrage = dateDeDemarrage;
    }

    public Integer getDureeEnMois() {
        return dureeEnMois;
    }

    public void setDureeEnMois(Integer dureeEnMois) {
        this.dureeEnMois = dureeEnMois;
    }

    public Date getDateDeLEvaluation() {
        return dateDeLEvaluation;
    }

    public void setDateDeLEvaluation(Date dateDeLEvaluation) {
        this.dateDeLEvaluation = dateDeLEvaluation;
    }

    public Integer getNoteDeLEvaluation() {
        return noteDeLEvaluation;
    }

    public void setNoteDeLEvaluation(Integer noteDeLEvaluation) {
        this.noteDeLEvaluation = noteDeLEvaluation;
    }

    public Boolean getValidationDuCandidatDansLaPoste() {
        return validationDuCandidatDansLaPoste;
    }

    public void setValidationDuCandidatDansLaPoste(Boolean validationDuCandidatDansLaPoste) {
        this.validationDuCandidatDansLaPoste = validationDuCandidatDansLaPoste;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
