package br.com.project.SB.NameProject.models.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users extends RepresentationModel<Users> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    private String password;

    private Boolean ativo;

    public Users(UsersDto data){
        this.username = data.username();
        this.password = data.password();

        this.ativo = true;
    }

    public void updateUsers(UsersUpdate data){
        if (data.username() != null){
            this.username = data.username();
        }
        if (data.password() != null){
            this.password = data.password();
        }
    }

    public Boolean getAtivo(){
        return ativo;
    }

    public String getUsername(){
        return username;
    }
    public void delete(){
        this.ativo = false;
    }

}
