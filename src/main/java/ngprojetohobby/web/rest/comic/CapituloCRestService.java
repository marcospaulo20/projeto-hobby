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

import ngprojetohobby.comic.domain.CapituloC;
import ngprojetohobby.comic.services.CapituloCService;

@Path("comic")
public class CapituloCRestService {

	@Inject
	private CapituloCService capituloCService;

	@GET
	@Path("{id}/titulo/{idTitulo}/capitulosC/numberOfCapituloCs")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfCapituloCs() {
		return capituloCService.getNumberOfCapitulosC();
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/capitulosC")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CapituloC> getAllCapitulosCInJSON(@PathParam("idTitulo") Long id) {
		return capituloCService.getAllCapitulosC(id);
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/capitulosC/{idCapituloC}")
	@Produces(MediaType.APPLICATION_JSON)
	public CapituloC getCapituloCById(@PathParam("idCapituloC") Long id) {
		return capituloCService.getById(id);
	}

	@POST
	@Path("titulo/capitulosC")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CapituloC create(CapituloC capituloC) {
		return capituloCService.create(capituloC);
	}

	@PUT
	@Path("{id}/titulo/{idTitulo}/capitulosC/{idCapituloC}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CapituloC uptade(CapituloC capituloC) {
		return capituloCService.update(capituloC);
	}

	@DELETE
	@Path("{id}/titulo/{idTitulo}/capitulosC/{idCapituloC}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("idCapituloC") Long id) {
		capituloCService.remove(id);
	}
}
