package br.com.project.SB.NameProject.models.users;

import jakarta.validation.constraints.NotBlank;

public record UsersDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        Boolean ativo
) {
}
