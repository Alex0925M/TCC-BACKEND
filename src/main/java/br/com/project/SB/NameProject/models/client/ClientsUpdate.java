package br.com.project.SB.NameProject.models.client;

import br.com.project.SB.NameProject.enums.QualificationsEnum;
import br.com.project.SB.NameProject.enums.SegmentsEnum;
import br.com.project.SB.NameProject.model.AdressesEmbeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record ClientsUpdate(
        @NotNull
        UUID id,
        @Pattern(regexp = "\\d{11}") String cpf,
        String phone,
        String birth,
        @Email
        String email,
        AdressesEmbeddable adress,
        String name,
        Boolean status
        ) {

}

