package org.example.rdv_app.metier;

import org.apache.coyote.Request;
import org.apache.coyote.RequestInfo;
import org.apache.logging.log4j.CloseableThreadContext;
import org.aspectj.weaver.ast.Instanceof;
import org.example.rdv_app.dao.entities.*;
import org.example.rdv_app.dao.repositories.*;
import org.example.rdv_app.dao.utils.Statut;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public void supprimerCompteAbonne(int a) {
        abonneRepository.delete(abonneRepository.findById(a).get());
    }

    @Override
    public void supprimerCompteClient(int a) {
        clientRepository.delete(clientRepository.findById(a).get());
    }

    @Override
    public void desactiverCompteAbonne(int a) {
        abonneRepository.findById(a).get().setActive(false);
    }

    @Override
    public void desactiverCompteClient(int a) {
        clientRepository.findById(a).get().setActive(false);
    }

    @Override
    public int totalNumberOfRVByAbonne(int abonneId) {
        return (int) abonneRepository.findById(abonneId).get().getRendezVousList().stream().filter(rv -> rv.getStatus()== Statut.Confirmé).count();
    }

    @Override
    public int totalRevenusByAbonne(int abonneId) {
        return abonneRepository.findById(abonneId).get().getRendezVousList().stream().map(rv -> rv.getOffre().getPrice()).reduce(0, Integer::sum);
    }

    @Override
    public List<Object> mostTakenOffresByAbonne(int abonneId) {
        class Result{
            public String nom;
            public Long number;
        }
        List<RendezVous> list = abonneRepository.findById(abonneId).get().getRendezVousList();
        int total = 0;
        Map<String , Long> map = list.stream().collect(Collectors.groupingBy(e -> e.getOffre().getNom(), Collectors.counting()));
        List<Result> results = new ArrayList<>();
        for (String key : map.keySet()) {
            Result r = new Result();
            r.nom = key;
            r.number=map.get(key);
            results.add(r);
        }
        return Collections.singletonList(results);
    }

    @Override
    public int totalNumberOfRVByClient(int clientId) {
        return (int) clientRepository.findById(clientId).get().getRendezVousList().stream().filter(rv -> rv.getStatus()== Statut.Confirmé).count();
    }

    @Override
    public List<Offre> allOffresByClient(int clientId) {
        return clientRepository.findById(clientId).get().getRendezVousList().stream().map(rv -> rv.getOffre()).collect(Collectors.toList());
    }

    @Override
    public List<Object> popularEvents(int abonneId) {
        class Result{
            public String nom;
            public Long number;
        }
        List<Evenement> list = abonneRepository.findById(abonneId).get().getEvenementList();
        int total = 0;
        Map<String , Long> map = list.stream().collect(Collectors.groupingBy(e -> e.getTitre(), Collectors.counting()));
        List<Result> results = new ArrayList<>();
        for (String key : map.keySet()) {
            Result r = new Result();
            r.nom = key;
            r.number=map.get(key);
            results.add(r);
        }

        return Collections.singletonList(results);
    }

    @Override
    public int totalEventsByClient(int clientId) {
        return (int) clientRepository.findById(clientId).get().getEvenementList().stream().count();
    }

    @Override
    public Map<LocalDate , Integer>  totalNewAbonnesPerDay() {
        Map<LocalDate , Integer> map = new HashMap<>();
        List<Abonne> list = abonneRepository.findAll();
        Integer total = 0;
        for (Abonne a : list) {
            if(Objects.equals(a.getCreation_date(), LocalDate.now())){
                total++;
            }
        }
        map.put(LocalDate.now(), total);
//        map.put(LocalDate.now(), (int) abonneRepository.findAll().stream().filter(a -> a.getCreation_date()==LocalDate.now()).count());
        return map;
    }

    @Override
    public Map<LocalDate, Integer> totalNewClientsPerDay() {
        Map<LocalDate , Integer> map = new HashMap<>();
        map.put(LocalDate.now(), (int) clientRepository.findAll().stream().filter(a -> Objects.equals(a.getCreation_date(), LocalDate.now())).count());
        return map;
    }

    @Override
    public Map<LocalDate, Integer> viewsPerAbonne(int abonneId) {
        Map<LocalDate , Integer> map = new HashMap<>();
        List<Client> clients=clientRepository.findAll();
        int total =0;
        for (Client c : clients) {
            if(c.getVisitedAbonnes().contains(abonneId)) total++;
        }
        map.put(LocalDate.now(), total);
        return map;
    }

    @Override
    public List<Client> listClientsVisitedAbonne(int abonneId) {
        return clientRepository.findAll().stream().filter(c -> c.getVisitedAbonnes().contains(abonneId)).collect(Collectors.toList());
    }


}
