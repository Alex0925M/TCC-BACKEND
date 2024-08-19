package br.com.project.SB.NameProject.models.jobs;

import br.com.project.SB.NameProject.model.intern.Intern;
import br.com.project.SB.NameProject.models.employe.Employees;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Table(name = "jobs")
@Entity(name = "job")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jobs extends RepresentationModel<Jobs> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String serviceType;

    private Employees employe;

    public Intern intern;

    private Boolean ativo;
    public void delete (){
        this.ativo = false;
    }

    public void update (JobsUpdate data){
        if (data.serviceType() != null){
            this.serviceType = data.serviceType();
        }
        if (data.intern() != null){
            this.intern = data.intern();
        }
        if (data.epmloye() != null){
            this.employe = data.epmloye();
        }
    }

    public Jobs(JobsDto data){
        this.serviceType = data.serviceType();
        this.intern = data.intern();
        this.employe = data.epmloye();
        this.ativo = true;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
