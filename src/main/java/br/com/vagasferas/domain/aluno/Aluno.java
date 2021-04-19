package br.com.vagasferas.domain.aluno;

import br.com.vagasferas.domain.skill.Skill;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@JsonPropertyOrder({"id", "ra", "nome_completo", "skills" })
@Entity
@Table(name = "aluno")
public class Aluno extends PanacheEntityBase {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "O nome deve ser preenchido")
    @Size(min = 10, message = "Nome muito curto")
    @JsonProperty("nome_completo")
    private String nome_completo;

    @Min(1)
    @JsonProperty("ra")
    private Long ra;
    public Long getId() {
        return id;
    }

    @JsonProperty("skills")
    @ManyToMany(
            targetEntity = Skill.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH)
    @JoinTable(
            name = "aluno_skills",
            joinColumns = @JoinColumn(name = "id_skill", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_aluno_id_skill")),
            inverseJoinColumns = @JoinColumn(name = "id_aluno", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_skill_id_aluno")))
    private List<Skill> skills;

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> requisitos) {
        this.skills = requisitos;
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }
}
