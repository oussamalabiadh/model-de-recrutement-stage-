package com.example.recrutement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private Integer age;
    private String sex;
    private String region;
    private String etude;
    private String diplome;
    private String experience;
    private String telephone;
    private String email;
    private String recommendationsDePoste;
    private String commentaire ;
    private Date dateDePostule;
    private Boolean entretienTelephonique;


    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public Date getDateDePostule() {
        return dateDePostule;
    }

    public void setDateDePostule(Date dateDePostule) {
        this.dateDePostule = dateDePostule;
    }

    public Boolean getEntretienTelephonique() {
        return entretienTelephonique;
    }

    public void setEntretienTelephonique(Boolean entretienTelephonique) {
        this.entretienTelephonique = entretienTelephonique;
    }
    @ManyToOne
    @JoinColumn(name = "demande_id")
    @JsonBackReference
    private Demande demande;

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }
}

