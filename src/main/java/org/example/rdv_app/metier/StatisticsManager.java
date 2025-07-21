package org.example.rdv_app.metier;

import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Client;
import org.example.rdv_app.dao.entities.RendezVous;
import org.example.rdv_app.dao.repositories.*;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class StatisticsManager implements StatisticsService {
    @Autowired
    private AbonneRepository abonneRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private CreneauRepository creneauRepository;
    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private CodePromoRepository codePromoRepository;
    @Override
    public int totalNumberOfUsers() {
        return abonneRepository.findAll().size()+clientRepository.findAll().size();
    }

    @Override
    public int totalNumberOfAbonnes() {
        return abonneRepository.findAll().size();
    }

    @Override
    public int totalNumberOfClients() {
        return clientRepository.findAll().size();
    }

    @Override
    public int totalNumberOfRendezvous() {
        return rendezVousRepository.findAll().size();
    }

    @Override
    public int totalRevenus() {
        List<RendezVous> list = rendezVousRepository.findAll();
        int total = 0;
        for (RendezVous r : list) {
            total = total + r.getOffre().getPrice();
        }
        return total;
    }

    @Override
    public List<Abonne> getAbonnes() {
        return abonneRepository.findAll();
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public int totalNumberOfAbonnesByMonth() {
        List<Abonne> list = abonneRepository.findAll();
        int total = 0;
        for (Abonne a : list) {
            if(a.getCreation_date().getMonthValue() == LocalDate.now().getMonthValue()-1){
                total++;
            }
        }
        return total;
    }

}
