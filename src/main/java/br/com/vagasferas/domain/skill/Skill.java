package br.com.vagasferas.domain.skill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonPropertyOrder({"id", "nome", "descricao"})
@Entity
@Table(name = "skill")
public class Skill extends PanacheEntityBase {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JsonProperty("nome")
    @NotBlank(message = "O Campo 'nome' deve ser preenchido")
    private String nome;

    @JsonProperty("descricao")
    @NotBlank(message = "O Campo 'descricao' deve ser preenchido")
    @Size(min = 10, message = "Descricao muito curta")
    private String descricao;

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
}
