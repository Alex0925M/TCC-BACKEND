package br.com.project.SB.NameProject.model;

import jakarta.validation.constraints.Pattern;

public record AdressesData(Integer number, String street, String district, @Pattern(regexp = "\\d{8}") Integer zipcode, String city) {

}
