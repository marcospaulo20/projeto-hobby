package ngprojetohobby.manga.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.Volume;
import ngprojetohobby.manga.repositories.VolumeRepository;

@javax.enterprise.context.RequestScoped
public class VolumeServiceImpl implements VolumeService {

	@Inject
	private VolumeRepository tituloRepository;

	@Override
	public List<Volume> getAllVolumes(Long id) {
		return this.tituloRepository.findAllByCol(Volume.class, "titulo", id);
	}

	@Override
	public Volume getById(Long id) {
		return this.tituloRepository.getById(id);
	}

	@Override
	public Volume createNewVolume(Volume volume) {
		return this.tituloRepository.create(volume);
	}

	@Override
	public Volume update(Volume volume) {
		return this.tituloRepository.update(volume);
	}

	@Override
	public void remove(Long id) {
		this.tituloRepository.remove(Volume.class, id);
	}

	@Override
	public Integer getNumberOfVolumes() {
		return this.tituloRepository.countAll(Volume.class);
	}
}
