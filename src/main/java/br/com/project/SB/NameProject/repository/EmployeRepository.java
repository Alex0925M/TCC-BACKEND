package br.com.project.SB.NameProject.repository;

import br.com.project.SB.NameProject.model.employe.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeRepository extends JpaRepository<Employe, UUID> {
}
