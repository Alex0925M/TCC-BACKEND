package br.com.project.SB.NameProject.models.jobs;

import br.com.project.SB.NameProject.model.employe.Employe;
import br.com.project.SB.NameProject.model.intern.Intern;
import br.com.project.SB.NameProject.models.employe.Employees;
import jakarta.validation.constraints.NotBlank;

public record JobsDto(
        @NotBlank
        String serviceType,
        Employees epmloye,
        Intern intern,
        Boolean ativo

) {}
