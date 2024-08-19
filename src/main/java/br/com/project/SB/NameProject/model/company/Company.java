package br.com.project.SB.NameProject.model.company;

import br.com.project.SB.NameProject.enums.QualificationsEnum;
import br.com.project.SB.NameProject.enums.SegmentsEnum;
import br.com.project.SB.NameProject.model.client.Clients;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Company extends RepresentationModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "\\d{14}", message = "Um CNPJ deve conter 14 digitos.")
    private String cnpj;
    private String companyName;
    @Enumerated(EnumType.STRING)
    private SegmentsEnum segment;
    @Enumerated(EnumType.STRING)
    private QualificationsEnum qualifications;
    private Boolean active;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfCreation;

    @ManyToOne
    private Clients clients;

    public void delete(){
        this.active = false;
    }

    public void restore() {
        this.active = true;
    }

    public Company(CompanyDto data){
        this.active = true;
        this.cnpj = data.cnpj();
        this.companyName = data.companyName();
        this.segment = data.segment();
        this.qualifications = data.qualifications();
        this.clients = new Clients(data.clientsDto());
        this.dateOfCreation = data.dateOfCreation();
    }

    public Company updateCompany(CompanyUpdate data){
        if (data.cnpj() != null){
            this.cnpj = data.cnpj();
        }
        if(data.companyName() != null){
            this.companyName = data.companyName();
        }
        if (data.segment() != null){
            this.segment = data.segment();
        }
        if (data.qualifications() != null){
            this.qualifications = data.qualifications();
        }
        if (data.clients() != null){
            this.clients = new Clients(data.clients());
        }

        return this;
    }
}

