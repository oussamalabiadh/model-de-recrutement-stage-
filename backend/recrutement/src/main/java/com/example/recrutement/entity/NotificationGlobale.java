package com.example.recrutement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NotificationGlobale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // Peut être "Validation" ou "Evaluation"

    @OneToOne
    private Notification validation; // Référence à la notification de validation

    @OneToOne
    private NotificationEvalution evaluation; // Référence à la notification d'évaluation

    @OneToOne
    private  notificationCv cv;
    @OneToOne
    private notificationDemande notificationDemande;

    // Constructeur, getters et setters

  private boolean vue = false ;
    public notificationCv getCv() {
        return cv;
    }

    public void setCv(notificationCv cv) {
        this.cv = cv;
    }

    public com.example.recrutement.entity.notificationDemande getNotificationDemande() {
        return notificationDemande;
    }

    public void setNotificationDemande(com.example.recrutement.entity.notificationDemande notificationDemande) {
        this.notificationDemande = notificationDemande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Notification getValidation() {
        return validation;
    }

    public void setValidation(Notification validation) {
        this.validation = validation;
    }

    public boolean isVue() {
        return vue;
    }

    public void setVue(boolean vue) {
        this.vue = vue;
    }

    public NotificationEvalution getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(NotificationEvalution evaluation) {
        this.evaluation = evaluation;
    }
}
