package org.example.rdv_app.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Abonné")
@NoArgsConstructor
@AllArgsConstructor

public class Abonne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer abonne_id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String password;
    private String profession;
    private LocalDate creation_date = LocalDate.now();
    private boolean active=true;
    @OneToMany(mappedBy = "abonne" , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RendezVous> rendezVousList;
    @OneToMany(mappedBy = "abonne" , fetch = FetchType.LAZY)
    @JsonIgnore

    private List<Creneau> creneauList;
    @OneToMany(mappedBy = "abonne" , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Evenement> evenementList;
    @OneToMany(mappedBy = "abonne" , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Offre> offreList;
    @ManyToOne
    @JsonIgnore
    private Client client;
    @OneToOne
    @JsonIgnore
    private CodePromo codePromo;

}
