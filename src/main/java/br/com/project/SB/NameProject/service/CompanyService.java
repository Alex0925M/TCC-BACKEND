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

    public Company getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa com ID " + id + " não encontrada."));
    }

    public Company update(UUID id, CompanyUpdate companyUpdate) {
        Company company = getById(id); // Verifica se a empresa existe
        company.updateCompany(companyUpdate);
        return repository.save(company); // Salva as alterações
    }

    public Company create(CompanyDto companyDto) {
        Company company = new Company(companyDto);
        return repository.save(company);
    }

    public void delete(UUID id) {
        Company company = getById(id); // Verifica se a empresa existe
        company.delete();
        repository.save(company); // Atualiza o status no banco de dados
    }

    public void restore(UUID id) {
        Company company = getById(id); // Verifica se a empresa existe
        company.restore();
        repository.save(company); // Atualiza o status no banco de dados
    }

    public List<SegmentsEnum> getSegments() {
        return Arrays.asList(SegmentsEnum.values());
    }

    public List<QualificationsEnum> getQualifications() {
        return Arrays.asList(QualificationsEnum.values());
    }
}
