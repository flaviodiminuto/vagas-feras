package br.com.vagasferas.domain.nivel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonPropertyOrder({"id", "nome", "descricao", "hierarquia", "nivel_inferior"})
@Entity
@Table(name = "nivel")
public class Nivel extends PanacheEntityBase {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "O campo 'nome' deve ser preenchido")
    @JsonProperty("nome")
    private String nome;

    @NotBlank(message = "O campo 'descricao' deve ser preenchido")
    @Size(min = 10, message = "Descricao muito curta")
    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("hirarquia")
    private Long hierarquia;

    @JsonProperty("nivel_inferior")
    @ManyToOne
    @JoinColumn(name = "nivel_inferior", unique = true, foreignKey = @ForeignKey(name = "FK_id_nivel_inferior"))
    private Nivel nivel_inferior;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(Long hierarquia) {
        this.hierarquia = hierarquia;
    }

    public Nivel getNivel_inferior() {
        return nivel_inferior;
    }

    public void setNivel_inferior(Nivel nivel_inferior) {
        this.nivel_inferior = nivel_inferior;
    }
}
