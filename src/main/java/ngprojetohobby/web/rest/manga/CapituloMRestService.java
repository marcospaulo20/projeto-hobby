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

import ngprojetohobby.manga.domain.CapituloM;
import ngprojetohobby.manga.services.CapituloMService;

@Path("manga")
public class CapituloMRestService {

	@Inject
	private CapituloMService capituloMService;

	@GET
	@Path("{id}/titulo/{idTituloM}/volume/{idCapituloM}/capitulos/numberOfCapitulos")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfCapitulos() {
		return capituloMService.getNumberOfCapitulosM();
	}

	@GET
	@Path("{id}/titulo/{idTituloM}/volume/{idVolume}/capitulos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CapituloM> getAllCapitulosInJSON(@PathParam("idVolume") Long id) {
		return capituloMService.getAllCapitulosM(id);
	}

	@GET
	@Path("{id}/titulo/{idTituloM}/volumes/{idVolume}/capitulos/{idCapituloM}")
	@Produces(MediaType.APPLICATION_JSON)
	public CapituloM getCapituloById(@PathParam("idCapituloM") Long id) {
		return capituloMService.getById(id);
	}

	@POST
	@Path("titulo/volume/capitulos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CapituloM create(CapituloM capitulo) {
		return capituloMService.create(capitulo);
	}

	@PUT
	@Path("{id}/titulo/{idTituloM}/volume/{idVolume}/capitulos/{idCapituloM}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CapituloM uptade(CapituloM capitulo) {
		return capituloMService.update(capitulo);
	}

	@DELETE
	@Path("{id}/titulo/{idTituloM}/volume/{idVolume}/capitulos/{idCapituloM}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("idCapituloM") Long id) {
		capituloMService.remove(id);
	}
}