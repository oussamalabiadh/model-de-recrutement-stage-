package com.example.recrutement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long candidatId;
    private Date dateDePostule;
    private String candidatNom;
    private String candidatPrenom;
    private Integer age;
    private String adresse;
    private String diplome;
    private String niveauDetude;
    private String specialite;
    private String experience;
    private String telephone;
    private String email;
    private String typeEntretien;
    private Integer niveauEntretien;
    private Date dateEntretien;
    private String horaire;
    private String lieu;
    private String etat;
    private String evaluateur1;
    private String evaluateur2;
    private String evaluateur3;
    private boolean failedOrSuccess =false;
    private boolean failedOrSuccessEntretient =false;
    // Getters and setters


    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getCandidatNom() {
        return candidatNom;
    }

    public void setCandidatNom(String candidatNom) {
        this.candidatNom = candidatNom;
    }

    public String getCandidatPrenom() {
        return candidatPrenom;
    }

    public void setCandidatPrenom(String candidatPrenom) {
        this.candidatPrenom = candidatPrenom;
    }


    public boolean isFailedOrSuccessEntretient() {
        return failedOrSuccessEntretient;
    }

    public void setFailedOrSuccessEntretient(boolean failedOrSuccessEntretient) {
        this.failedOrSuccessEntretient = failedOrSuccessEntretient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public Date getDateDePostule() {
        return dateDePostule;
    }

    public void setDateDePostule(Date dateDePostule) {
        this.dateDePostule = dateDePostule;
    }




    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNiveauDetude() {
        return niveauDetude;
    }

    public void setNiveauDetude(String niveauDetude) {
        this.niveauDetude = niveauDetude;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
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

    public String getTypeEntretien() {
        return typeEntretien;
    }

    public void setTypeEntretien(String typeEntretien) {
        this.typeEntretien = typeEntretien;
    }

    public Integer getNiveauEntretien() {
        return niveauEntretien;
    }

    public void setNiveauEntretien(Integer niveauEntretien) {
        this.niveauEntretien = niveauEntretien;
    }

    public Date getDateEntretien() {
        return dateEntretien;
    }

    public void setDateEntretien(Date dateEntretien) {
        this.dateEntretien = dateEntretien;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEvaluateur1() {
        return evaluateur1;
    }

    public void setEvaluateur1(String evaluateur1) {
        this.evaluateur1 = evaluateur1;
    }

    public String getEvaluateur2() {
        return evaluateur2;
    }

    public void setEvaluateur2(String evaluateur2) {
        this.evaluateur2 = evaluateur2;
    }

    public String getEvaluateur3() {
        return evaluateur3;
    }

    public void setEvaluateur3(String evaluateur3) {
        this.evaluateur3 = evaluateur3;
    }

    public Boolean getFailedOrSuccess() {
        return failedOrSuccess;
    }

    public void setFailedOrSuccess(Boolean failedOrSuccess) {
        this.failedOrSuccess = failedOrSuccess;
    }
}
