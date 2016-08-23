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

import ngprojetohobby.comic.domain.Comic;
import ngprojetohobby.comic.services.ComicService;

@Path("comics")
public class ComicRestService {

	@Inject
	private ComicService comicService;

	@GET
	@Path("numberOfComics")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfComics() {
		return comicService.getNumberOfComics();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comic> getAllComicsInJSON() {
		return comicService.getAllComics();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Comic getComicById(@PathParam("id") Long id) {
		return comicService.getById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comic create(Comic comic) {
		return comicService.create(comic);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comic uptade(Comic comic) {
		return comicService.update(comic);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		comicService.remove(id);
	}
}
