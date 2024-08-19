package br.com.project.SB.NameProject.repository;


import br.com.project.SB.NameProject.model.client.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientsRepository extends JpaRepository<Clients, UUID> {
}
