package br.com.project.SB.NameProject.service;

import br.com.project.SB.NameProject.model.jobs.Job;
import br.com.project.SB.NameProject.model.jobs.JobDto;
import br.com.project.SB.NameProject.model.jobs.JobUpdate;
import br.com.project.SB.NameProject.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    JobRepository repository;

    public List<Job> getAll(){
        return repository.findAll();
    }

    public Optional<Job> getById(UUID id){
        if (!repository.existsById(id)){
            throw new EntityNotFoundException("ID inexistente.");
        }
        return repository.findById(id);
    }

    public Job update(UUID id, JobUpdate jobsUpdate){
        return repository.getReferenceById(id).update(jobsUpdate);
    }

    public Job create (JobDto job){
        return repository.save(new Job(job));
    }

    public void delete(UUID id){
        repository.getReferenceById(id).delete();
    }

}
