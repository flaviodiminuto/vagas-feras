package br.com.vagasferas.domain.vaga;

import br.com.vagasferas.domain.area.Area;
import br.com.vagasferas.domain.nivel.Nivel;
import br.com.vagasferas.domain.skill.Skill;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@JsonPropertyOrder({"id", "titulo", "descricao", "area", "nivel",
        "remuneracao_minima", "remuneracao_maxima", "requisitos", "desejaveis"})
@Entity
@Table(name = "vaga")
public class Vaga extends PanacheEntityBase {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JsonProperty("titulo")
    @NotBlank(message = "O campo 'titulo' deve ser preenchido")
    private String titulo;

    @JsonProperty("descricao")
    @NotBlank(message = "O campo 'descricao' deve ser preenchido")
    @Size(max = 500, message = "Descricao deve conter até {max} caracteres")
    @Column(name = "descricao", length = 500)
    private String descricao;

    @JsonProperty("area")
    @NotNull(message = "O campo 'area' deve ser preenchido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area", nullable = false, foreignKey = @ForeignKey(name = "FK_id_area"))
    private Area area;

    @JsonProperty("nivel")
    @NotNull(message = "O campo 'nivel' deve ser preenchido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel", nullable = false,  foreignKey = @ForeignKey(name = "FK_id_nivel"))
    private Nivel nivel;

    @JsonProperty("remuneracao_minima")
    @Min(value = 0, message = "A remuneracao mínima não pode ser negativa")
    @NotNull(message = "O campo 'remuneracao_minima' deve ser preenchido")
    private Double remuneracao_minima;

    @JsonProperty("remuneracao_maxima")
    private Double remumeracao_maxima;

    @JsonProperty("requisitos")
    @ManyToMany(
            targetEntity = Skill.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH)
    @JoinTable(
            name = "vaga_skill_requisito",
                joinColumns = @JoinColumn(name = "id_skill", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_requisito_id_skill")),
                inverseJoinColumns = @JoinColumn(name = "id_vaga", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_requisito_id_vaga")))
    private List<Skill> requisitos;

    @JsonProperty("desejaveis")
    @ManyToMany(
            targetEntity = Skill.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "vaga_skill_desejavel",
            joinColumns = @JoinColumn(name = "id_skill", referencedColumnName = "id",  foreignKey = @ForeignKey(name = "FK_desejavel_id_skill")),
            inverseJoinColumns = @JoinColumn(name = "id_vaga", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_desejavel_id_vaga")))
    private List<Skill> desejaveis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Double getremuneracao_minima() {
        return remuneracao_minima;
    }

    public void setremuneracao_minima(Double remuneracao_minima) {
        this.remuneracao_minima = remuneracao_minima;
    }

    public Double getremumeracao_maxima() {
        return remumeracao_maxima;
    }

    public void setremumeracao_maxima(Double remumeracao_maxima) {
        this.remumeracao_maxima = remumeracao_maxima;
    }

    public List<Skill> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<Skill> requisitos) {
        this.requisitos = requisitos;
    }

    public List<Skill> getDesejaveis() {
        return desejaveis;
    }

    public void setDesejaveis(List<Skill> desejaveis) {
        this.desejaveis = desejaveis;
    }
}
