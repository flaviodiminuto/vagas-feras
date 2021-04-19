package br.com.vagasferas.domain.aluno;

import br.com.vagasferas.domain.contrato.ContratoRestFull;
import br.com.vagasferas.domain.skill.Skill;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/alunos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlunoController implements ContratoRestFull<Aluno> {

    @POST
    @Transactional
    public Response save(Aluno aluno){
        aluno.persist();
        return Response.ok(aluno).build();
    }

    @GET
    public Response listAll(){
        return Response.ok(Aluno.listAll()).build();
    }

    @Override
    public Response findById(Long id) {
        return null;
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    @Override
    public Response update(Long id, Aluno aluno) {
        try {
            Skill.update("nome = ?1,  ra = ?2 " +
                            " where id = ?3",
                    aluno.getNome_completo(),
                    aluno.getRa(),
                    aluno.getId());
            return Response.ok().build();
        } catch (Exception e) {
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

    @GET
    @Path("/{id}")
    public Response listById(@PathParam("id") Long id){
        Optional<Aluno> alunoOptional = Aluno.findByIdOptional(id);
        if(alunoOptional.isPresent())
            return Response.ok(alunoOptional.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}