package org.example.rdv_app.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Evenement")
public class Evenement{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titre;
    private Date date;
    private String debut;
    private String fin;
    private String status;
    private int nbr_place;
    @ManyToOne
    @JoinColumn(name = "abonne_id")
    private Abonne abonne;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
