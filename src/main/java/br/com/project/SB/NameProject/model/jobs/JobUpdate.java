package br.com.project.SB.NameProject.model.jobs;

import br.com.project.SB.NameProject.model.employe.Employe;
import br.com.project.SB.NameProject.model.employe.EmployeDto;
import br.com.project.SB.NameProject.model.intern.Intern;
import br.com.project.SB.NameProject.model.intern.InternDto;

import java.util.UUID;

public record JobUpdate(String serviceType, EmployeDto employe, InternDto intern) {
}
