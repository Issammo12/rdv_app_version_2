package org.example.rdv_app.controller;

import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import org.example.rdv_app.dao.entities.RendezVous;
import org.example.rdv_app.dao.repositories.RendezVousRepository;
import org.example.rdv_app.metier.EmailService;
import org.example.rdv_app.metier.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rendez-vous")
public class RendezVousController {
    @Autowired
    RendezVousService rendezVousService;
    @Autowired
    RendezVousRepository rendezVousRepository;
    @Autowired
    EmailService emailService;

    // les methodes pour recuperer abonne , client ...

    @PostMapping("/add")
    public RendezVous addRendezVous(@RequestBody RendezVous rendezVous , @RequestHeader("Authorization") String token) throws MessagingException {
        emailService.demandeRvEmail(rendezVous ,token);
        return rendezVousService.addRendezVous(rendezVous);
    }

    @PutMapping("/update")
    public RendezVous update(@RequestBody RendezVous rendezVous ,@RequestHeader("Authorization") String token) throws MessagingException {
        emailService.demandeRvEmail(rendezVous , token);
        return rendezVousService.updateRendezVous(rendezVous);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        boolean res = rendezVousService.deleteRendezVous(id);
        if (res) return "success";
        else return "fail";
    }

    @GetMapping("/{id}")
    public RendezVous getRendezVous(@PathVariable int id) {
        return rendezVousService.findRendezVousById(id);
    }

    @GetMapping("/all")
    public List<RendezVous> getAllRendezVous() {
        return rendezVousService.getAllRendezVous();
    }

    @GetMapping("/Confirm/{id}")
    public String ConfirmDemandeRV(@PathVariable int demandeRV , @RequestHeader("Authorization") String token) throws AuthenticationFailedException, MessagingException {
        emailService.confirmRvEmail(rendezVousRepository.getById(demandeRV), token);
        rendezVousService.confirmerRV(demandeRV);
        return "OK";
    }

    @GetMapping("/cancel/{id}")
    public String cancelDemandeRV(@PathVariable int demandeRV , @RequestHeader("Authorization") String token) throws AuthenticationFailedException , MessagingException {
        emailService.cancelRvEmail(rendezVousRepository.getById(demandeRV) , token);
        rendezVousService.annulerRV(demandeRV);
        return "OK";
    }
}
