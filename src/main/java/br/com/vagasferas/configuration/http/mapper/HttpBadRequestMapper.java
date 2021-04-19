package br.com.vagasferas.configuration.http.mapper;

import br.com.vagasferas.configuration.http.model.HttpExceptionResponse;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class HttpBadRequestMapper implements ExceptionMapper<ConstraintViolationException> {

    public Response toResponse(ConstraintViolationException exception){
        HttpExceptionResponse response = new HttpExceptionResponse();
        response.setStatus(400);
        response.setMensagem("Requisição inválida");
        List<String> causas = exception.getConstraintViolations().stream().map(v -> v.getMessageTemplate())
                .collect(Collectors.toList());
        response.setCausa(causas);
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
}
