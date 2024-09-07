package com.example.recrutement.dto;

import jakarta.persistence.Lob;

public class CandidatRequestDTO {
    private String nom;
    private String prenom;
    private int age;
    private String sex;
    private String region;
    private String etude;
    private String diplome;
    private String experience;
    @Lob
    private byte[] cv; // Stockage du fichier PDF en binaire

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }    private String telephone;
    private String email;
    private String commentaire ;
    private String recommendationsDePoste;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEtude() {
        return etude;
    }

    public void setEtude(String etude) {
        this.etude = etude;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }



    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecommendationsDePoste() {
        return recommendationsDePoste;
    }

    public void setRecommendationsDePoste(String recommendationsDePoste) {
        this.recommendationsDePoste = recommendationsDePoste;
    }
}
