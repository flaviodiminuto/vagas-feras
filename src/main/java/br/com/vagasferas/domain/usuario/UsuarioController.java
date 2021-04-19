package br.com.vagasferas.domain.usuario;

import br.com.vagasferas.domain.contrato.ContratoRestFull;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/usuarios")
public class UsuarioController implements ContratoRestFull<Usuario> {


    @Override
    @POST
    @Transactional
    public Response save(Usuario usuario) {
        usuario.persist();
        return Response.ok(usuario).build();
    }

    @Override
    @GET
    public Response listAll() {
        return Response.ok(Usuario.listAll()).build();
    }

    @Override
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Optional<Usuario> usuario = Usuario.findByIdOptional(id);
        if (usuario.isPresent())
            return Response.ok(usuario.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    @PATCH
    @Path("/{id}")
    @Transactional
    public Response update(@QueryParam("id") Long id, Usuario usuario) {
        Usuario.update("senha = ?1 where id = ?2", usuario.getSenha(), usuario.getId());
        return Response.noContent().build();
    }

    @Override
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Usuario.deleteById(id);
        return Response.noContent().build();
    }
}
