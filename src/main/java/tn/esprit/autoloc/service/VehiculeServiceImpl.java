package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.mapper.VehiculeMapper;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.web.dto.VehiculeRequestDTO;
import tn.esprit.autoloc.web.dto.VehiculeResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public VehiculeResponseDTO addVehicule(VehiculeRequestDTO dto) {
        Vehicule vehicule = vehiculeMapper.toEntity(dto);
        return vehiculeMapper.toDto(vehiculeRepository.save(vehicule));
    }

    @Override
    @Transactional
    public Optional<VehiculeResponseDTO> updateVehicule(Long id, VehiculeRequestDTO dto) {
        return vehiculeRepository.findById(id).map(vehicule -> {
            vehiculeMapper.updateEntity(dto, vehicule);
            return vehiculeMapper.toDto(vehiculeRepository.save(vehicule));
        });
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public List<VehiculeResponseDTO> findAllVehicules() {
        return vehiculeMapper.toDtoList(vehiculeRepository.findAll());
    }

    @Override
    public Optional<VehiculeResponseDTO> findVehiculeById(Long id) {
        return vehiculeRepository.findById(id).map(vehiculeMapper::toDto);
    }

    @Override
    public List<VehiculeResponseDTO> findByStatut(StatutVehicule statut) {
        return vehiculeMapper.toDtoList(vehiculeRepository.findByStatut(statut));
    }

    @Override
    public List<VehiculeResponseDTO> findByCategorie(CategorieVehicule categorie) {
        return vehiculeMapper.toDtoList(vehiculeRepository.findByCategorie(categorie));
    }

    @Override
    public List<VehiculeResponseDTO> findByMarque(String marque) {
        return vehiculeMapper.toDtoList(vehiculeRepository.findByMarque(marque));
    }
}
