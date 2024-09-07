package com.example.recrutement.service;

import com.example.recrutement.entity.Demande;
import com.example.recrutement.entity.FicheEvaluation;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.Date;
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;



    public void envoyerEmailValidation(String emailValidateur, Demande demande) throws MessagingException {
        String subject = "Validation de la demande #" + demande.getNumeroDeDemande();
        String formattedDateDemande = formatDate(demande.getDateDeLaDemande());

        String body = "<html><body style='font-family: Arial, sans-serif;'>"
                + "<h2 style='color: #2E4053; text-align: center;'>Formulaire de Validation de Demande</h2>"
                + "<p>Vous avez reçu une demande de validation pour examiner :</p>"
                + "<p><strong style='color: #212F3D;'>Poste :</strong> " + demande.getPoste() + "</p>"
                + "<p><strong style='color: #212F3D;'>Date de la demande :</strong> " + formattedDateDemande + "</p>"
                + "<p><strong style='color: #212F3D;'>Category :</strong> " + demande.getCategory() + "</p>"
                + "<p><strong style='color: #212F3D;'>Diplome :</strong> " + demande.getDiplome() + "</p>"
                + "<p><strong style='color: #212F3D;'>Niveau d'étude :</strong> " + demande.getNiveauDEtude() + "</p>"
                + "<p><strong style='color: #212F3D;'>Autres Conditions :</strong> " + demande.getAutresConditions() + "</p>"
                + "<p><strong style='color: #212F3D;'>Centre de Gestion :</strong> " + demande.getCentreDeGestion() + "</p>"
                + "<p><strong style='color: #212F3D;'>Fonction à Occuper :</strong> " + demande.getFonctionAOcuper() + "</p>"
                + "<p><strong style='color: #212F3D;'>Interne ou Externe :</strong> " + demande.getInterneOuExterne() + "</p>"
                + "<p><strong style='color: #212F3D;'>Nombre de Personnes :</strong> " + demande.getNbDePersonnes() + "</p>"
                + "<p><strong style='color: #212F3D;'>Zone Unité Machine :</strong> " + demande.getZoneUniteMachine() + "</p>"
                + "<p><strong style='color: #212F3D;'>Motif de Recrutement :</strong> " + demande.getMotifDeRecrutement() + "</p>"
                + "<p><strong style='color: #212F3D;'>Demandeur :</strong> " + demande.getDemandeur() + "</p>"
                + "<form style='margin-top: 20px;' action='http://localhost:8080/api/demandes/" + demande.getNumeroDeDemande() + "/traiter-reponse-validation' method='post'>"
                + "<input type='hidden' name='idDemande' value='" + demande.getNumeroDeDemande() + "'>"
                + "<label style='display: block; margin-top: 10px; color: #212F3D;' for='validation'>Valider :</label><br>"
                + "<input type='radio' id='validationOui' name='validation' value='oui'>"
                + "<label style='color: #212F3D;' for='validationOui'>Oui</label><br>"
                + "<input type='radio' id='validationNon' name='validation' value='non'>"
                + "<label style='color: #212F3D;' for='validationNon'>Non</label><br><br>"
                + "<label style='display: block; margin-top: 10px; color: #212F3D;' for='commentaire'>Commentaire :</label><br>"
                + "<textarea style='width: 100%; max-width: 400px;' id='commentaire' name='commentaire' rows='4' cols='50'></textarea><br><br>"
                + "<input type='submit' style='background-color: #28a745; color: white; padding: 10px 20px; border: none; cursor: pointer;' value='Soumettre'>"
                + "</form>"
                + "</body></html>";

        eee(emailValidateur, subject, body);
    }

    private void eee(String emailValidateur, String subject, String body) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        try {
            helper.setTo(emailValidateur);
            helper.setSubject(subject);
            helper.setText(body, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void envoyerEmailEvaluation(String emailEvaluateur, FicheEvaluation ficheEvaluation) throws MessagingException {
        String subject = "Évaluation du candidat pour la demande #" + ficheEvaluation.getDemande().getNumeroDeDemande();
        String formattedDateEvaluation = formatDate(ficheEvaluation.getDateDeEvaluation());

        String body = "<body style=\"font-size: 20px\">\n" +
                "    <h1 style=\"text-align: center; color: rgb(77, 117, 117)\">\n" +
                "        Fiche d'evalution de candidat\n" +
                "    </h1>\n" +
                "\n" +
                "    <form action='http://localhost:8080/api/evaluations/" + ficheEvaluation.getId() + "/traiter-reponse-evaluation'\n" +
                "        method='post'>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td>Poste ("+ficheEvaluation.getDemande().getDateDeLaDemande()+")</td>\n" +
                "                <td>"+ficheEvaluation.getDemande().getPoste()+"</td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td>Candidat("+ficheEvaluation.getCandidat().getDateDePostule()+") : </td>\n" +
                "                <td>"+ficheEvaluation.getCandidat().getNom() + ficheEvaluation.getCandidat().getPrenom() +"</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Evaluateur</td>\n" +
                "                <td>"+emailEvaluateur+"</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>votre Nom et Prenom :</td>\n" +
                "                <td><input type='text' name='evaluateur' value='"+ficheEvaluation.getEvaluateur()+"' /></td>\n" +
                "            </tr>\n" +

                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"4\" style=\"background-color: rgb(98, 171, 179)\">\n" +
                "                    Aptitude administratif\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"4\" style=\"text-align: start\">\n" +
                "                    Age maximum : ("+ficheEvaluation.getCandidat().getAge()+")\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>age du candidat</td>\n" +
                "                <td>40=<</td>\n" +
                "                </td>\n" +
                "                <td>40></td>\n" +
                "                <td>60></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>2</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"3\"><input type='text' name='noteAge' value='" + ficheEvaluation.getNoteAge() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">\n" +
                "                    Exigence adresse personnelle :\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>adresse du candidat :"+ficheEvaluation.getCandidat().getRegion()+"</td>\n" +
                "                <td>Meme adresse</td>\n" +
                "                <td>au voisinage</td>\n" +
                "                <td>Meme Gouvernorats</td>\n" +
                "                <td>Hors Gouvernorats</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>4</td>\n" +
                "                <td>3</td>\n" +
                "\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteAdressePersonnelle'\n" +
                "                        value='" + ficheEvaluation.getNoteAdressePersonnelle() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">Niveau d'etude :</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>et plus</td>\n" +
                "                <td>inferieur au niveau</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteNiveauEtude'\n" +
                "                        value='" + ficheEvaluation.getNoteNiveauEtude() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">Spécialité demandée :</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>Au cœur de la spécialité demandée</td>\n" +
                "                <td>Hors spécialité demandée</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteSpecialiteDemandee'\n" +
                "                        value='" + ficheEvaluation.getNoteSpecialiteDemandee() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">Parcour éducatif :</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>Adéquat</td>\n" +
                "                <td>Non Adéquat</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteParcoursEducatif'\n" +
                "                        value='" + ficheEvaluation.getNoteParcoursEducatif() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">Autres exigences :</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>Adéquat</td>\n" +
                "                <td>Non Adéquat</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteAutresExigences'\n" +
                "                        value='" + ficheEvaluation.getNoteAutresExigences() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">Disponibilité :</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>Immédiatement</td>\n" +
                "                <td>1 mois</td>\n" +
                "                <td>2 mois</td>\n" +
                "                <td>>3 mois</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>3</td>\n" +
                "                <td>1</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteDisponibilite'\n" +
                "                        value='" + ficheEvaluation.getNoteDisponibilite() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">\n" +
                "                    Type de contrat possible :\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>Adéquat</td>\n" +
                "                <td>Non Adéquat</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteTypeContrat'\n" +
                "                        value='" + ficheEvaluation.getNoteTypeContrat() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"4\" style=\"background-color: rgb(98, 171, 179)\">\n" +
                "                    Critére\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">\n" +
                "                    Experience :\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>(+-)>5 ans </td>\n" +
                "                <td>(+-)2-5 ans </td>\n" +
                "                <td>(+-)< 2 ans </td>\n" +
                "                <td>(+-)1 ans </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>3</td>\n" +
                "                <td>1</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteExperience'\n" +
                "                        value='" + ficheEvaluation.getNoteExperience() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">\n" +
                "                    Parcours Professionnel : Nature des poste occcupes\n" +
                "                </td>\n" +
                "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td>Critére</td>\n" +
                    "                <td>Au cœur de la spécialité demandée</td>\n" +
                    "                <td>Moyennement adéquat</td>\n" +
                "                   <td>Connaissances très basique</td>\n" +
                "                    <td>Hors spécialité demandée</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>3</td>\n" +
                "                <td>1</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteNaturePostes'\n" +
                "                        value='" + ficheEvaluation.getNoteNaturePostes() + "' /></td>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">\n" +
                "                    Stage :\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>Au cœur de la spécialité demandée</td>.\n" +
                "\n" +
                "                <td>Hors spécialité demandée</td>\n" +
                "\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>5</td>\n" +
                "                <td>0</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteStages' value='" + ficheEvaluation.getNoteStages() + "' />\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">\n" +
                "                    Formation : 0.5 pt pour chaque formation ou certification complémentaire\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>Managérial</td>\n" +
                "                <td>Linguistique</td>\n" +
                "                <td>Informatique</td>\n" +
                "                <td>Autres</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Nombre</td>\n" +
                "                <td><input type=\"number\" name=\"n1\" id=\"n1\"></td>\n" +
                "                <td><input type=\"number\" name=\"n2\" id=\"n2\"></td>\n" +
                "                <td><input type=\"number\" name=\"n3\" id=\"n3\"></td>\n" +
                "                <td><input type=\"number\" name=\"n4\" id=\"n4\"></td>\n" +
                "\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteFormations'\n" +
                "                        value='" + ficheEvaluation.getNoteFormations() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"5\" style=\"text-align: start\">\n" +
                "                    Autres Activité :0.5 pt pour chaque activité associative\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Critére</td>\n" +
                "                <td>Clubs</td>\n" +
                "                <td>Associations</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td><input type=\"number\" name=\"\" id=\"\"></td>\n" +
                "                <td><input type=\"number\" name=\"\" id=\"\"></td>\n" +
                "\n" +
                "\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"4\"><input type='text' name='noteActivitesAssociatives'\n" +
                "                        value='" + ficheEvaluation.getNoteActivitesAssociatives() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"4\" style=\"background-color: rgb(98, 171, 179)\">\n" +
                "                    Competence Techniques\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: start; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"6\" style=\"text-align: start\">\n" +
                "                    Competence Techniques : Aptitude technique par rapport aux requis de poste\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Savoir (Connaissances théoriques)</td>\n" +
                "                <td>1</td>\n" +
                "                <td>2</td>\n" +
                "                <td>3</td>\n" +
                "                <td>4</td>\n" +
                "                <td>5</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Connaître les techniques, les méthodes et les procédures de vente des produits</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r1\" id=\"r1\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r1\" id=\"r1\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r1\" id=\"r1\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r1\" id=\"r1\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r1\" id=\"r1\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Avoir des connaissances sur le comportement du consommateur.</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r2\" id=\"r2\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r2\" id=\"r2\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r2\" id=\"r2\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r2\" id=\"r2\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r2\" id=\"r2\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"5\"><input type='text' name='noteCompetencesTechniquesSavoir'\n" +
                "                        value='" + ficheEvaluation.getNoteCompetencesTechniquesSavoir() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "\n" +
                "        <table border=\"1\" style=\"text-align: start; margin: 2% auto; width: 80%\">\n" +
                "\n" +
                "            <tr>\n" +
                "                <td>Savoir (Connaissances Pratique)</td>\n" +
                "                <td>1</td>\n" +
                "                <td>2</td>\n" +
                "                <td>3</td>\n" +
                "                <td>4</td>\n" +
                "                <td>5</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Etre capable d'accomplir un programme d’animation</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r3\" id=\"r3\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r3\" id=\"r3\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r3\" id=\"r3\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r3\" id=\"r3\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r3\" id=\"r3\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Assurer la bonne exécution des actions TDM sur les lieux de vente</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r4\" id=\"r4\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r4\" id=\"r4\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r4\" id=\"r4\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r4\" id=\"r4\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r4\" id=\"r4\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Maitriser les techniques de merchandising</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Etre capable d'assurer la mise en place du matériel PLV </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"r5\" id=\"r5\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td>Note</td>\n" +
                "                <td colspan=\"5\"><input type='text' name='noteCompetencesTechniquesSavoirFaire'\n" +
                "                        value='" + ficheEvaluation.getNoteCompetencesTechniquesSavoirFaire() + "' /></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"4\" style=\"background-color: rgb(98, 171, 179)\">\n" +
                "                    Note Comportementale\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: start; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"6\" style=\"text-align: start\">\n" +
                "                    sens de L'organisation ?<br>\n" +
                "                    Arrive à l'heure, fourni tous les documents et informations requis, a préparé son entretien, analysé\n" +
                "                    l'offre, découvert l'entreprise ...\n" +
                "\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>1</td>\n" +
                "                <td>2</td>\n" +
                "                <td>3</td>\n" +
                "                <td>4</td>\n" +
                "                <td>5</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Ponctualité </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPonctualite\" value='1' id=\"noteComportementPonctualite\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPonctualite\" value='2' id=\"noteComportementPonctualite\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPonctualite\" value='3' id=\"noteComportementPonctualite\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPonctualite\" value='4' id=\"noteComportementPonctualite\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPonctualite\" value='5' id=\"noteComportementPonctualite\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Préparation de l'entretien</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPreparationEntretien\" value='1'\n" +
                "                        id=\"noteComportementPreparationEntretien\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPreparationEntretien\" value='2'\n" +
                "                        id=\"noteComportementPreparationEntretien\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPreparationEntretien\" value='3'\n" +
                "                        id=\"noteComportementPreparationEntretien\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPreparationEntretien\" value='4'\n" +
                "                        id=\"noteComportementPreparationEntretien\">\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementPreparationEntretien\" value='5'\n" +
                "                        id=\"noteComportementPreparationEntretien\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Connaisance de l'Etse</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConnaissanceEntreprise\"\n" +
                "                        id=\"noteComportementConnaissanceEntreprise\" value='1'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConnaissanceEntreprise\"\n" +
                "                        id=\"noteComportementConnaissanceEntreprise\" value='2'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConnaissanceEntreprise\"\n" +
                "                        id=\"noteComportementConnaissanceEntreprise\" value='3'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConnaissanceEntreprise\"\n" +
                "                        id=\"noteComportementConnaissanceEntreprise\" value='4'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConnaissanceEntreprise\"\n" +
                "                        id=\"noteComportementConnaissanceEntreprise\" value='5'>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "\n" +
                "        </table>\n" +
                "\n" +
                "        <table border=\"1\" style=\"text-align: start; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"6\" style=\"text-align: start\">\n" +
                "                    Sens de communication ? <br>\n" +
                "                    Expression et prise de parole : Parle avec un bon tempo, aisence dans l'expression et répond\n" +
                "                    clairement aux diverses questions <br>\n" +
                "                    Capacité à communiquer à des personnes inconnus : s'adresse à tous les présents ou bien il a choisi\n" +
                "                    de se fixer sur un seul recruteur <br>\n" +
                "                    Confience en soi : Gestuels et posture lors d'expression, energie et capacité a défendre ses aquis\n" +
                "                    <br>\n" +
                "                    Ecoute : Avoir la capacité d’écouter attentivement et analyser les questions afin de pouvoir les\n" +
                "                    reformuler en une réponse exacte. <br>\n" +
                "                    Motivation : Pourquoi avez-vous changez de poste? Que pouvez vous ajouter dans votre nouveau poste?\n" +
                "                    Votre objectif dans notre groupe?\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Echelle</td>\n" +
                "                <td>1</td>\n" +
                "                <td>2</td>\n" +
                "                <td>3</td>\n" +
                "                <td>4</td>\n" +
                "                <td>5</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Expression et prise de parole</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementExpressionPriseParole\"\n" +
                "                        id=\"noteComportementExpressionPriseParole\" value='1'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementExpressionPriseParole\"\n" +
                "                        id=\"noteComportementExpressionPriseParole\" value='2'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementExpressionPriseParole\"\n" +
                "                        id=\"noteComportementExpressionPriseParole\" value='3'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementExpressionPriseParole\"\n" +
                "                        id=\"noteComportementExpressionPriseParole\" value='4'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementExpressionPriseParole\"\n" +
                "                        id=\"noteComportementExpressionPriseParole\" value='5'>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Capacité à communiquer</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementCapaciteCommuniquer\"\n" +
                "                        id=\"noteComportementCapaciteCommuniquer\" value='1'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementCapaciteCommuniquer\"\n" +
                "                        id=\"noteComportementCapaciteCommuniquer\" value='2'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementCapaciteCommuniquer\"\n" +
                "                        id=\"noteComportementCapaciteCommuniquer\" value='3'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementCapaciteCommuniquer\"\n" +
                "                        id=\"noteComportementCapaciteCommuniquer\" value='4'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementCapaciteCommuniquer\"\n" +
                "                        id=\"noteComportementCapaciteCommuniquer\" value='5'>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Confiance en soi</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConfianceEnSoi\" id=\"noteComportementConfianceEnSoi\"\n" +
                "                        value='1'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConfianceEnSoi\" id=\"noteComportementConfianceEnSoi\"\n" +
                "                        value='2'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConfianceEnSoi\" id=\"noteComportementConfianceEnSoi\"\n" +
                "                        value='3'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConfianceEnSoi\" id=\"noteComportementConfianceEnSoi\"\n" +
                "                        value='4'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementConfianceEnSoi\" id=\"noteComportementConfianceEnSoi\"\n" +
                "                        value='5'>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Ecoute </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementEcoute\" id=\"noteComportementEcoute\" value='1'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementEcoute\" id=\"noteComportementEcoute\" value='2'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementEcoute\" id=\"noteComportementEcoute\" value='3'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementEcoute\" id=\"noteComportementEcoute\" value='4'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementEcoute\" id=\"noteComportementEcoute\" value='5'>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Motivation & volonté de progresser</td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementMotivation\" id=\"noteComportementMotivation\" value='1'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementMotivation\" id=\"noteComportementMotivation\" value='2'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementMotivation\" id=\"noteComportementMotivation\" value='3'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementMotivation\" id=\"noteComportementMotivation\" value='4'>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"noteComportementMotivation\" id=\"noteComportementMotivation\" value='5'>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "\n" +
                "        </table>\n" +
                "\n" +
                "\n" +
                "        <table border=\"1\" style=\"text-align: center; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"4\" style=\"background-color: rgb(98, 171, 179)\">\n" +
                "                    Resultat final\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <table border=\"1\" style=\"text-align: start; margin: 2% auto; width: 80%\">\n" +
                "            <tr>\n" +
                "                <td colspan=\"4\">commentaire :</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td colspan=\"4\">\n" +
                "<textarea style='width: 100%; max-width: 400px;' id='commentaire' name='commentaire' rows='4' cols='50'></textarea><br><br>"+

        "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Point Forts</td>\n" +
                "                <td>Point Faible</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td><input type=\"text\" style=\"width: 98%;\" name='poidsFort'\n" +
                "                        value='" + ficheEvaluation.getPoidsFort() + "'></td>\n" +
                "                <td><input type=\"text\" style=\"width: 98%;\" name='poidsFaible'\n" +
                "                        value='" + ficheEvaluation.getPoidsFaible() + "'></td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                 "<input type='submit' style='background-color: #28a745; color: white; padding: 10px 20px; border: none; cursor: pointer;' value='Soumettre'>"+

        "    </form>\n" +
                "\n" +
                "</body>";

        eee(emailEvaluateur, subject, body);
    }

        private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


}
