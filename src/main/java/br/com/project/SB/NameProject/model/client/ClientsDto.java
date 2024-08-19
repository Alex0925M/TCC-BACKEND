package br.com.project.SB.NameProject.model.client;

import br.com.project.SB.NameProject.model.AdressesData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientsDto(
        String name,
        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "Um CPF deve conter 11 digitos.")
        String cpf,
        String cnpj,
        String company,
        String phone,
        String birth,
        @Email String email,
        AdressesData adress,
        Boolean status) {

}
