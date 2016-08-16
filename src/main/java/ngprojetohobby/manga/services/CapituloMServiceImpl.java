package ngprojetohobby.manga.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.CapituloM;
import ngprojetohobby.manga.domain.Volume;
import ngprojetohobby.manga.repositories.CapituloMRepository;
import ngprojetohobby.manga.repositories.VolumeRepository;

@javax.enterprise.context.RequestScoped
public class CapituloMServiceImpl implements CapituloMService {

	@Inject
	private CapituloMRepository capituloMRepository;
	
	@Inject
	private VolumeRepository volumeRepository;

	@Override
	public List<CapituloM> getAllCapitulosM(Long id) {
		return this.capituloMRepository.findAllByCol(CapituloM.class, "volume", id);
	}

	@Override
	public CapituloM getById(Long id) {
		return this.capituloMRepository.getById(id);
	}

	@Override
	public CapituloM create(CapituloM capituloM) {		
		capituloM = this.capituloMRepository.create(capituloM); 
			
		Volume volume = volumeRepository.getById(capituloM.getVolume());
		volume.getCapitulosM().add(capituloM);
		volumeRepository.update(volume);
		
		return capituloM;
	}

	@Override
	public CapituloM update(CapituloM capituloM) {
		return this.capituloMRepository.update(capituloM);
	}

	@Override
	public void remove(Long id) {
		CapituloM capituloM = capituloMRepository.getById(id);
		Volume volume = volumeRepository.getById(capituloM.getVolume());
		
		for(int i = 0; i < volume.getCapitulosM().size(); i++) {
			CapituloM c = volume.getCapitulosM().get(i);
			if(c.getId().equals(capituloM.getId())) {
				volume.getCapitulosM().remove(c);
				volumeRepository.update(volume);
				break;
			}
		}
		capituloMRepository.remove(CapituloM.class, id);
	}

	@Override
	public Integer getNumberOfCapitulosM() {
		return this.capituloMRepository.countAll(CapituloM.class);
	}

	@Override
	public List<CapituloM> findAllPage(int startPosition, int maxResults, String sortFields) {
		return capituloMRepository.findAll(CapituloM.class, startPosition, maxResults, sortFields);
	}
}
