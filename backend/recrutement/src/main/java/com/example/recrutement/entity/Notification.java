package com.example.recrutement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Notification {
    private String title ;
    private Long idDemande;
    private String validateurEmail;
    private String decision;
    private String commentaire;
    private String numeroDemande;
    private String nomDemandeur;
    private String poste;
    private Date dateDemande;
    private Date dateDeValidation;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Constructeur, getters et setters
    public Notification(String s, Long idDemande, String validateurEmail, String decision, String commentaire, String numeroDemande, String nomDemandeur, String poste, Date dateDemande, Date dateDeValidation) {
        this.idDemande = idDemande;
        this.validateurEmail = validateurEmail;
        this.decision = decision;
        this.commentaire = commentaire;
        this.numeroDemande = numeroDemande;
        this.nomDemandeur = nomDemandeur;
        this.poste = poste;
        this.dateDemande = dateDemande;
        this.dateDeValidation =dateDeValidation;
    }

    public Notification() {

    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = idDemande;
    }

    public String getValidateurEmail() {
        return validateurEmail;
    }

    public void setValidateurEmail(String validateurEmail) {
        this.validateurEmail = validateurEmail;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNumeroDemande() {
        return numeroDemande;
    }

    public void setNumeroDemande(String numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public String getNomDemandeur() {
        return nomDemandeur;
    }

    public void setNomDemandeur(String nomDemandeur) {
        this.nomDemandeur = nomDemandeur;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Date getDateDeValidation() {
        return dateDeValidation;
    }

    public void setDateDeValidation(Date dateDeValidation) {
        this.dateDeValidation = dateDeValidation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
