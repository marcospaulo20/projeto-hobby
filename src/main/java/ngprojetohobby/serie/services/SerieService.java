package ngprojetohobby.serie.services;

import java.util.List;

import ngprojetohobby.serie.domain.Serie;

public interface SerieService {

	List<Serie> getAllSeries();

	Serie getById(Long id);

	Serie create(Serie serie);

	Serie update(Serie serie);

	void remove(Long id);

	Integer getNumberOfSeries();
}
