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
public class notificationCv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     private String title ;
    private String nomCandidat ;
    private String prenomCandidat ;
    private Date datePostule;


   public notificationCv(String title, String nomCandidat, String prenomCandidat, Date datePostule) {
        this.title = title;
        this.nomCandidat = nomCandidat;
        this.prenomCandidat = prenomCandidat;
        this.datePostule = datePostule;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNomCandidat() {
        return nomCandidat;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    public String getPrenomCandidat() {
        return prenomCandidat;
    }

    public void setPrenomCandidat(String prenomCandidat) {
        this.prenomCandidat = prenomCandidat;
    }

    public Date getDatePostule() {
        return datePostule;
    }

    public void setDatePostule(Date datePostule) {
        this.datePostule = datePostule;
    }
}
