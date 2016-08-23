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

import ngprojetohobby.serie.domain.EpisodioS;
import ngprojetohobby.serie.services.EpisodioSService;

@Path("serie")
public class EpisodioSRestService {

	@Inject
	private EpisodioSService episodioService;

	@GET
	@Path("{id}/titulo/{idTitulo}/temporada/{idTemporada}/episodios/numberOfEpisodios")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfEpisodios() {
		return episodioService.getNumberOfEpisodios();
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/temporada/{idTemporada}/episodios")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EpisodioS> getAllEpisodiosInJSON(@PathParam("idTemporada") Long id) {
		return episodioService.getAllEpisodios(id);
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/temporadas/{idTemporada}/episodios/{idEpisodio}")
	@Produces(MediaType.APPLICATION_JSON)	
	public EpisodioS getEpisodioById(@PathParam("idEpisodio") Long id) {
		return episodioService.getById(id);
	}

	@POST
	@Path("titulo/temporada/episodios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EpisodioS create(EpisodioS episodio) {
		return episodioService.create(episodio);
	}

	@PUT
	@Path("{id}/titulo/{idTitulo}/temporada/{idTemporada}/episodios/{idEpisodio}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EpisodioS uptade(EpisodioS episodio) {
		return episodioService.update(episodio);
	}

	@DELETE
	@Path("{id}/titulo/{idTitulo}/temporada/{idTemporada}/episodios/{idEpisodio}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("idEpisodio") Long id) {
		episodioService.remove(id);
	}

}
