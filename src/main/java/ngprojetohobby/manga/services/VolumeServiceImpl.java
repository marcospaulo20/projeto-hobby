package ngprojetohobby.manga.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.Titulo;
import ngprojetohobby.manga.domain.Volume;
import ngprojetohobby.manga.repositories.TituloRepository;
import ngprojetohobby.manga.repositories.VolumeRepository;

@javax.enterprise.context.RequestScoped
public class VolumeServiceImpl implements VolumeService {

	@Inject
	private VolumeRepository volumeRepository;

	@Inject
	private TituloRepository tituloRepository;

	@Override
	public List<Volume> getAllVolumes(Long id) {
		return this.volumeRepository.findAllByCol(Volume.class, "titulo", id);
	}

	@Override
	public Volume getById(Long id) {
		return this.volumeRepository.getById(id);
	}

	@Override
	public Volume create(Volume volume) {
		volume = this.volumeRepository.create(volume);

		Titulo titulo = tituloRepository.getById(volume.getTitulo());
		titulo.getVolumes().add(volume);
		tituloRepository.update(titulo);

		return volume;
	}

	@Override
	public Volume update(Volume volume) {
		return this.volumeRepository.update(volume);
	}

	@Override
	public void remove(Long id) {
		this.volumeRepository.remove(Volume.class, id);
	}

	@Override
	public Integer getNumberOfVolumes() {
		return this.volumeRepository.countAll(Volume.class);
	}
}
