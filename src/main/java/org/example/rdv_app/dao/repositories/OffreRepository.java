package org.example.rdv_app.dao.repositories;

import org.example.rdv_app.dao.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Offre, Integer> {
}
