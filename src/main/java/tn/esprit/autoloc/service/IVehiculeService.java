package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.web.dto.VehiculeRequestDTO;
import tn.esprit.autoloc.web.dto.VehiculeResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IVehiculeService {

    VehiculeResponseDTO addVehicule(VehiculeRequestDTO dto);

    Optional<VehiculeResponseDTO> updateVehicule(Long id, VehiculeRequestDTO dto);

    void deleteVehicule(Long id);

    List<VehiculeResponseDTO> findAllVehicules();

    Optional<VehiculeResponseDTO> findVehiculeById(Long id);

    List<VehiculeResponseDTO> findByStatut(StatutVehicule statut);

    List<VehiculeResponseDTO> findByCategorie(CategorieVehicule categorie);

    List<VehiculeResponseDTO> findByMarque(String marque);
}
