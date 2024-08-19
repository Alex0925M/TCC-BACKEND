package br.com.project.SB.NameProject.controller;

import br.com.project.SB.NameProject.model.company.Company;
import br.com.project.SB.NameProject.model.employe.Employe;
import br.com.project.SB.NameProject.model.employe.EmployeDto;
import br.com.project.SB.NameProject.model.employe.EmployeUpdate;
import br.com.project.SB.NameProject.repository.EmployeRepository;
import br.com.project.SB.NameProject.service.EmployeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("authenticated/employe")
public class EmployeController {

    @Autowired
    EmployeService service;

    @GetMapping
    public ResponseEntity<List<Employe>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Employe>> getAllByAtivo (){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllByAtivo());
    }


    @GetMapping("{id}")
    public ResponseEntity<Optional<Employe>> getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Employe> create (@RequestBody EmployeDto employees){
        var created = service.create(employees);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(created);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Employe> update (@PathVariable @Value ("id") UUID id, @RequestBody EmployeUpdate employeUpdate){

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.status(HttpStatus.OK).location(location).body(service.update(id, employeUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Optional<Employe>> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/restore/{id}")
    @Transactional
    public ResponseEntity<Optional<Employe>> restore(@PathVariable UUID id) {
        service.restore(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
