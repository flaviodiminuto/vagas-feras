package br.com.vagasferas.domain.vaga;

import br.com.vagasferas.domain.contrato.ContratoRestFull;
import br.com.vagasferas.domain.skill.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/vagas")
public class VagaController implements ContratoRestFull<Vaga> {
    Logger logger = LoggerFactory.getLogger(VagaController.class);

    @Override
    @POST
    @Transactional
    public Response save(Vaga vaga) {
            vaga.persist();
            return Response.ok(vaga).build();
    }

    @Override
    @GET
    public Response listAll() {
        try{
            return Response.ok(Vaga.listAll()).build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }

    @Override
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Optional<Vaga> vaga;
        try{
            vaga = Skill.findByIdOptional(id);
        } catch (Exception e){
            return Response.serverError().build();
        }
        if(vaga.isPresent())
            return Response.ok(vaga.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    @PATCH
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Vaga vaga) {
        vaga.persist();

        try{
        //todo programar a atualizacao, alterando somente campos planejados
//            Skill.update("nome = ?1, " +
//                            " descricao = ?2, " +
//                            " hierarquia = ?3 " +
//                            " where id = ?4",
//                    nivel.getNome(),
//                    nivel.getDescricao(),
//                    nivel.getHierarquia(),
//                    id);
            return Response.ok().build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }

    @Override
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try{
            if(Vaga.delete("where id = ?1", id)>0)
                return Response.ok().build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        }catch (Exception e){
            return Response.serverError().build();
        }
    }
}
