package com.example.recrutement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroDeDemande;

    private Date dateDeLaDemande;
    private String poste;
    private String coderFf;
    private Integer nbDePersonnes;
    private Integer ageDemandeeMin;
    private Integer ageDemandeeMax;
    private String niveauDInstructionMin;
    private String niveauDInstructionMax;
    private String diplome;
    private Integer experienceRequise;
    private String motifDeRecrutement;
    private String demandeur;
    private String autresConditions;
    private String category;
    private String fonctionAOcuper;
    private String societe;
    private String zoneUniteMachine;
    private String centreDeGestion;
    private String niveauDEtude;
    private String interneOuExterne;
    private String etat = "non valide";
    private Boolean validation;
    private Date dateDeValidation;
    private String commentaireValidateur = "";

    @OneToOne
    @JoinColumn(name = "candidat_id")
    @JsonBackReference
    private Candidat candidatId;

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidat> candidats = new ArrayList<>();

    // Getters et setters

    public Long getNumeroDeDemande() {
        return numeroDeDemande;
    }

    public void setNumeroDeDemande(Long numeroDeDemande) {
        this.numeroDeDemande = numeroDeDemande;
    }

    public Date getDateDeLaDemande() {
        return dateDeLaDemande;
    }

    public void setDateDeLaDemande(Date dateDeLaDemande) {
        this.dateDeLaDemande = dateDeLaDemande;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getCoderFf() {
        return coderFf;
    }

    public void setCoderFf(String coderFf) {
        this.coderFf = coderFf;
    }

    public Integer getNbDePersonnes() {
        return nbDePersonnes;
    }

    public void setNbDePersonnes(Integer nbDePersonnes) {
        this.nbDePersonnes = nbDePersonnes;
    }

    public Integer getAgeDemandeeMin() {
        return ageDemandeeMin;
    }

    public void setAgeDemandeeMin(Integer ageDemandeeMin) {
        this.ageDemandeeMin = ageDemandeeMin;
    }

    public Integer getAgeDemandeeMax() {
        return ageDemandeeMax;
    }

    public void setAgeDemandeeMax(Integer ageDemandeeMax) {
        this.ageDemandeeMax = ageDemandeeMax;
    }

    public String getNiveauDInstructionMin() {
        return niveauDInstructionMin;
    }

    public void setNiveauDInstructionMin(String niveauDInstructionMin) {
        this.niveauDInstructionMin = niveauDInstructionMin;
    }

    public String getNiveauDInstructionMax() {
        return niveauDInstructionMax;
    }

    public void setNiveauDInstructionMax(String niveauDInstructionMax) {
        this.niveauDInstructionMax = niveauDInstructionMax;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public Integer getExperienceRequise() {
        return experienceRequise;
    }

    public void setExperienceRequise(Integer experienceRequise) {
        this.experienceRequise = experienceRequise;
    }

    public String getMotifDeRecrutement() {
        return motifDeRecrutement;
    }

    public void setMotifDeRecrutement(String motifDeRecrutement) {
        this.motifDeRecrutement = motifDeRecrutement;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getAutresConditions() {
        return autresConditions;
    }

    public void setAutresConditions(String autresConditions) {
        this.autresConditions = autresConditions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFonctionAOcuper() {
        return fonctionAOcuper;
    }

    public void setFonctionAOcuper(String fonctionAOcuper) {
        this.fonctionAOcuper = fonctionAOcuper;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getZoneUniteMachine() {
        return zoneUniteMachine;
    }

    public void setZoneUniteMachine(String zoneUniteMachine) {
        this.zoneUniteMachine = zoneUniteMachine;
    }

    public String getCentreDeGestion() {
        return centreDeGestion;
    }

    public void setCentreDeGestion(String centreDeGestion) {
        this.centreDeGestion = centreDeGestion;
    }

    public String getNiveauDEtude() {
        return niveauDEtude;
    }

    public void setNiveauDEtude(String niveauDEtude) {
        this.niveauDEtude = niveauDEtude;
    }

    public String getInterneOuExterne() {
        return interneOuExterne;
    }

    public void setInterneOuExterne(String interneOuExterne) {
        this.interneOuExterne = interneOuExterne;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Boolean getValidation() {
        return validation;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    public Date getDateDeValidation() {
        return dateDeValidation;
    }

    public void setDateDeValidation(Date dateDeValidation) {
        this.dateDeValidation = dateDeValidation;
    }

    public String getCommentaireValidateur() {
        return commentaireValidateur;
    }

    public void setCommentaireValidateur(String commentaireValidateur) {
        this.commentaireValidateur = commentaireValidateur;
    }

    public Candidat getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Candidat candidatId) {
        this.candidatId = candidatId;
        if (candidatId != null) {
            this.etat = "cloture";
        }
    }

    public List<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(List<Candidat> candidats) {
        this.candidats = candidats;
    }


    // Méthode pour ajouter un candidat à la liste



}
