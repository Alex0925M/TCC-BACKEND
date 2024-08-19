package br.com.project.SB.NameProject.controller;

import br.com.project.SB.NameProject.models.employe.Employees;
import br.com.project.SB.NameProject.models.employe.EmployeesDto;
import br.com.project.SB.NameProject.models.employe.EmployeesUpdate;
import br.com.project.SB.NameProject.repository.EmployeesRepository;
import br.com.project.SB.NameProject.service.EmployeesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("employees")
public class EmployeesController {

    @Autowired
    EmployeesService service;

    @Autowired
    EmployeesRepository repository;

    @GetMapping
    public ResponseEntity<List<Employees>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Employees>> getAllByAtivo (){
        List<Employees> activeEmployes = service.getAllByAtivo();
        return ResponseEntity.status(HttpStatus.OK).body(activeEmployes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Employees>> getById(@PathVariable UUID id){
        var employee = service.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Employees> delete(@PathVariable UUID id){
        var employee = repository.getReferenceById(id);
        employee.delete();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Employees> create (@RequestBody EmployeesDto employees){
        var newEmployee = new Employees(employees);
        service.create(newEmployee);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEmployee.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(newEmployee);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Employees> update (@RequestBody EmployeesUpdate employeesUpdate){
        var employee = repository.getReferenceById(employeesUpdate.id());
        employee.updateEmployees(employeesUpdate);
        service.create(employee);
        employee.setId(employeesUpdate.id());
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeesUpdate.id()).toUri();

        return ResponseEntity.status(HttpStatus.OK).location(location).body(employee);
    }

}
