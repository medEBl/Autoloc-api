package tn.esprit.autoloc.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.web.dto.ClientRequestDTO;
import tn.esprit.autoloc.web.dto.ClientResponseDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponseDTO toDto(Client client);

    List<ClientResponseDTO> toDtoList(List<Client> clients);

    @Mapping(target = "idClient", ignore = true)
    @Mapping(target = "dateInscription", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Client toEntity(ClientRequestDTO dto);

    /** Met à jour une entité existante : id, dateInscription et réservations sont conservés. */
    @InheritConfiguration(name = "toEntity")
    void updateEntity(ClientRequestDTO dto, @MappingTarget Client client);
}
