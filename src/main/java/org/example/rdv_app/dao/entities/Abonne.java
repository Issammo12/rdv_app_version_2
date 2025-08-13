package org.example.rdv_app.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Abonné")
@NoArgsConstructor
@AllArgsConstructor

public class Abonne implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return active; }
}
