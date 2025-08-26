package org.example.rdv_app.metier;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Client;
import org.example.rdv_app.dao.entities.RendezVous;
import org.example.rdv_app.dao.repositories.AbonneRepository;
import org.example.rdv_app.dao.utils.EmailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Arrays;
import java.util.Date;

@Service
public class EmailManager implements EmailService{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AuthService authService;
    @Autowired
    private EmailContent emailContent;
    @Autowired
    private AbonneRepository abonneRepository;


    @Override
    public void demandeRvEmail(RendezVous demandeRV , HttpSession session) throws MessagingException {
//        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        message.setFrom("issam.mounir.pro@gmail.com");
        mimeMessage.setFrom("issam.mounir.pro@gmail.com");
        mimeMessage.setSubject("demande d'un rendez-vous");
        EmailContent emailContent = new EmailContent();
        String object= (String) session.getAttribute("class");
        if (object.equals(Abonne.class)) {
            String html =emailContent.demandeEmailContent(demandeRV.getAbonne().getNom());
            mimeMessage.setContent(html , "text/html ; charset=utf-8");
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO , demandeRV.getClient().getEmail());
            mailSender.send(mimeMessage);
            System.out.println("ok");
        }
        if (object.equals(Client.class)) {
            String html =emailContent.demandeEmailContent(demandeRV.getClient().getNom());
            mimeMessage.setContent(html , "text/html ; charset=utf-8");
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO , demandeRV.getAbonne().getEmail());
            mailSender.send(mimeMessage);
            System.out.println("ok");
        }




    }

    @Override
    public void confirmRvEmail(RendezVous demandeRV, HttpSession session) throws MessagingException {
        //        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        message.setFrom("issam.mounir.pro@gmail.com");
        mimeMessage.setFrom("issam.mounir.pro@gmail.com");
        mimeMessage.setSubject("demande d'un rendez-vous");
        EmailContent emailContent = new EmailContent();
        String object= (String) session.getAttribute("class");
        if (object.equals(Abonne.class)) {
            String html =emailContent.confirmEmailContent(demandeRV.getAbonne().getNom());
            mimeMessage.setContent(html , "text/html ; charset=utf-8");
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO , demandeRV.getClient().getEmail());
            mailSender.send(mimeMessage);
            System.out.println("ok");
        }
        if (object.equals(Client.class)) {
            String html =emailContent.confirmEmailContent(demandeRV.getClient().getNom());
            mimeMessage.setContent(html , "text/html ; charset=utf-8");
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO , demandeRV.getAbonne().getEmail());
            mailSender.send(mimeMessage);
            System.out.println("ok");
        }


    }

    @Override
    public void cancelRvEmail(RendezVous demandeRV , HttpSession session) throws MessagingException {
        //        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        message.setFrom("issam.mounir.pro@gmail.com");
        mimeMessage.setFrom("issam.mounir.pro@gmail.com");
        mimeMessage.setSubject("demande d'un rendez-vous");
//        EmailContent emailContent = new EmailContent();
        String object= (String) session.getAttribute("class");
        if (object.equals(Abonne.class)) {
            String html =emailContent.cancelEmailContent(demandeRV.getAbonne().getNom());
            mimeMessage.setContent(html , "text/html ; charset=utf-8");
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO , demandeRV.getClient().getEmail());
            mailSender.send(mimeMessage);
            System.out.println("ok");
        }
        if (object.equals(Client.class)) {
            String html =emailContent.cancelEmailContent(demandeRV.getClient().getNom());
            mimeMessage.setContent(html , "text/html ; charset=utf-8");
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO , demandeRV.getAbonne().getEmail());
            mailSender.send(mimeMessage);
            System.out.println("ok");
        }
    }

    @Override
    public void rappelVeilleRvEmail(RendezVous demandRV) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setFrom("issam.mounir.pro@gmail.com");
        mimeMessage.setSubject("demande d'un rendez-vous");
        String[] adresses= {demandRV.getAbonne().getEmail() , demandRV.getClient().getEmail()};
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO , Arrays.toString(adresses));
        Date date = new Date();
        date.setYear(demandRV.getCreneau().getDate().getYear());
        date.setMonth(demandRV.getCreneau().getDate().getMonth());
        date.setDate(demandRV.getCreneau().getDate().getDate()-1);
        mimeMessage.setSentDate(date);
        String html =emailContent.rappelVeilleEmailContent();
        mimeMessage.setContent(html , "text/html ; charset=utf-8");
        mailSender.send(mimeMessage);
        System.out.println("ok");
    }

    public void rappelHeureRvEmail(RendezVous demandRV) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setFrom("issam.mounir.pro@gmail.com");
        mimeMessage.setSubject("demande d'un rendez-vous");
        String[] adresses= {demandRV.getAbonne().getEmail() , demandRV.getClient().getEmail()};
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO , Arrays.toString(adresses));
        Date date = new Date();
        date.setYear(demandRV.getCreneau().getDate().getYear());
        date.setMonth(demandRV.getCreneau().getDate().getMonth());
        date.setDate(demandRV.getCreneau().getDate().getDate());
        date.setHours(demandRV.getCreneau().getDate().getHours()-1);
        mimeMessage.setSentDate(date);
        String html =emailContent.rappelHeureEmailContent();
        mimeMessage.setContent(html , "text/html ; charset=utf-8");
        mailSender.send(mimeMessage);
        System.out.println("ok");
    }


}
