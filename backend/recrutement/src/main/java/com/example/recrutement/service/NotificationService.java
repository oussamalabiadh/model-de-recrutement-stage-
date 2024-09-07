package com.example.recrutement.service;

import com.example.recrutement.entity.*;
import com.example.recrutement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

        @Autowired
        private NotificationEvaluationRepository notificationEvaluationRepository;
        @Autowired
        private NotificationRepository notificationRepository;
        @Autowired
        private NotificationGlobaleRepository notificationGlobaleRepository;
        @Autowired
        private NotificationCvRepository notificationCvRepository;
        @Autowired
        private NotificationDemandeRepository notificationDemandeRepository;
        // Méthode pour enregistrer une nouvelle notification

        @Transactional

        public void saveNotificationDemande(Notification notification) {
                notificationRepository.save(notification);
                NotificationGlobale notificationGlobale = new NotificationGlobale();
                notificationGlobale.setType("Validation");
                notificationGlobale.setValidation(notification);
                notificationGlobale.setVue(false);
                notificationGlobaleRepository.save(notificationGlobale);

        }
        @Transactional

        public void saveNotificationEvalution(NotificationEvalution notificationEvalution) {
                notificationEvaluationRepository.save(notificationEvalution);
                NotificationGlobale notificationGlobale = new NotificationGlobale();
                notificationGlobale.setType("Evaluation");
                notificationGlobale.setEvaluation(notificationEvalution);
                notificationGlobale.setVue(false);

                notificationGlobaleRepository.save(notificationGlobale);

        }
        @Transactional

        public void saveNotificationCv(notificationCv cv) {
                notificationCvRepository.save(cv);
                NotificationGlobale notificationGlobale = new NotificationGlobale();
                notificationGlobale.setType("cv");
                notificationGlobale.setVue(false);

                notificationGlobale.setCv(cv);
                notificationGlobaleRepository.save(notificationGlobale);
        }
        @Transactional

        public void saveNotificationDemande(notificationDemande demande) {
                notificationDemandeRepository.save(demande);
                NotificationGlobale notificationGlobale = new NotificationGlobale();
                notificationGlobale.setType("Demande");
                notificationGlobale.setVue(false);

                notificationGlobale.setNotificationDemande(demande);
                notificationGlobaleRepository.save(notificationGlobale);
        }

        public byte[] getPdfDataById(Long id) {
                Optional<NotificationEvalution> notificationEvalution = notificationEvaluationRepository.findById(id);
                if (notificationEvalution.isPresent()) {
                        return notificationEvalution.get().getPdf();
                }
                return null;
        }


}
