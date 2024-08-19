package br.com.project.SB.NameProject.repository;

import br.com.project.SB.NameProject.model.intern.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InternRepository extends JpaRepository<Intern, UUID> {
}
