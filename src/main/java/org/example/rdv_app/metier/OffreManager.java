package org.example.rdv_app.metier;

import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Offre;
import org.example.rdv_app.dao.repositories.AbonneRepository;
import org.example.rdv_app.dao.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffreManager implements OffreService{
    @Autowired
    private OffreRepository offreRepo;
    @Autowired
    private AbonneRepository abonneRepository;

    @Override
    public List<Offre> getAllOffre() {
        return offreRepo.findAll();
    }

    @Override
    public Offre getOffreById(int id) {
        return offreRepo.findById(id).get();
    }

    @Override
    public List<Offre> getOffreByCategory(String category) {
        return offreRepo.getOffresByCategory(category);
    }

    @Override
    public Offre addOffre(Offre offre , int id) {
        Abonne abonne = abonneRepository.findById(id).get();
        if(offreRepo.existsById(offre.getId())==false) {
            offre.setAbonne(abonne);
            return offreRepo.save(offre);
        }
        return null;
    }

    @Override
    public Offre updateOffre(Offre offre) {
        return offreRepo.save(offre);
    }

    @Override
    public boolean deleteOffre(int id) {
        if(offreRepo.existsById(id)==true) {
            offreRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<String> CategoryList() {
        return offreRepo.findAll().stream().map(Offre::getCategory).distinct().collect(Collectors.toList());
    }
}
