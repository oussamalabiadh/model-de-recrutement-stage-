package com.example.recrutement.service;

import com.example.recrutement.entity.FicheEvaluation;
import com.example.recrutement.entity.Notification;
import com.example.recrutement.entity.NotificationEvalution;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})

@Service
public class EmailNotificationService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Autowired
    private NotificationService notificationService;

    public EmailNotificationService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendValidationNotification(String validateurEmail, Long idDemande, String decision, String commentaire, String numeroDemande, String nomDemandeur, String poste, Date dateDemande, Date dateDeValidation) {
        String subject = "Notification de Validation de Demande #" + idDemande;
        String formattedDateDemande = formatDate(dateDemande);
        String formattedDateValidation = formatDate(dateDeValidation);

        String body = "<html><body style='font-family: Arial, sans-serif;'>"
                + "<h2 style='color: #2E86C1; text-align: center;'>Notification de Validation de Demande</h2>"
                + "<p>Bonjour,</p>"
                + "<p>Le validateur avec l'email <strong>" + validateurEmail + "</strong> a soumis les données pour la demande <strong>#" + numeroDemande + "</strong>.</p>"
                + "<h3 style='color: #2E4053;'>Détails de la demande :</h3>"
                + "<table style='border-collapse: collapse; width: 100%; border: 1px solid #ddd; margin-bottom: 20px;'>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Numéro de demande :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + numeroDemande + "</td></tr>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Nom du demandeur :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + nomDemandeur + "</td></tr>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Poste :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + poste + "</td></tr>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Date de la demande :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + formattedDateDemande + "</td></tr>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Date de validation :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + formattedDateValidation + "</td></tr>"
                + "</table>"
                + "<h3 style='color: #2E4053;'>Décision de validation :</h3>"
                + "<p><strong>" + decision + "</strong></p>"
                + "<h3 style='color: #2E4053;'>Commentaire :</h3>"
                + "<p>" + commentaire + "</p>"
                + "<p style='text-align: center; margin-top: 20px;'>Cordialement,</p>"
                + "<p style='text-align: center;'>Votre Application</p>"
                + "</body></html>";

        sendEmail( validateurEmail,senderEmail, subject, body);

        Notification notification = new Notification("Notification de Validation de Demande #\" "+ idDemande,idDemande, validateurEmail, decision, commentaire, numeroDemande, nomDemandeur, poste, dateDemande, dateDeValidation);
        notificationService.saveNotificationDemande(notification);
    }
    public void envoyerNotificationEvaluation(String emailEvaluateur, FicheEvaluation ficheEvaluation, byte[] pdf) {

        // Créer et enregistrer la notification d'évaluation
        NotificationEvalution notificationEvalution = new NotificationEvalution(
                "Notification de Evaluation de Demande # " + ficheEvaluation.getDemande().getNumeroDeDemande().toString(),
                pdf,
                ficheEvaluation.getDateDeEvaluation(),
                ficheEvaluation.getNoteGlobale(),
                ficheEvaluation.getCandidat().getNom(),
                ficheEvaluation.getCandidat().getPrenom(),
                ficheEvaluation.getDemande().getNumeroDeDemande().toString()
        );

        notificationService.saveNotificationEvalution(notificationEvalution);

        // Récupérer l'ID de la notification
        Long notificationId = notificationEvalution.getId();
        String subject = "Notification d'Évaluation de la demande #" + ficheEvaluation.getDemande().getNumeroDeDemande();
        String formattedDateEvaluation = formatDate(ficheEvaluation.getDateDeEvaluation());

        String pdfUrl = "http://localhost:8080/api/evaluations/" +notificationId+ "/pdf";

        String body = "<html><body style='font-family: Arial, sans-serif;'>"
                + "<h2 style='color: #2E86C1; text-align: center;'>Notification d'Évaluation</h2>"
                + "<p>Bonjour,</p>"
                + "<p>L'évaluateur avec l'email <strong>" + emailEvaluateur + "</strong> a soumis les données pour la demande <strong>#" + ficheEvaluation.getDemande().getNumeroDeDemande() + "</strong>.</p>"
                + "<h3 style='color: #2E4053;'>Détails de l'évaluation :</h3>"
                + "<table style='border-collapse: collapse; width: 100%; border: 1px solid #ddd; margin-bottom: 20px;'>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Numéro de demande :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + ficheEvaluation.getDemande().getNumeroDeDemande() + "</td></tr>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Poste de demande :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + ficheEvaluation.getDemande().getPoste() + "</td></tr>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Candidat :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + ficheEvaluation.getCandidat().getNom() + " " + ficheEvaluation.getCandidat().getPrenom() + "</td></tr>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Date de l'évaluation :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + formattedDateEvaluation + "</td></tr>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>Score :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'>" + ficheEvaluation.getNoteGlobale() + "</td></tr>"
                + "  <tr><td style='border: 1px solid #ddd; padding: 8px;'><strong>PDF :</strong></td><td style='border: 1px solid #ddd; padding: 8px;'><a href='" + pdfUrl + "' download>Télécharger le PDF</a></td></tr>"
                + "</table>"
                + "<p style='text-align: center; margin-top: 20px;'>Cordialement,</p>"
                + "<p style='text-align: center;'>Votre Application</p>"
                + "</body></html>";


        sendEmail(emailEvaluateur,senderEmail,  subject, body);
    }
    private void sendEmail(String fromEmail, String toEmail, String subject, String body) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true); // true indicates HTML content

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
