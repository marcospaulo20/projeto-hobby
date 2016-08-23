package ngprojetohobby.serie.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.serie.domain.EpisodioS;
import ngprojetohobby.serie.domain.Temporada;
import ngprojetohobby.serie.domain.TituloS;
import ngprojetohobby.serie.repositories.EpisodioSRepository;
import ngprojetohobby.serie.repositories.TemporadaRepository;
import ngprojetohobby.serie.repositories.TituloSRepository;

@javax.enterprise.context.RequestScoped
public class TemporadaServiceImpl implements TemporadaService {

	@Inject
	private TemporadaRepository temporadaRepository;

	@Inject
	private TituloSRepository tituloRepository;
	
	@Inject
	private EpisodioSRepository episodioRepository;
	
	@Override
	public List<Temporada> getAllTemporadas(Long id) {
		return this.temporadaRepository.findAllByCol(Temporada.class, "tituloS", id);
	}

	@Override
	public Temporada getById(Long id) {
		return this.temporadaRepository.getById(id);
	}

	@Override
	public Temporada create(Temporada temporada) {
		temporada = this.temporadaRepository.create(temporada);

		TituloS titulo = tituloRepository.getById(temporada.getTituloS());
		titulo.getTemporadas().add(temporada);
		tituloRepository.update(titulo);

		return temporada;
	}

	@Override
	public Temporada update(Temporada temporada) {
		if(temporada.getStatus() == true) {
			for(EpisodioS e : temporada.getEpisodiosS()) {
				e.setStatus(true);
				this.episodioRepository.update(e);
			}
		}
		return this.temporadaRepository.update(temporada);
	}

	@Override
	public void remove(Long id) {
		this.temporadaRepository.remove(Temporada.class, id);
	}

	@Override
	public Integer getNumberOfTemporadas() {
		return this.temporadaRepository.countAll(Temporada.class);
	}

}
