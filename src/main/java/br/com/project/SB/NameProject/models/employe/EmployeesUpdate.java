package br.com.project.SB.NameProject.models.employe;

import java.util.UUID;

public record EmployeesUpdate(
        UUID id,
        String name,
        String cpf,
        String email,
        String phone,
        String birth,
        Boolean status
) {}
