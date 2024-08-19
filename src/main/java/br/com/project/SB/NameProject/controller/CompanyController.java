package br.com.project.SB.NameProject.controller;

import br.com.project.SB.NameProject.enums.QualificationsEnum;
import br.com.project.SB.NameProject.enums.SegmentsEnum;
import br.com.project.SB.NameProject.model.company.Company;
import br.com.project.SB.NameProject.model.company.CompanyDto;
import br.com.project.SB.NameProject.model.company.CompanyUpdate;
import br.com.project.SB.NameProject.repository.CompanyRepository;
import br.com.project.SB.NameProject.service.CompanyService;
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
@RequestMapping("authenticated/company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Company>> getAllActiveByTrue() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllByActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Company>> getById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody @Valid CompanyDto company) {
        var created = service.create(company);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(created);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Company> update(@PathVariable @Value("id") UUID id, @RequestBody @Valid CompanyUpdate companyUpdate) {
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.status(HttpStatus.OK).location(location).body(service.update(id, companyUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Optional<Company>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/restore/{id}")
    @Transactional
    public ResponseEntity<Optional<Company>> restore(@PathVariable UUID id) {
        service.restore(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("/segments")
    public ResponseEntity<List<SegmentsEnum>> segments() {
        final List<SegmentsEnum> segmentsList = service.getSegments();
        return ResponseEntity.status(HttpStatus.OK).body(segmentsList);
    }

    @GetMapping("/qualifications")
    public ResponseEntity<List<QualificationsEnum>> qualifications() {
        final List<QualificationsEnum> qualificationsList = service.getQualifications();
        return ResponseEntity.status(HttpStatus.OK).body(qualificationsList);
    }
}
