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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer client_id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String password;
    private boolean active=true;
    private LocalDate creation_date = LocalDate.now();
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<RendezVous> rendezVousList;
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Evenement> evenementList;
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Abonne> visitedAbonnes;
    @ManyToOne
    @JsonIgnore
    private CodePromo codePromo;
}
