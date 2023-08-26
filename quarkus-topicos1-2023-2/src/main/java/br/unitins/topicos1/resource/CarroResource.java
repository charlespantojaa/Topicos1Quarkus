package br.unitins.topicos1.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import br.unitins.topicos1.model.Carro;
import br.unitins.topicos1.repository.CarroRepository;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarroResource {

    @Inject
    CarroRepository repository;

    @GET
    public List<Carro> listaCarros(){
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Carro findById(@PathParam("id") Long id){
        return repository.findById(id);
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<Carro> findByName(@PathParam("nome") String nome){
        return repository.findByName(nome);
    }

    @POST
    @Transactional
    public Carro insert(Carro carro){
        Carro novoCarro = new Carro();
        novoCarro.setModelo(carro.getModelo());
        novoCarro.setAno(carro.getAno());
        novoCarro.setCor(carro.getCor());

        repository.persist(novoCarro);
        return novoCarro;
    }

    @GET
    @Path("/count")
    public long count(){
        return repository.count();
    }

    @DELETE
    @Transactional
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Carro carro){
        Carro atualizaCarro = repository.findById(id);
        if (atualizaCarro == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        atualizaCarro.setModelo(carro.getModelo());
        atualizaCarro.setAno(carro.getAno());
        atualizaCarro.setCor(carro.getCor());

        repository.persist(atualizaCarro);
        return Response.ok(atualizaCarro).build();
    }
}
