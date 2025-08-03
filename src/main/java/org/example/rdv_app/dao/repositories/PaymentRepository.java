package org.example.rdv_app.dao.repositories;


import org.example.rdv_app.dao.entities.Payment;
import org.example.rdv_app.dao.entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    //List<Payments> findByStatusEquals(String status);
    List<Payment> findByTreatmentStatusEquals(PaymentStatus status);

    @Query("SELECT p FROM Payment p WHERE p.transaction_id = ?1")
    Payment findByTransaction_idEquals(String transaction_id);

    @Query("SELECT p FROM Payment p WHERE p.applicationSource = ?1 AND p.treatmentStatus = ?2")
    List<Payment> findByApplicationSourceAndTreatmentStatus(String applicationSource, PaymentStatus status);

    @Query("SELECT p FROM Payment p WHERE p.payment_status = ?2 AND p.treatmentStatus = ?1 AND p.applicationSource = ?3")
    List<Payment> findByTreatmentStatusAndPaymentStatus(PaymentStatus status, String payment_status, String applicationSource);
}
