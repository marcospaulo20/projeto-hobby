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

import ngprojetohobby.serie.domain.Serie;
import ngprojetohobby.serie.services.SerieService;

@Path("series")
public class SerieRestService {

	@Inject
	private SerieService serieService;

	@GET
	@Path("numberOfSeries")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfSeries() {
		return serieService.getNumberOfSeries();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Serie> getAllSeriesInJSON() {
		return serieService.getAllSeries();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Serie getSerieById(@PathParam("id") Long id) {
		return serieService.getById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Serie create(Serie serie) {
		return serieService.create(serie);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Serie uptade(Serie serie) {
		return serieService.update(serie);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		serieService.remove(id);
	}

}
