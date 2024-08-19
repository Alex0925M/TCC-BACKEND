package br.com.project.SB.NameProject.model.client;
import br.com.project.SB.NameProject.model.AdressesEmbeddable;
import br.com.project.SB.NameProject.model.company.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "Clients")
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Clients extends RepresentationModel<Clients> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "Um CPF deve conter 11 digitos.")
    @Column(unique = true)
    private String cpf;
    private String cnpj;
    private String name;
    private String phone;
    private String birth;
    @OneToMany
    private List<Company> company;
    @Column(unique = true)
    @Email(message = "O usuario a ser cadastrado deve conter um e-mail válido.")
    private String email;
    private boolean ativo;

    @Embedded
    private AdressesEmbeddable adresses;

    private Boolean status;

    public void delete(){
        this.status = false;
    }

    public void restore() {
        this.ativo = true;
    }

    public Clients(ClientsDto data) {
        this.ativo = true;
        this.cpf = data.cpf();
        this.cnpj = data.cnpj();
        this.name = data.name();
        this.phone = data.phone();
        this.birth = data.birth();
        this.email = data.email();
        this.adresses = new AdressesEmbeddable(data.adress());
    }

    public Clients updateClients(ClientsUpdate data){
        if (data.cpf() != null){
            this.cpf = data.cpf();
        }
        if (data.cnpj() != null){
            this.cnpj = data.cnpj();
        }
        if (data.name() != null){
            this.name = data.name();
        }
        if (data.phone() != null){
            this.phone = data.phone();
        }
        if (data.birth() != null){
            this.birth = data.birth();
        }
        if (data.email() != null){
            this.email = data.email();
        }
        if (data.adress() != null){
            this.adresses = new AdressesEmbeddable(data.adress());
        }
        if(data.status() != null){
            this.status = data.status();
        }
        return this;
    }

    public boolean getAtivo() {
        return ativo;
    }
}
