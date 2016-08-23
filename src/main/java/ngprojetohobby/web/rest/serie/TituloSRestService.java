package ngprojetohobby.web.rest.serie;

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

import ngprojetohobby.serie.domain.TituloS;
import ngprojetohobby.serie.services.TituloSService;

@Path("serie")
public class TituloSRestService {

	@Inject
	private TituloSService tituloService;
	
	@GET
	@Path("{serie}/titulos/numberOfTitulos")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfTitulos() {
		return tituloService.getNumberOfTitulos();
	}

	@GET
	@Path("{serie}/titulos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TituloS> getAllTitulosInJSON(@PathParam("serie") Long id) {
		return tituloService.getAllTitulos(id);
	}

	@GET
	@Path("{serie}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TituloS getTituloById(@PathParam("id") Long id) {
		return tituloService.getById(id);
	}

	@POST
	@Path("titulos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TituloS create(TituloS titulo) {
		return tituloService.create(titulo);
	}

	@PUT
	@Path("{serie}/titulos/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TituloS uptade(TituloS titulo) {
		return tituloService.update(titulo);
	}

	@DELETE
	@Path("{serie}/titulos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		tituloService.remove(id);
	}

}
