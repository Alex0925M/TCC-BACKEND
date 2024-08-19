package br.com.project.SB.NameProject.controller;

import br.com.project.SB.NameProject.enums.SegmentsEnum;
import br.com.project.SB.NameProject.model.client.Clients;
import br.com.project.SB.NameProject.model.client.ClientsDto;
import br.com.project.SB.NameProject.model.client.ClientsUpdate;
import br.com.project.SB.NameProject.service.ClientService;
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
@RequestMapping("authenticated/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clients>> getById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Clients>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Clients>> getAllAtivoByTrue() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllByAtivo());
    }

    @PostMapping
    public ResponseEntity<Clients> create(@RequestBody @Valid ClientsDto clients) {
        var created = service.create(clients);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(created);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Clients> update(@PathVariable @Value("id") UUID id, @RequestBody @Valid ClientsUpdate clients) {
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.status(HttpStatus.OK).location(location).body(service.update(id, clients));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Optional<Clients>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("restore/{id}")
    @Transactional
    public ResponseEntity<Optional<Clients>> restore(@PathVariable UUID id) {
        service.restore(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

