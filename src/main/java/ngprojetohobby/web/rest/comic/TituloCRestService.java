package ngprojetohobby.web.rest.comic;

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

import ngprojetohobby.comic.domain.TituloC;
import ngprojetohobby.comic.services.TituloCService;

@Path("comic")
public class TituloCRestService {

	@Inject
	private TituloCService tituloService;
	
	@GET
	@Path("{comic}/titulos/numberOfTitulos")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfTitulos() {
		return tituloService.getNumberOfTitulos();
	}

	@GET
	@Path("{comic}/titulos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TituloC> getAllTitulosInJSON(@PathParam("comic") Long id) {
		return tituloService.getAllTitulos(id);
	}

	@GET
	@Path("{comic}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TituloC getTituloById(@PathParam("id") Long id) {
		return tituloService.getById(id);
	}

	@POST
	@Path("titulos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TituloC create(TituloC titulo) {
		return tituloService.create(titulo);
	}

	@PUT
	@Path("{comic}/titulos/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TituloC uptade(TituloC titulo) {
		return tituloService.update(titulo);
	}

	@DELETE
	@Path("{comic}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		tituloService.remove(id);
	}

}
