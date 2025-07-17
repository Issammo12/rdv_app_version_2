package org.example.rdv_app.dao.repositories;

import org.example.rdv_app.dao.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
