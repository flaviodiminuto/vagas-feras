package br.com.vagasferas.configuration.http.mapper;

import br.com.vagasferas.configuration.http.model.HttpExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

@Provider
public class HttpExceptionMapper implements ExceptionMapper<Exception> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Response toResponse(Exception exception) {
        HttpExceptionResponse response = HttpExceptionResponse.builder()
                .status(500)
                .mensagem("Falha")
                .causa(Collections.singletonList("Não foi possível atender requisição"))
                .build();
        logger.error(response.toString(), exception);
        return Response.serverError().entity(response).build();
    }
}
