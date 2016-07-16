package ngprojetohobby.web.rest.manga;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ngprojetohobby.manga.domain.Manga;
import ngprojetohobby.manga.services.MangaService;
import ngprojetohobby.util.PaginatedListWrapper;

@Path("mangas")
public class MangaRestService {

	@Inject
	private MangaService mangaService;
	
	@GET
	@Path("numberOfMangas")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfMangas() {
		return mangaService.getNumberOfMangas();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Manga> getAllMangasInJSON() {
		return mangaService.getAllMangas();
	}

	@GET
	@Path("page")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper listMangas(@DefaultValue("1") @QueryParam("page") int page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields) {
		PaginatedListWrapper paginatedListWrapper = new PaginatedListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setPageSize(10);
		return findAll(paginatedListWrapper);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Manga getMangaById(@PathParam("id") Long id) {
		return mangaService.getById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Manga create(Manga manga) {
		return mangaService.createNewManga(manga);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Manga uptade(Manga manga) {
		return mangaService.update(manga);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") Long id) {
		mangaService.remove(id);
	}
	
	private PaginatedListWrapper findAll(PaginatedListWrapper wrapper) {
		wrapper.setTotalResults(mangaService.getNumberOfMangas());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(mangaService.findAllPage(start, wrapper.getPageSize(), wrapper.getSortFields()));
		return wrapper;
	}
}