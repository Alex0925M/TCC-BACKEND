package br.com.project.SB.NameProject.repository;

import br.com.project.SB.NameProject.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByUsername(String username);
}
