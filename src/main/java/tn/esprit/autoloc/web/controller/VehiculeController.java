package tn.esprit.autoloc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.VehiculeRequestDTO;
import tn.esprit.autoloc.web.dto.VehiculeResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    public ResponseEntity<VehiculeResponseDTO> addVehicule(@Valid @RequestBody VehiculeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.addVehicule(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculeResponseDTO> updateVehicule(@PathVariable Long id,
                                                              @Valid @RequestBody VehiculeRequestDTO dto) {
        return vehiculeService.updateVehicule(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
    public ResponseEntity<List<VehiculeResponseDTO>> findAllVehicules() {
        return ResponseEntity.ok(vehiculeService.findAllVehicules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeResponseDTO> findVehiculeById(@PathVariable Long id) {
        return vehiculeService.findVehiculeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<VehiculeResponseDTO>> findByStatut(@PathVariable StatutVehicule statut) {
        return ResponseEntity.ok(vehiculeService.findByStatut(statut));
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<VehiculeResponseDTO>> findByCategorie(@PathVariable CategorieVehicule categorie) {
        return ResponseEntity.ok(vehiculeService.findByCategorie(categorie));
    }

    @GetMapping("/marque/{marque}")
    public ResponseEntity<List<VehiculeResponseDTO>> findByMarque(@PathVariable String marque) {
        return ResponseEntity.ok(vehiculeService.findByMarque(marque));
    }
}
