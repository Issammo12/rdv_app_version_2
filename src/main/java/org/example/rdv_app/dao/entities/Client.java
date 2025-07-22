package org.example.rdv_app.dao.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @OneToMany(mappedBy = "client")
    private List<RendezVous> rendezVousList;
    @OneToMany(mappedBy = "client")
    private List<Evenement> evenementList;
    @ManyToOne
    private CodePromo codePromo;
}
