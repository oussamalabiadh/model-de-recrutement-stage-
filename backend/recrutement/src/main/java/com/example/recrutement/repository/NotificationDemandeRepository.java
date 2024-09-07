package com.example.recrutement.repository;

import com.example.recrutement.entity.notificationDemande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationDemandeRepository extends JpaRepository<notificationDemande,Long> {
}
