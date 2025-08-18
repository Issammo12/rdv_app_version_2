package org.example.rdv_app.controller;

import jakarta.servlet.http.HttpSession;
import org.example.rdv_app.dao.entities.Abonne;
import org.example.rdv_app.dao.entities.Offre;
import org.example.rdv_app.dao.repositories.AbonneRepository;
import org.example.rdv_app.dao.utils.JwtUtil;
import org.example.rdv_app.metier.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offre")
public class OffreController {
    @Autowired
    private OffreService offreService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AbonneRepository abonneRepository;
    @GetMapping("/list")
    public List<Offre> getOffreList(){

        return offreService.getAllOffre();
    }

    @GetMapping("{id}")
    public Offre getOffre(@PathVariable int id){
        return offreService.getOffreById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOffre(@RequestBody Offre offre , HttpSession session){
        int id = (int) session.getAttribute("id");
        Offre o= offreService.addOffre(offre , id);
        return ResponseEntity.ok("Offer added successfully" + o.toString());
    }

    @PutMapping("update")
    public Offre updateOffre(@RequestBody Offre offre){
        return offreService.updateOffre(offre);
    }

    @DeleteMapping("delete/{id}")
    public String deleteOffre(@PathVariable int id){
        boolean res = offreService.deleteOffre(id);
        if(res){
            return "Offre deleted Successfully";
        }
        else{
            return "Offre Not Deleted";
        }
    }

    @GetMapping("/offre-By-Category")
    public List<Offre> getOffreByCategory(@RequestParam String category){
        return offreService.getOffreByCategory(category);
    }
    @GetMapping("/all-categories")
public List<String> getAllCategories(){
    return offreService.CategoryList();}
}
