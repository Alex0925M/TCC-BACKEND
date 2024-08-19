package br.com.project.SB.NameProject.service;

import br.com.project.SB.NameProject.infra.exceptions.AtivoIsFalseException;
import br.com.project.SB.NameProject.model.employe.Employe;
import br.com.project.SB.NameProject.model.employe.EmployeDto;
import br.com.project.SB.NameProject.model.employe.EmployeUpdate;
import br.com.project.SB.NameProject.repository.EmployeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeService {

    @Autowired
    EmployeRepository repository;

    public List<Employe> getAll(){
        return repository.findAll();
    }
    
    public List<Employe> getAllByAtivo(){
        List<Employe> employeesList = repository.findAll();
        
        return employeesList.stream().filter(Employe::getActive).collect(Collectors.toList());
    }

    public Optional<Employe> getById(UUID id){
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("ID Inexistente");
        }
        return repository.findById(id);
    }

    public Employe create(EmployeDto employe) {
        return repository.save(new Employe(employe));
    }

    public Employe update(UUID id, EmployeUpdate employeUpdate) {
        return repository.getReferenceById(id).updateEmployees(employeUpdate);
    }

    public void delete(UUID id) {
        repository.getReferenceById(id).delete();
    }

    public void restore(UUID id) {
        repository.getReferenceById(id).restore();
    }
}
