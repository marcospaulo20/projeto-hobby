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

import ngprojetohobby.anime.domain.TituloA;
import ngprojetohobby.anime.services.TituloAService;

@Path("anime")
public class TituloARestService {

	@Inject
	private TituloAService tituloService;
	
	@GET
	@Path("{anime}/titulos/numberOfTitulos")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfTitulos() {
		return tituloService.getNumberOfTitulos();
	}

	@GET
	@Path("{anime}/titulos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TituloA> getAllTitulosInJSON(@PathParam("anime") Long id) {
		return tituloService.getAllTitulos(id);
	}

	@GET
	@Path("{anime}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TituloA getTituloById(@PathParam("id") Long id) {
		return tituloService.getById(id);
	}

	@POST
	@Path("titulos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TituloA create(TituloA titulo) {
		return tituloService.create(titulo);
	}

	@PUT
	@Path("{anime}/titulos/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TituloA uptade(TituloA titulo) {
		return tituloService.update(titulo);
	}

	@DELETE
	@Path("{anime}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		tituloService.remove(id);
	}
}