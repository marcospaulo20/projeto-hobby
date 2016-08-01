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

import ngprojetohobby.manga.domain.Capitulo;
import ngprojetohobby.manga.services.CapituloService;

@Path("manga")
public class CapituloRestService {

	@Inject
	private CapituloService capituloService;

	@GET
	@Path("{id}/titulo/{idTitulo}/volume/{idCapitulo}/capitulos/numberOfCapitulos")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfCapitulos() {
		return capituloService.getNumberOfCapitulos();
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/volume/{idVolume}/capitulos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Capitulo> getAllCapitulosInJSON(@PathParam("idVolume") Long id) {
		return capituloService.getAllCapitulos(id);
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/volumes/{idVolume}/capitulos/{idCapitulo}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Capitulo getCapituloById(@PathParam("idCapitulo") Long id) {
		return capituloService.getById(id);
	}

	@POST
	@Path("titulo/volume/capitulos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Capitulo create(Capitulo capitulo) {
		return capituloService.createOrUpdateCapitulo(capitulo);
	}

	@PUT
	@Path("{id}/titulo/{idTitulo}/volume/{idVolume}/capitulos/{idCapitulo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Capitulo uptade(Capitulo capitulo) {
		return capituloService.update(capitulo);
	}

	@DELETE
	@Path("{id}/titulo/{idTitulo}/volumes/{idVolume}/capitulos/{idCapitulo}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("idCapitulo") Long id) {
		capituloService.remove(id);
	}
}