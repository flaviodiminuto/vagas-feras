package br.com.vagasferas.domain.usuario;

import br.com.vagasferas.configuration.http.validator.ExtendedEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@JsonPropertyOrder({"id", "login", "senha"})
@Entity
@Table(name = "usuario")
public class Usuario extends PanacheEntityBase {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "O campo 'login' deve ser preenchido")
    @JsonProperty("login")
    private String login;

    @NotBlank(message = "O campo 'Senha' deve ser preenchido")
    @JsonProperty("senha")
    private String senha;

    @ExtendedEmail
    @NotBlank(message = "O campo 'email' deve ser preenchido")
    @JsonProperty("email")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
