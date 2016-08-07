package ngprojetohobby.manga.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.Capitulo;
import ngprojetohobby.manga.domain.Volume;
import ngprojetohobby.manga.repositories.CapituloRepository;
import ngprojetohobby.manga.repositories.VolumeRepository;

@javax.enterprise.context.RequestScoped
public class CapituloServiceImpl implements CapituloService {

	@Inject
	private CapituloRepository capituloRepository;
	
	@Inject
	private VolumeRepository volumeRepository;

	@Override
	public List<Capitulo> getAllCapitulos(Long id) {
		return this.capituloRepository.findAllByCol(Capitulo.class, "volume", id);
	}

	@Override
	public Capitulo getById(Long id) {
		return this.capituloRepository.getById(id);
	}

	@Override
	public Capitulo create(Capitulo capitulo) {
		if(capitulo.getId() == null) {
			capitulo = this.capituloRepository.create(capitulo); 
			
			Volume volume = volumeRepository.getById(capitulo.getVolume());
			
			List<Capitulo> cList = new ArrayList<Capitulo>();
			cList.add(capitulo);
			
			for(Capitulo capitulo_ : volume.getCapitulos()) {
				cList.add(capitulo_);
			}
			
			volume.setCapitulos(cList);
			volumeRepository.update(volume);
		} else {
			capitulo = this.capituloRepository.update(capitulo);
		}
		return capitulo;
	}

	@Override
	public Capitulo update(Capitulo capitulo) {
		return this.capituloRepository.update(capitulo);
	}

	@Override
	public void remove(Long id) {
		this.capituloRepository.remove(Capitulo.class, id);
	}

	@Override
	public Integer getNumberOfCapitulos() {
		return this.capituloRepository.countAll(Capitulo.class);
	}

	@Override
	public List<Capitulo> findAllPage(int startPosition, int maxResults, String sortFields) {
		return capituloRepository.findAll(Capitulo.class, startPosition, maxResults, sortFields);
	}
}
