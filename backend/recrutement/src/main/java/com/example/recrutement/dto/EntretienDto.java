package com.example.recrutement.dto;

import java.util.Date;

public class EntretienDto {
    private Long candidatId;
    private String typeEntretien;
    private Integer niveauEntretien;
    private Date dateEntretien;
    private String horaire;
    private String lieu;
    private String etat;
    private String evaluateur1;
    private String evaluateur2;
    private String evaluateur3;
    private Boolean failedOrSuccess;

    // Getters and setters


    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
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
