package ngprojetohobby.web.rest.anime;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ngprojetohobby.anime.domain.Arco;
import ngprojetohobby.anime.services.ArcoService;

@Path("anime")
public class ArcoRestService {

	@Inject
	private ArcoService arcoService;

	@GET
	@Path("{id}/titulo/{idTitulo}/arcos/numberOfArcos")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfArcos() {
		return arcoService.getNumberOfArcos();
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/arcos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Arco> getAllArcosInJSON(@PathParam("idTitulo") Long id) {
		return arcoService.getAllArcos(id);
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/arcos/{idArco}")
	@Produces(MediaType.APPLICATION_JSON)
	public Arco getArcoById(@PathParam("idArco") Long id) {
		return arcoService.getById(id);
	}

	@POST
	@Path("titulo/arcos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Arco create(Arco arco) {
		return arcoService.create(arco);
	}

	@PUT
	@Path("{id}/titulo/{idTitulo}/arcos/{idArco}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Arco uptade(Arco arco) {
		return arcoService.update(arco);
	}

	@DELETE
	@Path("{id}/titulo/{idTitulo}/arcos/{idArco}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("idArco") Long id) {
		arcoService.remove(id);
	}
}