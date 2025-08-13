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

		Abonne abonne = new Abonne();
		abonne.setAbonne_id(null);
		abonne.setNom("iss");
		abonne.setPrenom("is");
		abonne.setEmail("lajigamomo@gmail.com");
		abonne.setPassword("archiissam123456");
		abonne.setProfession("architecte");
		abonne.setAdresse("Casablanca");
		abonne.setTelephone("1234567890");



	    System.out.println(abonne.toString());
		abonneRepository.save(abonne);

		Client client = new Client();
		client.setClient_id(null);
		client.setNom("adam");
		client.setPrenom("adma");
		client.setEmail("issam.mounir.pro@gmail.com");
		client.setPassword("archiissam123");
		client.setAdresse("Casablanca");
		client.setTelephone("1234567890");

	    System.out.println(client.toString());
		clientRepository.save(client);

		Offre offre = new Offre();
		offre.setId(null);
		offre.setNom("Consultation");
		offre.setDescription("Je consulte avec vous les differents idees ");
		offre.setCategory("Consultation");
		offre.setPrice(10);
		offre.setDuration(30);
		offre.setAbonne(abonne);
	    System.out.println(offre.toString());
//		abonne.setOffreList(List.of(offre));
//		abonneRepository.save(abonne);
		offreRepository.save(offre);




		Creneau creneau = new Creneau();
		creneau.setId(null);
		creneau.setHeure(10);
		creneau.setDate(new Date(2025 , 07 , 18));
		creneau.setAbonne(abonne);
	    System.out.println(creneau.toString());
		creneauRepository.save(creneau);
//		abonne.setCreneauList(List.of(creneau));
//
//
//
//
		RendezVous rendezVous = new RendezVous();
		rendezVous.setId(null);
		rendezVous.setStatus(Statut.Confirmé);
		rendezVous.setCreneau(creneau);
		rendezVous.setOffre(offre);
		rendezVous.setClient(client);
		rendezVous.setAbonne(abonne);
//		rendezVous.toString();
		System.out.println(rendezVous.toString());
		rendezVousRepository.save(rendezVous);
//		abonne.setRendezVousList(List.of(rendezVous));
//
//
//
//
		CodePromo codePromo = new CodePromo();
		codePromo.setCode_id(null);
		codePromo.setCode("SSF3I");
		codePromo.setPercentage(20);
		codePromo.setDateDebut(new Date(14 , 07 , 2025));
		codePromo.setDateFin(new Date(15 , 07 , 2025));
		codePromo.setAbonne(abonne);
//		abonne.setCodePromo(codePromo);
	    System.out.println(codePromo.toString());
		codePromoRepository.save(codePromo);

		double p=abonneService.appliquerCode(codePromo , offre);
		System.out.println(p);



		System.out.println(creneau.getDate().getDate() + 1);


		System.out.println(java.sql.Date.valueOf(LocalDate.now()).after(rendezVousRepository.findById(1).get().getCreneau().getDate()));

		System.out.println(Calendar.getInstance());
		Date date = new Date();
		int a =Calendar.DAY_OF_MONTH;
		System.out.println(a);


		List<Abonne> liste = abonneRepository.findAll();
		Map<LocalDate , Long> map = liste.stream().collect(Collectors.groupingBy(e -> e.getCreation_date(), Collectors.counting()));
		System.out.println(map.toString());


	}
}
