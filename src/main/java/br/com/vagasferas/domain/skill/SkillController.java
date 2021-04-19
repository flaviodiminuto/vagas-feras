package br.com.vagasferas.domain.skill;

import br.com.vagasferas.domain.contrato.ContratoRestFull;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/skills")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SkillController implements ContratoRestFull<Skill> {
    @POST
    @Override
    @Transactional
    public Response save(Skill skill) {
            skill.persist();
            return Response.ok(skill).build();
    }

    @GET
    @Override
    public Response listAll() {
        try{
            return Response.ok(Skill.listAll()).build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Optional<Skill> skill = Optional.empty();
        try{
            skill = Skill.findByIdOptional(id);
        } catch (Exception e){
            return Response.serverError().build();
        }
        if(skill.isPresent())
            return Response.ok(skill.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    @Override
    public Response update(@PathParam("id") Long id, Skill entity) {
        try{
            Skill.update("nome = ?1 where id = ?2",
                    entity.getNome(), id);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Override
    public Response delete(@PathParam("id") Long id) {
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
