package ngprojetohobby.manga.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.CapituloM;
import ngprojetohobby.manga.domain.Manga;
import ngprojetohobby.manga.domain.TituloM;
import ngprojetohobby.manga.domain.Volume;
import ngprojetohobby.manga.repositories.CapituloMRepository;
import ngprojetohobby.manga.repositories.MangaRepository;
import ngprojetohobby.manga.repositories.TituloMRepository;
import ngprojetohobby.manga.repositories.VolumeRepository;

@javax.enterprise.context.RequestScoped
public class VolumeServiceImpl implements VolumeService {

	@Inject
	private VolumeRepository volumeRepository;

	@Inject
	private CapituloMRepository capituloMRepository;
	
	@Inject
	private MangaRepository mangaRepository;
	
	@Inject
	private TituloMRepository tituloMRepository;

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
		if(volume.getPrimeiroVolume()) {
			TituloM tituloM = tituloMRepository.getById(volume.getTituloM()); 
			if(volume.getImagem() != null) {
				Manga m = mangaRepository.getById(tituloM.getManga());
				m.setImagem(volume.getImagem());
				mangaRepository.update(m);
			}
		}
		return this.volumeRepository.create(volume);
	}

	@Override
	public Volume update(Volume volume) {
		if (volume.getStatus().equals("JL")) {
			for (CapituloM c : volume.getCapitulosM()) {
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
