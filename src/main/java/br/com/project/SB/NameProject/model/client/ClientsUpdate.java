package br.com.project.SB.NameProject.model.client;

import br.com.project.SB.NameProject.model.AdressesData;
import br.com.project.SB.NameProject.model.AdressesEmbeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record ClientsUpdate(
        String name,
        @Pattern(regexp = "\\d{11}") String cpf,
        String cnpj,
        String phone,
        String birth,
        @Email
        String email,
        AdressesData adress,
        Boolean status) {

}

