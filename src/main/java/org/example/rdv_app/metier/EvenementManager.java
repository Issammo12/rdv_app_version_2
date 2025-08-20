package org.example.rdv_app.metier;

import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Evenement;
import org.example.rdv_app.dao.repositories.AbonneRepository;
import org.example.rdv_app.dao.repositories.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvenementManager implements EvenementService {
    @Autowired
    private EvenementRepository eventRepo;
    @Autowired
    private AbonneRepository abonneRepository;

    @Override
    public List<Evenement> getAllEvenements() {
        return eventRepo.findAll();
    }

    @Override
    public Evenement getEvenementById(int id) {
        return eventRepo.findById(id).get();
    }

    @Override
    public Evenement addEvenement(Evenement evenement , int id) {
        Abonne a = abonneRepository.findById(id).get();
        Evenement ev = new Evenement();
        ev.setDate(evenement.getDate());
        ev.setStatus(evenement.getStatus());
        ev.setFin(evenement.getFin());
        ev.setDebut(evenement.getDebut());
        ev.setNbr_place(evenement.getNbr_place());
        ev.setPrix(evenement.getPrix());
        ev.setTitre(evenement.getTitre());
        ev.setDescription(evenement.getDescription());
        ev.setAbonne(a);
        return eventRepo.save(ev);
    }

    @Override
    public Evenement updateEvenement(Evenement evenement) {
        return eventRepo.save(evenement);
    }

    @Override
    public boolean deleteEvenementById(int id) {
        if(eventRepo.existsById(id)==true) {
            eventRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
