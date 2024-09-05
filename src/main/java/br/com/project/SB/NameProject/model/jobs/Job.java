package br.com.project.SB.NameProject.model.jobs;

import br.com.project.SB.NameProject.model.client.Clients;
import br.com.project.SB.NameProject.model.company.Company;
import br.com.project.SB.NameProject.model.employe.Employe;
import br.com.project.SB.NameProject.model.intern.Intern;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String serviceType;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    private Intern intern;

    @ManyToMany
    @JoinTable(
            name = "job_company",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<Company> companies;

    @ManyToMany
    @JoinTable(
            name = "job_client",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Clients> clients;

    private Boolean active;

    public void delete() {
        this.active = false;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public Job update(JobUpdate data) {
        if (data.serviceType() != null) {
            this.serviceType = data.serviceType();
        }

        if (data.employe() != null) {
            this.employe = new Employe(data.employe()); // Assuming EmployeDto is converted to Employe
        }

        if (data.intern() != null) {
            // Assuming Intern should also be updated
            this.intern = new Intern(data.intern()); // Convert InternDto to Intern if needed
        }

        return this;
    }

    public Job(JobDto data) {
        this.serviceType = data.serviceType();
        this.employe = new Employe(data.employe()); // Convert EmployeDto to Employe if needed
        this.active = true;
    }
}
