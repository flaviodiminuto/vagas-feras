package br.com.vagasferas.configuration.http.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"codigo", "status", "mensagem", "causa"})
public class HttpExceptionResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("causa")
    private List<String> causa;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<String> getCausa() {
        return causa;
    }

    public void setCausa(List<String> causa) {
        this.causa = causa;
    }

    public static HttpExceptionResponseBuilder builder(){
        return HttpExceptionResponseBuilder.builder();
    }

    public static final class HttpExceptionResponseBuilder {
        private int status;
        private String mensagem;
        private List<String> causa;

        private HttpExceptionResponseBuilder() {
        }

        public static HttpExceptionResponseBuilder builder() {
            return new HttpExceptionResponseBuilder();
        }

        public HttpExceptionResponseBuilder status(int status) {
            this.status = status;
            return this;
        }

        public HttpExceptionResponseBuilder mensagem(String mensagem) {
            this.mensagem = mensagem;
            return this;
        }

        public HttpExceptionResponseBuilder causa(List<String> causa) {
            this.causa = causa;
            return this;
        }

        public HttpExceptionResponse build() {
            HttpExceptionResponse httpExceptionResponse = new HttpExceptionResponse();
            httpExceptionResponse.setStatus(status);
            httpExceptionResponse.setMensagem(mensagem);
            httpExceptionResponse.setCausa(causa);
            return httpExceptionResponse;
        }
    }
}
