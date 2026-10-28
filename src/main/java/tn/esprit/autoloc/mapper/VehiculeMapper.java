package tn.esprit.autoloc.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.web.dto.VehiculeRequestDTO;
import tn.esprit.autoloc.web.dto.VehiculeResponseDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    VehiculeResponseDTO toDto(Vehicule vehicule);

    List<VehiculeResponseDTO> toDtoList(List<Vehicule> vehicules);

    @Mapping(target = "idVehicule", ignore = true)
    @Mapping(target = "agence", ignore = true)
    @Mapping(target = "maintenances", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Vehicule toEntity(VehiculeRequestDTO dto);

    /** Met à jour une entité existante : les relations et l'id sont conservés. */
    @InheritConfiguration(name = "toEntity")
    void updateEntity(VehiculeRequestDTO dto, @MappingTarget Vehicule vehicule);
}
