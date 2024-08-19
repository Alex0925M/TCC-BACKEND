package br.com.project.SB.NameProject.service;

import br.com.project.SB.NameProject.infra.exceptions.AtivoIsFalseException;
import br.com.project.SB.NameProject.models.employe.Employees;
import br.com.project.SB.NameProject.repository.EmployeesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    @Autowired
    EmployeesRepository repository;

    public List<Employees> getAll(){
        return repository.findAll();
    }
    
    public List<Employees> getAllByAtivo(){
        List<Employees> employeesList = repository.findAll();
        
        return employeesList.stream().filter(Employees::getStatus).collect(Collectors.toList());
    }

    public Optional<Employees> getById(UUID id){
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("ID Inexistente");
        } else if (!repository.getReferenceById(id).getStatus()) {
            throw new AtivoIsFalseException(id);
        }

        return repository.findById(id);
    }

    public Employees create(Employees employees){
        return repository.save(employees);
    }

}
