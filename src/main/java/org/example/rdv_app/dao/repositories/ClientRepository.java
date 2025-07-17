package org.example.rdv_app.dao.repositories;

import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client getClientByEmail(String email);

    boolean existsByEmail(String email);
}
