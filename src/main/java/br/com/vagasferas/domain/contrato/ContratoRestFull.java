package br.com.vagasferas.domain.contrato;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

public interface ContratoRestFull<T> {
    Response save(@Valid T entity);
    Response listAll();
    Response findById(Long id);
    Response update(Long id, T entity);
    Response delete(Long id);
}
