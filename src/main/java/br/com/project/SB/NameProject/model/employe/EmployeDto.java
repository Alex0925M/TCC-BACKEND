package br.com.project.SB.NameProject.model.employe;

import java.util.UUID;

public record EmployeDto(UUID id, String name, String email, String cpf, String telephone) {
}
