package org.example.rdv_app.metier;

import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import org.example.rdv_app.dao.entities.RendezVous;
import org.springframework.stereotype.Service;


public interface EmailService {
    public void demandeRvEmail(RendezVous demandeRV , String authHeader) throws MessagingException;
    public void confirmRvEmail(RendezVous demandeRV , String authHeader) throws MessagingException;
    public void cancelRvEmail(RendezVous demandeRV , String authHeader) throws MessagingException;
    public void rappelVeilleRvEmail(RendezVous demandRV) throws MessagingException;
    public void rappelHeureRvEmail(RendezVous demandRV) throws MessagingException;

}
