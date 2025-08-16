package org.example.rdv_app;

import org.example.rdv_app.dao.entities.*;
import org.example.rdv_app.dao.repositories.*;
import org.example.rdv_app.dao.utils.Statut;
import org.example.rdv_app.metier.AbonneService;
import org.example.rdv_app.metier.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableScheduling
public class RdvAppApplication implements CommandLineRunner {
	@Autowired
	AbonneRepository abonneRepository;
	@Autowired
	CreneauRepository creneauRepository;
	@Autowired
	OffreRepository offreRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	RendezVousRepository rendezVousRepository;
    @Autowired
    private CodePromoRepository codePromoRepository;
    @Autowired
    private AbonneService abonneService;

    @Autowired
    private EmailService emailService;


	public static void main(String[] args) {
		SpringApplication.run(RdvAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
