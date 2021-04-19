package br.com.vagasferas.domain.nivel;

import br.com.vagasferas.domain.contrato.ContratoRestFull;
import br.com.vagasferas.domain.skill.Skill;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/niveis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NivelControler implements ContratoRestFull<Nivel> {
    @POST
    @Override
    @Transactional
    public Response save(Nivel nivel) {
            nivel.persist();
            return Response.ok(nivel).build();
    }

    @GET
    @Override
    public Response listAll() {
        try{
            return Response.ok(Nivel.listAll()).build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(Long id) {
        Optional<Nivel> nivel = Optional.empty();
        try{
            nivel = Skill.findByIdOptional(id);
        } catch (Exception e){
            return Response.serverError().build();
        }
        if(nivel.isPresent())
            return Response.ok(nivel.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    @Override
    public Response update(Long id, Nivel nivel) {
        try{
            Skill.update("nome = ?1, " +
                            " descricao = ?2, " +
                            " hierarquia = ?3 " +
                            " where id = ?4",
                    nivel.getNome(),
                    nivel.getDescricao(),
                    nivel.getHierarquia(),
                    id);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Override
    public Response delete(Long id) {
        try{
            if(Skill.delete("where id = ?1", id)>0)
                return Response.ok().build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }
}
