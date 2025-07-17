package org.example.rdv_app.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.rdv_app.dao.utils.Statut;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RendezVous")
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//    private boolean paymentReception;
    private Statut status;
    @OneToOne
    private Creneau creneau;
    @OneToOne
    private Offre offre;
    @ManyToOne
    @JoinColumn(name = "abonne_id")
    private Abonne abonne;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
