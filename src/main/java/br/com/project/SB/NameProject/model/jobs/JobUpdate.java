package br.com.project.SB.NameProject.model.jobs;

import br.com.project.SB.NameProject.model.employe.EmployeDto;
import br.com.project.SB.NameProject.model.intern.InternDto;

import java.util.UUID;

// Define JobUpdate as a record to encapsulate update data
public record JobUpdate(
        String serviceType,
        EmployeDto employe,
        InternDto intern
) {
}
