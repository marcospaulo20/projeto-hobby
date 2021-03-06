package ngprojetohobby.web.rest.manga;

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

import ngprojetohobby.manga.domain.TituloM;
import ngprojetohobby.manga.services.TituloMService;

@Path("manga")
public class TituloMRestService {

	@Inject
	private TituloMService tituloService;
	
	@GET
	@Path("{manga}/titulos/numberOfTitulos")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfTitulos() {
		return tituloService.getNumberOfTitulos();
	}

	@GET
	@Path("{manga}/titulos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TituloM> getAllTitulosInJSON(@PathParam("manga") Long id) {
		return tituloService.getAllTitulos(id);
	}

	@GET
	@Path("{manga}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TituloM getTituloById(@PathParam("id") Long id) {
		return tituloService.getById(id);
	}

	@POST
	@Path("titulos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TituloM create(TituloM titulo) {
		return tituloService.create(titulo);
	}

	@PUT
	@Path("{manga}/titulos/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TituloM uptade(TituloM titulo) {
		return tituloService.update(titulo);
	}

	@DELETE
	@Path("{manga}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		tituloService.remove(id);
	}
}