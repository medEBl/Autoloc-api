package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.ClientRequestDTO;
import tn.esprit.autoloc.web.dto.ClientResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    ClientResponseDTO addClient(ClientRequestDTO dto);

    Optional<ClientResponseDTO> updateClient(Long id, ClientRequestDTO dto);

    void deleteClient(Long id);

    List<ClientResponseDTO> findAllClients();

    Optional<ClientResponseDTO> findClientById(Long id);
}
