package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.mapper.ClientMapper;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.web.dto.ClientRequestDTO;
import tn.esprit.autoloc.web.dto.ClientResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponseDTO addClient(ClientRequestDTO dto) {
        Client client = clientMapper.toEntity(dto);
        client.setDateInscription(LocalDate.now());
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Override
    @Transactional
    public Optional<ClientResponseDTO> updateClient(Long id, ClientRequestDTO dto) {
        return clientRepository.findById(id).map(client -> {
            clientMapper.updateEntity(dto, client);
            return clientMapper.toDto(clientRepository.save(client));
        });
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientResponseDTO> findAllClients() {
        return clientMapper.toDtoList(clientRepository.findAll());
    }

    @Override
    public Optional<ClientResponseDTO> findClientById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }
}
