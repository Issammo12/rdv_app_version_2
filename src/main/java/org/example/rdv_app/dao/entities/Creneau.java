package org.example.rdv_app.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Heure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int heure;
    private String jour;
}
