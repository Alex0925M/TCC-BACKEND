package br.com.project.SB.NameProject.controller;

import br.com.project.SB.NameProject.model.intern.Intern;
import br.com.project.SB.NameProject.model.intern.InternDto;
import br.com.project.SB.NameProject.model.intern.InternUpdate;
import br.com.project.SB.NameProject.repository.InternRepository;
import br.com.project.SB.NameProject.service.InternService;
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
@RequestMapping("authenticated/intern")
public class InternController {

    @Autowired
    InternService service;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Intern>> getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Intern>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Intern> create (@RequestBody @Valid InternDto intern){
        var created = service.create(intern);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(created);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Intern> update (@PathVariable @Value ("id") UUID id, @RequestBody @Valid InternUpdate internUpdate){
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.status(HttpStatus.OK).location(location).body(service.update(id, internUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Intern>> delete (@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
