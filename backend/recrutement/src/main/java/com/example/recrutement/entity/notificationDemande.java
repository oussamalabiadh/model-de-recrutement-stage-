package com.example.recrutement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class notificationDemande {
    private String title ;
    private String nomDemandeur ;

    private String poste ;
    private Date dateDemande;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    public notificationDemande(String title, String nomDemandeur, String poste, Date dateDemande) {
        this.title = title;
        this.nomDemandeur = nomDemandeur;
        this.poste = poste;
        this.dateDemande = dateDemande;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }



}
