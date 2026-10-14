package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    public ResponseEntity<Vehicule> addVehicule(@RequestBody Vehicule vehicule) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.addVehicule(vehicule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        if (vehiculeService.findVehiculeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vehicule.setIdVehicule(id);
        return ResponseEntity.ok(vehiculeService.updateVehicule(vehicule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        if (vehiculeService.findVehiculeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> findAllVehicules() {
        return ResponseEntity.ok(vehiculeService.findAllVehicules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> findVehiculeById(@PathVariable Long id) {
        return vehiculeService.findVehiculeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Vehicule>> findByStatut(@PathVariable StatutVehicule statut) {
        return ResponseEntity.ok(vehiculeService.findByStatut(statut));
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Vehicule>> findByCategorie(@PathVariable CategorieVehicule categorie) {
        return ResponseEntity.ok(vehiculeService.findByCategorie(categorie));
    }

    @GetMapping("/marque/{marque}")
    public ResponseEntity<List<Vehicule>> findByMarque(@PathVariable String marque) {
        return ResponseEntity.ok(vehiculeService.findByMarque(marque));
    }
}
