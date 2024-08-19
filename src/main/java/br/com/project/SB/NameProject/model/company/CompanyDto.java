package br.com.project.SB.NameProject.model.company;

import br.com.project.SB.NameProject.enums.QualificationsEnum;
import br.com.project.SB.NameProject.enums.SegmentsEnum;
import br.com.project.SB.NameProject.model.client.ClientsDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CompanyDto (
        @NotBlank
        @Pattern(regexp = "\\d{14}", message = "Um CNPJ deve conter 14 digitos.")
        String cnpj,
        SegmentsEnum segment,
        QualificationsEnum qualifications,
        String companyName,
        LocalDate dateOfCreation,
        ClientsDto clientsDto
){


}
