package br.com.project.SB.NameProject.models.users;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UsersUpdate(
        UUID id,
        @NotBlank
        String username,
        @NotBlank
        String password,
        Boolean ativo
) {
}
