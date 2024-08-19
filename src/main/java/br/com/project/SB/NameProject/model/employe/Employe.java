package br.com.project.SB.NameProject.model.employe;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employe extends RepresentationModel<Employe> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String name;
    @Email
    private String email;

    @Pattern(regexp = "\\d{11}", message = "Um CPF deve conter 11 digitos.")
    @Column(unique = true)
    private String cpf;

    private String telephone;

    private Boolean active;

    public void delete(){
        this.active = false;
    }

    public void restore(){
        this.active = true;
    }

    public Employe(EmployeDto data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.telephone = data.telephone();
        this.active = true;
    }

    public Employe updateEmployees(EmployeUpdate data){
        if (data.name() != null){
            this.name = data.name();
        }
        if (data.email() != null){
            this.email = data.email();
        }
        if (data.cpf() != null){
            this.cpf = data.cpf();
        }
        if (data.telephone() != null){
            this.telephone = data.telephone();
        }
        return this;
    }
}
