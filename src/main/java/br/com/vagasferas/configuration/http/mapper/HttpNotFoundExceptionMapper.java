package br.com.vagasferas.configuration.http.mapper;

import br.com.vagasferas.configuration.http.model.HttpExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Arrays;

public class HttpNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
        private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Response toResponse(NotFoundException exception) {
            HttpExceptionResponse response = new HttpExceptionResponse();
            response.setStatus(400);
            response.setMensagem("Não encontrado");
            response.setCausa(Arrays.asList("Recurso não encontrado"));
            logger.warn(response.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
}
