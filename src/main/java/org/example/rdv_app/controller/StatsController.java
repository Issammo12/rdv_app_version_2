package org.example.rdv_app.controller;

import jdk.jfr.Timespan;
import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Client;
import org.example.rdv_app.dao.entities.Evenement;
import org.example.rdv_app.dao.entities.Offre;
import org.example.rdv_app.metier.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/back-office")
public class StatsController {
    @Autowired
    private StatisticsService service;

    @GetMapping("/total-number-users")
    public int totalNumberOfUsers() {
        return service.totalNumberOfUsers();
    }

    @GetMapping("/total-number-abonnes")
    public int totalNumberOfAbonnes() {
        return service.totalNumberOfAbonnes();
    }

    @GetMapping("/total-number-clients")
    public int totalNumberOfClients() {
        return service.totalNumberOfClients();
    }

    @GetMapping("/total-number-rv")
    public int totalNumberOfRV() {
        return service.totalNumberOfRendezvous();
    }

    @GetMapping("/total-Revenus")
    public int totalRevenus() {
        return service.totalRevenus();
    }
    @GetMapping("/abonnes-list")
    public List<Abonne> abonnesList() {
        return service.getAbonnes();
    }
    @GetMapping("/clients-list")
    public List<Client> clientsList() {
        return service.getClients();
    }

    @DeleteMapping("/supprimer-abonne/{id}")
    public String supprimerAbonne(@PathVariable int abonne) {
        service.supprimerCompteAbonne(abonne);
        return "success";
    }
    @DeleteMapping("/supprimer-client/{id}")
    public String supprimerClient(@PathVariable int client) {
        service.supprimerCompteClient(client);
        return "success";
    }
    @PostMapping("/disable-abonne/{id}")
    public String disableAbonne(@PathVariable int abonne) {
        service.desactiverCompteAbonne(abonne);
        return "success";
    }
    @PostMapping("/disable-client/{id}")
    public String disableClient(@PathVariable int client) {
        service.desactiverCompteClient(client);
        return "success";
    }
    @GetMapping("/number-rv/{id}")
    public int numberRV(@PathVariable int id) {
        return service.totalNumberOfRVByAbonne(id);
    }
    @GetMapping("/revenus/{id}")
    public int revenus(@PathVariable int id) {
        return service.totalRevenusByAbonne(id);
    }
    @GetMapping("/mostTakenOffres/{id}")
    public List<Object> mostTakenOffres(@PathVariable int id) {
        return service.mostTakenOffresByAbonne(id);
    }
    @GetMapping("/number-rv-client/{id}")
    public int numberRVClient(@PathVariable int id) {
        return service.totalNumberOfRVByClient(id);
    }
    @GetMapping("all-Offres-client/{id}")
    public List<Offre> allOffres(@PathVariable int id) {
        return service.allOffresByClient(id);
    }
    @GetMapping("/popularEvents/{id}")
    public List<Object> popularEvents(@PathVariable int id) {
        return service.popularEvents(id);
    }
    @Scheduled(fixedRate = 300000)
    @GetMapping("/new-abonnes-day")
    public Map<LocalDate , Integer> newAbonnesDay() {
        return service.totalNewAbonnesPerDay();
    }

    @GetMapping("/new-clients-day")
    public Map<LocalDate , Integer> newClientsDay() {
        return service.totalNewClientsPerDay();
    }
    @GetMapping("/views/{id}")
    public Map<LocalDate, Integer> views(@PathVariable int id) {
        return service.viewsPerAbonne(id);
    }

    @GetMapping("/total-events/{id}")
    public int totalEvents(@PathVariable int id) {
        return service.totalEventsByClient(id);
    }

    @GetMapping("/list-clients-visiting-abonne/{id}")
    public List<Client> listClientsVisitingAbonne(@PathVariable int id) {
        return service.listClientsVisitedAbonne(id);
    }
}
