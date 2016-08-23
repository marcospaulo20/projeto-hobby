package ngprojetohobby.serie.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.serie.domain.Serie;
import ngprojetohobby.serie.repositories.SerieRepository;

@javax.enterprise.context.RequestScoped
public class SerieServiceImpl implements SerieService {

	@Inject
	private SerieRepository serieRepository;
	
	@Override
	public List<Serie> getAllSeries() {
		return this.serieRepository.getAll(Serie.class);
	}

	@Override
	public Serie getById(Long id) {
		return this.serieRepository.getById(id);
	}

	@Override
	public Serie create(Serie serie) {
		return this.serieRepository.create(serie);
	}

	@Override
	public Serie update(Serie serie) {
		return this.serieRepository.update(serie);
	}

	@Override
	public void remove(Long id) {
		this.serieRepository.remove(Serie.class, id);
	}

	@Override
	public Integer getNumberOfSeries() {
		return this.serieRepository.countAll(Serie.class);
	}

}
