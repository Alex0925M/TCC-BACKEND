package br.com.project.SB.NameProject.models.employe;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeesDto(
        @NotBlank
        String name,
        @NotBlank
        String cpf,
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        String birth
) {}
