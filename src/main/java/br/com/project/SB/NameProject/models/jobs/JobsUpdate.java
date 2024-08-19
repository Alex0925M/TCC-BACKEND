package br.com.project.SB.NameProject.models.jobs;

import br.com.project.SB.NameProject.model.employe.Employe;
import br.com.project.SB.NameProject.model.intern.Intern;
import br.com.project.SB.NameProject.models.employe.Employees;

import java.util.UUID;

public record JobsUpdate(
        UUID id,
        String serviceType,
        Employees epmloye,
        Intern intern,
        Boolean ativo
) {
}
