package br.com.project.SB.NameProject.service;

import br.com.project.SB.NameProject.infra.exceptions.AtivoIsFalseException;
import br.com.project.SB.NameProject.model.client.Clients;
import br.com.project.SB.NameProject.repository.ClientsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository repository;

    public List<Clients> getAll(){
        return repository.findAll();
    }

    public List<Clients> getAllByAtivo(){
        List<Clients> clientsList = repository.findAll();

        return clientsList.stream().filter(Clients::getStatus).collect(Collectors.toList());
    }

    public Optional<Clients> getById(UUID id){
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("ID inexistente");
        } else if (!repository.getReferenceById(id).getStatus()) {
            throw new AtivoIsFalseException(id);
        }

        return repository.findById(id);
    }

    public Clients create(Clients clients){
        return repository.save(clients);
    }

}
