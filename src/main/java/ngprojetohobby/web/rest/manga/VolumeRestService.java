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

import ngprojetohobby.manga.domain.Volume;
import ngprojetohobby.manga.services.VolumeService;

@Path("manga")
public class VolumeRestService {

	@Inject
	private VolumeService volumeService;

	@GET
	@Path("{id}/titulo/{idTitulo}/volumes/numberOfVolumes")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer getNumberOfVolumes() {
		return volumeService.getNumberOfVolumes();
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/volumes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Volume> getAllVolumesInJSON(@PathParam("idTitulo") Long id) {
		return volumeService.getAllVolumes(id);
	}

	@GET
	@Path("{id}/titulo/{idTitulo}/volumes/{idVolume}")
	@Produces(MediaType.APPLICATION_JSON)
	public Volume getVolumeById(@PathParam("idVolume") Long id) {
		return volumeService.getById(id);
	}

	@POST
	@Path("titulo/volumes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Volume create(Volume volume) {
		return volumeService.create(volume);
	}

	@PUT
	@Path("{id}/titulo/{idTitulo}/volumes/{idVolume}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Volume uptade(Volume volume) {
		return volumeService.update(volume);
	}

	@DELETE
	@Path("{id}/titulo/{idTitulo}/volumes/{idVolume}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("idVolume") Long id) {
		volumeService.remove(id);
	}
}