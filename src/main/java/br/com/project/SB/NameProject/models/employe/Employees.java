package br.com.project.SB.NameProject.models.employe;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Data
public class Employees extends RepresentationModel implements Serializable {

    @Id
    private UUID id;
    @NotBlank
    private String name;
    @Email
    private String email;
    private String cpf;
    private String phone;
    private String birth;
    private Boolean status;

    public void delete(){
        this.status = false;
    }

    public Employees() {}

    public Employees(@Valid EmployeesDto data) {
        this.id = UUID.randomUUID();
        this.name = data.name();
        this.cpf = data.cpf();
        this.email = data.email();
        this.phone = data.phone();
        this.birth = data.birth();
        this.status = true;
    }

    public void updateEmployees(EmployeesUpdate data){
        if (data.name() != null){
            this.name = data.name();
        }

        if (data.cpf() != null){
            this.cpf = data.cpf();
        }

        if (data.email() != null){
            this.email = data.email();
        }

        if (data.phone() != null) {
            this.phone = data.phone();
        }

        if (data.birth() != null) {
            this.birth = data.birth();
        }

        if (data.status() != null){
            this.status = data.status();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getStatus(){
        return status;
    }
}
