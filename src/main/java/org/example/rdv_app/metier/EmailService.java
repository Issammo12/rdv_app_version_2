package org.example.rdv_app.metier;

import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.example.rdv_app.dao.entities.RendezVous;
import org.springframework.stereotype.Service;


public interface EmailService {
    public void demandeRvEmail(RendezVous demandeRV , HttpSession session) throws MessagingException;
    public void confirmRvEmail(RendezVous demandeRV , HttpSession session) throws MessagingException;
    public void cancelRvEmail(RendezVous demandeRV , HttpSession session) throws MessagingException;
    public void rappelVeilleRvEmail(RendezVous demandRV) throws MessagingException;
    public void rappelHeureRvEmail(RendezVous demandRV) throws MessagingException;

}
