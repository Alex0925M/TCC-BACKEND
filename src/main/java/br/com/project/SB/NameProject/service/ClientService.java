package br.com.project.SB.NameProject.service;

import br.com.project.SB.NameProject.model.client.Clients;
import br.com.project.SB.NameProject.model.client.ClientsDto;
import br.com.project.SB.NameProject.model.client.ClientsUpdate;
import br.com.project.SB.NameProject.repository.ClientsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientsRepository repository;

    public List<Clients> getAll() {
        return repository.findAll();
    }

    public List<Clients> getAllByAtivo() {
        List<Clients> clientsList = repository.findAll();

        return clientsList.stream().filter(Clients::getAtivo).collect(Collectors.toList());
    }

    public Optional<Clients> getById(UUID id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("ID inexistente.");
        return repository.findById(id);
    }

    public Clients create(ClientsDto clients) {
        return repository.save(new Clients(clients));
    }

    public Clients update(UUID id, ClientsUpdate clientsUpdate) {
        return repository.getReferenceById(id).updateClients(clientsUpdate);
    }

    public void delete(UUID id) {
        repository.getReferenceById(id).delete();
    }

    public void restore(UUID id) {
        repository.getReferenceById(id).restore();
    }
}
