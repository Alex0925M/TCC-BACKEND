package br.com.project.SB.NameProject.controller;

import br.com.project.SB.NameProject.enums.QualificationsEnum;
import br.com.project.SB.NameProject.enums.SegmentsEnum;
import br.com.project.SB.NameProject.model.company.Company;
import br.com.project.SB.NameProject.model.company.CompanyDto;
import br.com.project.SB.NameProject.model.company.CompanyUpdate;
import br.com.project.SB.NameProject.service.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("authenticated/company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        List<Company> companies = service.getAll();
        return ResponseEntity.ok(companies);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable UUID id) {
        try {
            Company company = service.getById(id); // Método service.getById já lança exceção se não encontrar
            return ResponseEntity.ok(company);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody @Valid CompanyDto companyDto) {
        Company createdCompany = service.create(companyDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCompany.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdCompany);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Company> update(@PathVariable UUID id, @RequestBody @Valid CompanyUpdate companyUpdate) {
        try {
            Company updatedCompany = service.update(id, companyUpdate);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();
            return ResponseEntity.ok().location(location).body(updatedCompany);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/restore/{id}")
    @Transactional
    public ResponseEntity<Void> restore(@PathVariable UUID id) {
        try {
            service.restore(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/segments")
    public ResponseEntity<List<SegmentsEnum>> getSegments() {
        List<SegmentsEnum> segments = Arrays.asList(SegmentsEnum.values());
        return ResponseEntity.ok(segments);
    }

    @GetMapping("/qualifications")
    public ResponseEntity<List<QualificationsEnum>> getQualifications() {
        List<QualificationsEnum> qualifications = Arrays.asList(QualificationsEnum.values());
        return ResponseEntity.ok(qualifications);
    }
}
