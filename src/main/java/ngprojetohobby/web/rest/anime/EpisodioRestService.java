package ngprojetohobby.web.rest.anime;

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

import ngprojetohobby.anime.services.EpisodioService;
import ngprojetohobby.anime.domain.EpisodioA;

@Path("anime")
public class EpisodioRestService {

	@Inject
	private EpisodioService episodioService;

	@GET
	@Path("{id}/titulo/{idTitulo}/arco/{idArco}/episodios/numberOfEpisodios")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfEpisodios() {
		return episodioService.getNumberOfEpisodios();
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/arco/{idArco}/episodios")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EpisodioA> getAllEpisodiosInJSON(@PathParam("idArco") Long id) {
		return episodioService.getAllEpisodios(id);
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/arcos/{idArco}/episodios/{idEpisodio}")
	@Produces(MediaType.APPLICATION_JSON)	
	public EpisodioA getEpisodioById(@PathParam("idEpisodio") Long id) {
		return episodioService.getById(id);
	}

	@POST
	@Path("titulo/arco/episodios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EpisodioA create(EpisodioA episodio) {
		return episodioService.create(episodio);
	}

	@PUT
	@Path("{id}/titulo/{idTitulo}/arco/{idArco}/episodios/{idEpisodio}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EpisodioA uptade(EpisodioA episodio) {
		return episodioService.update(episodio);
	}

	@DELETE
	@Path("{id}/titulo/{idTitulo}/arco/{idArco}/episodios/{idEpisodio}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("idEpisodio") Long id) {
		episodioService.remove(id);
	}
}