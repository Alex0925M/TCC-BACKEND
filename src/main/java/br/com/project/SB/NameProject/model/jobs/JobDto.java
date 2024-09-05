package br.com.project.SB.NameProject.model.jobs;

import br.com.project.SB.NameProject.model.employe.EmployeDto;
import br.com.project.SB.NameProject.model.intern.InternDto;

import java.util.UUID;

// Define JobDto as a record with the fields corresponding to Job attributes
public record JobDto(
        UUID id, // Adding id for completeness, optional
        String serviceType,
        EmployeDto employe,
        InternDto intern, // Added InternDto
        Boolean active
) {
}

