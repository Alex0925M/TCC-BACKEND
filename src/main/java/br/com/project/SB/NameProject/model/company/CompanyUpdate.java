package br.com.project.SB.NameProject.model.company;

import br.com.project.SB.NameProject.enums.QualificationsEnum;
import br.com.project.SB.NameProject.enums.SegmentsEnum;
import br.com.project.SB.NameProject.model.client.ClientsDto;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;


public record CompanyUpdate(
        @Pattern(regexp = "\\d{14}") String cnpj,
        SegmentsEnum segment,
        QualificationsEnum qualifications,
        String companyName,
        LocalDate dateOfCreation,
        ClientsDto clients
        ) {


}
