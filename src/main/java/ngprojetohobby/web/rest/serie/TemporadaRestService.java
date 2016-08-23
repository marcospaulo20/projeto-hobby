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

import ngprojetohobby.serie.domain.Temporada;
import ngprojetohobby.serie.services.TemporadaService;

@Path("serie")
public class TemporadaRestService {

	@Inject
	private TemporadaService temporadaService;

	@GET
	@Path("{id}/titulo/{idTitulo}/temporadas/numberOfTemporadas")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfTemporadas() {
		return temporadaService.getNumberOfTemporadas();
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/temporadas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Temporada> getAllTemporadasInJSON(@PathParam("idTitulo") Long id) {
		return temporadaService.getAllTemporadas(id);
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/temporadas/{idTemporada}")
	@Produces(MediaType.APPLICATION_JSON)
	public Temporada getTemporadaById(@PathParam("idTemporada") Long id) {
		return temporadaService.getById(id);
	}

	@POST
	@Path("titulo/temporadas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Temporada create(Temporada temporada) {
		return temporadaService.create(temporada);
	}

	@PUT
	@Path("{id}/titulo/{idTitulo}/temporadas/{idTemporada}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Temporada uptade(Temporada temporada) {
		return temporadaService.update(temporada);
	}

	@DELETE
	@Path("{id}/titulo/{idTitulo}/temporadas/{idTemporada}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("idTemporada") Long id) {
		temporadaService.remove(id);
	}

}
