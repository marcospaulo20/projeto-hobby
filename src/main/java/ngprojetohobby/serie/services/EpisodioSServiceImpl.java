package ngprojetohobby.serie.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.serie.domain.EpisodioS;
import ngprojetohobby.serie.domain.Temporada;
import ngprojetohobby.serie.repositories.EpisodioSRepository;
import ngprojetohobby.serie.repositories.TemporadaRepository;

@javax.enterprise.context.RequestScoped
public class EpisodioSServiceImpl implements EpisodioSService {

	@Inject
	private EpisodioSRepository episodioRepository;
	
	@Inject
	private TemporadaRepository temporadaRepository;
	
	@Override
	public List<EpisodioS> getAllEpisodios(Long id) {
		return this.episodioRepository.findAllByCol(EpisodioS.class, "temporada", id);
	}

	@Override
	public EpisodioS getById(Long id) {
		return this.episodioRepository.getById(id);
	}

	@Override
	public EpisodioS create(EpisodioS episodio) {
		episodio = this.episodioRepository.create(episodio); 
		
		Temporada temporada = temporadaRepository.getById(episodio.getTemporada());
		temporada.getEpisodiosS().add(episodio);
		temporadaRepository.update(temporada);
		
		return episodio;
	}

	@Override
	public EpisodioS update(EpisodioS episodio) {
		return this.episodioRepository.update(episodio);
	}

	@Override
	public void remove(Long id) {
		EpisodioS episodio = episodioRepository.getById(id);
		Temporada temporada = temporadaRepository.getById(episodio.getTemporada());
		
		for(int i = 0; i < temporada.getEpisodiosS().size(); i++) {
			EpisodioS e = temporada.getEpisodiosS().get(i);
			if(e.getId().equals(episodio.getId())) {
				temporada.getEpisodiosS().remove(e);
				temporadaRepository.update(temporada);
				break;
			}
		}			
		episodioRepository.remove(EpisodioS.class, id);
	}

	@Override
	public Integer getNumberOfEpisodios() {
		return this.episodioRepository.countAll(EpisodioS.class);
	}

}
