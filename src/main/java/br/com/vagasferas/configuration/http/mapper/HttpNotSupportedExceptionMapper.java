package br.com.vagasferas.configuration.http.mapper;

import br.com.vagasferas.configuration.http.model.HttpExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

@Provider
public class HttpNotSupportedExceptionMapper implements ExceptionMapper<NotSupportedException> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Response toResponse(NotSupportedException exception) {
        HttpExceptionResponse response = HttpExceptionResponse.builder()
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .mensagem("Content type")
                .causa(Collections.singletonList("Content type inválido"))
                .build();
        logger.warn(response.toString());
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
}
