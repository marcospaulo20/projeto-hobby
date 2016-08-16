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

import ngprojetohobby.anime.domain.Anime;
import ngprojetohobby.anime.services.AnimeService;

@Path("animes")
public class AnimeRestService {

	@Inject
	private AnimeService animeService;

	@GET
	@Path("numberOfAnimes")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfAnimes() {
		return animeService.getNumberOfAnimes();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Anime> getAllAnimesInJSON() {
		return animeService.getAllAnimes();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Anime getAnimeById(@PathParam("id") Long id) {
		return animeService.getById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Anime create(Anime anime) {
		return animeService.create(anime);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Anime uptade(Anime anime) {
		return animeService.update(anime);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		animeService.remove(id);
	}

}
