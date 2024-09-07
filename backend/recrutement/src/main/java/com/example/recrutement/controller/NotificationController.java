package com.example.recrutement.controller;

import com.example.recrutement.dto.ResponseGeneraleDTO;
import com.example.recrutement.dto.ResponseNotificationDTO;
import com.example.recrutement.entity.NotificationGlobale;
import com.example.recrutement.repository.NotificationEvaluationRepository;
import com.example.recrutement.repository.NotificationGlobaleRepository;
import com.example.recrutement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationGlobaleRepository notificationRepository;

    @GetMapping
    public ResponseEntity<ResponseNotificationDTO> getAllNotification() {
        List<NotificationGlobale> NotificationGlobale = notificationRepository.findAll();
        if (!NotificationGlobale.isEmpty()) {
            ResponseNotificationDTO response;
            response = new ResponseNotificationDTO("success", "Notification retrieved successfully", NotificationGlobale);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseNotificationDTO response = new ResponseNotificationDTO("failed", "No Notification found",  null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable Long id) {
        Optional<NotificationGlobale> optionalNotification = notificationRepository.findById(id);
        ResponseNotificationDTO response = new ResponseNotificationDTO();
        if (optionalNotification.isPresent()) {
            response.setState("success");
            response.setMessage("Notification found");
            optionalNotification.get().setVue(true);
            notificationRepository.save(optionalNotification.get());
            response.setData(optionalNotification.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setState("failed");
            response.setMessage("Notification not found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseGeneraleDTO> deleteNotification(@PathVariable Long id) {
        Optional<NotificationGlobale> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            NotificationGlobale notification = optionalNotification.get();
            notificationRepository.delete(notification);
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("success", "notification deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseGeneraleDTO response = new ResponseGeneraleDTO("failed", "notification not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
