package br.com.vagasferas.configuration.http.mapper;

import br.com.vagasferas.configuration.http.model.HttpExceptionResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

@Provider
public class HttpExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        HttpExceptionResponse response = HttpExceptionResponse.builder()
                .status(500)
                .mensagem("Falha")
                .causa(Collections.singletonList("Não foi possível atender requisição"))
                .build();

        return Response.serverError().entity(response).build();
    }
}
