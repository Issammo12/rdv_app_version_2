package org.example.rdv_app.metier;

import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Creneau;
import org.example.rdv_app.dao.repositories.AbonneRepository;
import org.example.rdv_app.dao.repositories.CreneauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CreneauManager implements CreneauService{
    @Autowired
    private CreneauRepository creneauRepository;
    @Autowired
    private AbonneRepository abonneRepository;

    @Override
    public Creneau getCreneauById(int id) {
        return creneauRepository.findById(id).get();
    }

    @Override
    public List<Creneau> getAllCreneau() {
        return creneauRepository.findAll();
    }

    @Override
    public Creneau addCreneau(Creneau creneau , int id) {
        Abonne abonne = abonneRepository.findById(id).get();
        Creneau c=new Creneau();
        c.setDate(creneau.getDate());
        c.setHeure(creneau.getHeure());
        c.setAbonne(abonne);
        return creneauRepository.save(c);
    }

    @Override
    public Creneau updateCreneau(Creneau creneau) {
        return creneauRepository.save(creneau);
    }

    @Override
    public boolean deleteCreneauById(int id) {
        if(creneauRepository.existsById(id)==true) {
            creneauRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
