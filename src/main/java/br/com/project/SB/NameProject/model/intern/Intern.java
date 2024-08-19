package br.com.project.SB.NameProject.model.intern;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "intern")
@Table(name = "intern")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Intern extends RepresentationModel<Intern> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email
    private String email;

    @NotBlank
    private String name;

    @Pattern(regexp = "\\d{11}", message = "Um CPF deve conter 11 digitos.")
    @Column(unique = true)
    private String cpf;

    private Boolean ativo;

    public void delete(){
        this.ativo = false;
    }

    public Intern(InternDto data){
        this.email = data.email();
        this.name = data.name();
        this.cpf = data.cpf();
        this.ativo = true;
    }

    public Intern update(InternUpdate data){
        if (data.email() != null){
            this.email = data.email();
        }
        if (data.name() != null){
            this.name = data.name();
        }
        if (data.cpf() != null){
            this.cpf = data.cpf();
        }
        return this;
    }

}
