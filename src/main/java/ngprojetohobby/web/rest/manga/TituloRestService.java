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

import ngprojetohobby.manga.domain.Titulo;
import ngprojetohobby.manga.services.TituloService;

@Path("manga")
public class TituloRestService {

	@Inject
	private TituloService tituloService;

	@GET
	@Path("{manga}/titulos/numberOfTitulos")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfTitulos() {
		return tituloService.getNumberOfTitulos();
	}

	@GET
	@Path("{manga}/titulos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Titulo> getAllTitulosInJSON(@PathParam("manga") Long id) {
		return tituloService.getAllTitulos(id);
	}

	@GET
	@Path("{manga}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Titulo getTituloById(@PathParam("id") Long id) {
		return tituloService.getById(id);
	}

	@POST
	@Path("titulos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Titulo create(Titulo titulo) {
		return tituloService.createNewTitulo(titulo);
	}

	@PUT
	@Path("{manga}/titulos/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Titulo uptade(Titulo titulo) {
		return tituloService.update(titulo);
	}

	@DELETE
	@Path("{manga}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		tituloService.remove(id);
	}
}