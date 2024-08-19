package br.com.project.SB.NameProject.repository;

import br.com.project.SB.NameProject.model.jobs.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
}
