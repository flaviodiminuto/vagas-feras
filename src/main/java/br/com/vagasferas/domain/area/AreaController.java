package br.com.vagasferas.domain.area;

import br.com.vagasferas.domain.contrato.ContratoRestFull;
import br.com.vagasferas.domain.skill.Skill;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/areas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AreaController implements ContratoRestFull<Area> {
    @POST
    @Override
    @Transactional
    public Response save(Area area) {
        area.persist();
        return Response.ok(area).build();
    }

    @GET
    @Override
    public Response listAll() {
        try{
            return Response.ok(Area.listAll()).build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(Long id) {
        Optional<Area> area = Optional.empty();
        try{
            area = Skill.findByIdOptional(id);
        } catch (Exception e){
            return Response.serverError().build();
        }
        if(area.isPresent())
            return Response.ok(area.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    @Override
    public Response update(Long id, Area aera) {
        try {
            Skill.update("nome = ?1, " +
                            " descricao = ?2, " +
                            " where id = ?3",
                    aera.getNome(),
                    aera.getDescricao(),
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
