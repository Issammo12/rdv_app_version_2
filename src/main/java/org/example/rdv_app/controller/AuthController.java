package org.example.rdv_app.controller;

import jakarta.servlet.http.HttpSession;
import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Client;
import org.example.rdv_app.dao.repositories.AbonneRepository;
import org.example.rdv_app.dao.repositories.ClientRepository;
import org.example.rdv_app.dao.utils.JwtUtil;
import org.example.rdv_app.dao.utils.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AbonneRepository abonneRepository;
    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request , HttpSession session) {
        Client user = clientRepository.getClientByEmail(request.getEmail());
        Abonne abonne = abonneRepository.getAbonneByEmail(request.getEmail());
        if (user != null) {
            boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());
            if (!passwordMatches) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }

            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("class" , user.getClass().getSimpleName());
            // You might want to send a DTO instead
            session.setAttribute("id", user.getId());
            session.setAttribute("class" , user.getClass().getSimpleName());
            return ResponseEntity.ok(response);
        }

        if(abonne != null){
            boolean passwordMatches = passwordEncoder.matches(request.getPassword(), abonne.getPassword());
            if (!passwordMatches) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }


            Map<String, Object> response = new HashMap<>();
            response.put("user", abonne);
            response.put("class" , abonne.getClass().getSimpleName());
            // You might want to send a DTO instead
            session.setAttribute("id", abonne.getId());
            session.setAttribute("class" , abonne.getClass().getSimpleName());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
    @PostMapping("/current-user")
    public ResponseEntity<?> getAuthenticatedUser(HttpSession session) {
        Integer id = (Integer) session.getAttribute("id");
        if (session.getAttribute("class").equals("Client")) {
            Client client=clientRepository.findById(id).get();
            return ResponseEntity.ok(client);
        }
        else if (session.getAttribute("class").equals("Abonne")) {
            Abonne abonne=abonneRepository.findById(id).get();
            return ResponseEntity.ok(abonne);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }
}
