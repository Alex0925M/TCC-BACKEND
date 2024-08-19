package br.com.project.SB.NameProject.service;

import br.com.project.SB.NameProject.enums.QualificationsEnum;
import br.com.project.SB.NameProject.enums.SegmentsEnum;
import br.com.project.SB.NameProject.model.company.Company;
import br.com.project.SB.NameProject.model.company.CompanyDto;
import br.com.project.SB.NameProject.model.company.CompanyUpdate;
import br.com.project.SB.NameProject.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public List<Company> getAll() {
        return repository.findAll();
    }

    public List<Company> getAllByActive() {
        List<Company> companyList = repository.findAll();

        return companyList.stream().filter(Company::getActive).collect(Collectors.toList());
    }

    public Optional<Company> getById(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("ID inexistente");
        }
        return repository.findById(id);
    }

    public Company update(UUID id, CompanyUpdate companyUpdate) {
        return repository.getReferenceById(id).updateCompany(companyUpdate);
    }

    public Company create(CompanyDto company) {
        return repository.save(new Company(company));
    }

    public void delete(UUID id){
        repository.getReferenceById(id).delete();
    }

    public void restore(UUID id) {
        repository.getReferenceById(id).restore();
    }

    public List<SegmentsEnum> getSegments() {
        return Arrays.asList(SegmentsEnum.values());
    }

    public List<QualificationsEnum> getQualifications() {
        return Arrays.asList(QualificationsEnum.values());
    }
}
