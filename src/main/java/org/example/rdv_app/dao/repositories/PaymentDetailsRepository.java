package org.example.rdv_app.dao.repositories;

import org.example.rdv_app.dao.entities.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails,Long> {

}
