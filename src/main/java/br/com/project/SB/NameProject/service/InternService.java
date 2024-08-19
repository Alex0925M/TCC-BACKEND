package br.com.project.SB.NameProject.service;

import br.com.project.SB.NameProject.model.intern.Intern;
import br.com.project.SB.NameProject.model.intern.InternDto;
import br.com.project.SB.NameProject.model.intern.InternUpdate;
import br.com.project.SB.NameProject.repository.InternRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InternService {

    @Autowired
    private InternRepository repository;

    public List<Intern> getAll(){
        return repository.findAll();
    }

    public Optional<Intern> getById(UUID id){
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("ID inexistente");
        }
        return repository.findById(id);
    }

    public List<Intern> getAllByActive(){
        List<Intern> internList = repository.findAll();

        return internList.stream().filter(Intern::getAtivo).collect(Collectors.toList());
    }

    public Intern create(InternDto intern){
        return repository.save(new Intern(intern));
    }

    public Intern update(UUID id, InternUpdate internUpdate){
        return repository.getReferenceById(id).update(internUpdate);
    }

    public void delete(UUID id){
        repository.getReferenceById(id).delete();
    }

}
