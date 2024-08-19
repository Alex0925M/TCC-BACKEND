package br.com.project.SB.NameProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class AdressesEmbeddable {

    private Integer number;
    private String street;
    private String district;
    @Pattern(regexp = "\\d{8}")
    private Integer zipcode;
    private String city;

    public AdressesEmbeddable(AdressesData data){
        this.number = data.number();
        this.street = data.street();
        this.district = data.district();
        this.zipcode = data.zipcode();
        this.city = data.city();
    }

    public void updateAdresses(AdressesData data){
        if (data.number() != null){
            this.number = data.number();
        }
        if (data.street() != null){
            this.street = data.street();
        }
        if (data.district() != null){
            this.district = data.district();
        }
        if (data.zipcode() != null){
            this.zipcode = data.zipcode();
        }
        if (data.city() != null){
            this.city = data.city();
        }
    }

}