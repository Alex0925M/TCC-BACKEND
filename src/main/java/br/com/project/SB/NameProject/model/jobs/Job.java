package br.com.project.SB.NameProject.model.jobs;

import br.com.project.SB.NameProject.model.employe.Employe;
import br.com.project.SB.NameProject.model.intern.Intern;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Table(name = "jobs")
@Entity(name = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job extends RepresentationModel<Job> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String serviceType;

    @OneToOne
    private Employe employe;

    //@OneToOne
    // private Intern intern;

    private Boolean active;

    public void delete (){
        this.active = false;
    }

    public Job update (JobUpdate data){
        if (data.serviceType() != null){
            this.serviceType = data.serviceType();
        }
//4

        if (data.employe() != null){
            this.employe = new Employe(data.employe());
        }
        return this;
    }

    public Job(JobDto data){
        this.serviceType = data.serviceType();
        // this.intern = new Intern(data.intern());
        this.employe = new Employe(data.employe());
        this.active = true;
    }

}
