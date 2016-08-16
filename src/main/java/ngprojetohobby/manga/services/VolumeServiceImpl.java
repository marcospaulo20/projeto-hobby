package ngprojetohobby.manga.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.CapituloM;
import ngprojetohobby.manga.domain.TituloM;
import ngprojetohobby.manga.domain.Volume;
import ngprojetohobby.manga.repositories.CapituloMRepository;
import ngprojetohobby.manga.repositories.TituloMRepository;
import ngprojetohobby.manga.repositories.VolumeRepository;

@javax.enterprise.context.RequestScoped
public class VolumeServiceImpl implements VolumeService {

	@Inject
	private VolumeRepository volumeRepository;

	@Inject
	private TituloMRepository tituloMRepository;
	
	@Inject
	private CapituloMRepository capituloMRepository;

	@Override
	public List<Volume> getAllVolumes(Long id) {
		return this.volumeRepository.findAllByCol(Volume.class, "tituloM", id);
	}

	@Override
	public Volume getById(Long id) {
		return this.volumeRepository.getById(id);
	}

	@Override
	public Volume create(Volume volume) {
		volume = this.volumeRepository.create(volume);

		TituloM tituloM = tituloMRepository.getById(volume.getTituloM());
		tituloM.getVolumes().add(volume);
		tituloMRepository.update(tituloM);

		return volume;
	}

	@Override
	public Volume update(Volume volume) {
		
		if(volume.getStatus() == true) {
			for(CapituloM c : volume.getCapitulosM()) {
				c.setStatus(true);
				this.capituloMRepository.update(c);
			}
		}
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
