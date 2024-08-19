package br.com.project.SB.NameProject.repository;

import br.com.project.SB.NameProject.models.employe.Employees;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface EmployeesRepository extends JpaRepository<Employees, UUID> {

}

