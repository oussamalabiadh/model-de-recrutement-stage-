package com.example.recrutement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NotificationEvalution {
    private String title ;
   private String nomCandidat;
    private String prenomCandidat;

    private String numeroDemande;

    private Integer noteGlobal;
    private Date dateEvalution ;
    @Length(max = 10485760, message = "PDF size must not exceed 10MB")
    private byte[] pdf;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public NotificationEvalution(String title, byte[] pdf, Date dateEvalution, Integer noteGlobal, String numeroDemande ,String nomCandidat, String prenomCandidat) {
        this.title = title;
        this.nomCandidat = nomCandidat;
        this.prenomCandidat = prenomCandidat;
        this.noteGlobal = noteGlobal;
        this.dateEvalution = dateEvalution;
        this.pdf = pdf;
        this.numeroDemande=numeroDemande;
    }

    public Date getDateEvalution() {
        return dateEvalution;
    }

    public void setDateEvalution(Date dateEvalution) {
        this.dateEvalution = dateEvalution;
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

    public String getNumeroDemande() {
        return numeroDemande;
    }

    public void setNumeroDemande(String numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public Integer getNoteGlobal() {
        return noteGlobal;
    }

    public void setNoteGlobal(Integer noteGlobal) {
        this.noteGlobal = noteGlobal;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
