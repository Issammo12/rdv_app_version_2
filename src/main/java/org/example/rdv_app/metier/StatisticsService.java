package org.example.rdv_app.metier;

import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Client;

import java.util.List;

public interface StatisticsService {
    public int totalNumberOfUsers();
    public int totalNumberOfAbonnes();
    public int totalNumberOfClients();
    public int totalNumberOfRendezvous();

    public int totalRevenus();
    public List<Abonne> getAbonnes();
    public List<Client> getClients();

    public int totalNumberOfAbonnesByMonth();

}
