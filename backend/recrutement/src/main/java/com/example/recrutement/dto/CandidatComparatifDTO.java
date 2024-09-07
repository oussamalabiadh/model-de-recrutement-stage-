package com.example.recrutement.dto;


public class CandidatComparatifDTO {
    private Long candidatId;
    private String nom;
    private String prenom;
    private String diplome;
    private Integer age;
    private String sexe;
    private String poste;
    private Double noteGlobaleMoyenne;
    private int nombreDeFiches;

    // Constructors, getters and setters

    public CandidatComparatifDTO(Long candidatId, String nom, String prenom, String diplome, Integer age, String sexe, String poste, Double noteGlobaleMoyenne, int nombreDeFiches) {
        this.candidatId = candidatId;
        this.nom = nom;
        this.prenom = prenom;
        this.diplome = diplome;
        this.age = age;
        this.sexe = sexe;
        this.poste = poste;
        this.noteGlobaleMoyenne = noteGlobaleMoyenne;
        this.nombreDeFiches = nombreDeFiches;
    }

    // Getters and Setters


    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Double getNoteGlobaleMoyenne() {
        return noteGlobaleMoyenne;
    }

    public void setNoteGlobaleMoyenne(Double noteGlobaleMoyenne) {
        this.noteGlobaleMoyenne = noteGlobaleMoyenne;
    }

    public int getNombreDeFiches() {
        return nombreDeFiches;
    }

    public void setNombreDeFiches(int nombreDeFiches) {
        this.nombreDeFiches = nombreDeFiches;
    }
}
