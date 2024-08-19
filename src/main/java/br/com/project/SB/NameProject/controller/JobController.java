package br.com.project.SB.NameProject.controller;

import br.com.project.SB.NameProject.model.jobs.Job;
import br.com.project.SB.NameProject.model.jobs.JobDto;
import br.com.project.SB.NameProject.model.jobs.JobUpdate;
import br.com.project.SB.NameProject.repository.JobRepository;
import br.com.project.SB.NameProject.service.JobService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("authenticated/jobs")
public class JobController {

    @Autowired
    JobService service;

    @GetMapping
    public ResponseEntity<List<Job>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Job>> getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Job> create (@RequestBody @Valid JobDto jobs){
        var created = service.create(jobs);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Job>> delete (@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Job> update (@PathVariable @Value ("id") UUID id, @RequestBody @Valid JobUpdate jobsUpdate){
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.status(HttpStatus.OK).location(location).body((service.update(id, jobsUpdate)));
    }

}
