package ngprojetohobby.serie.services;

import java.util.List;

import ngprojetohobby.serie.domain.EpisodioS;

public interface EpisodioSService {

	List<EpisodioS> getAllEpisodios(Long id);

	EpisodioS getById(Long id);

	EpisodioS create(EpisodioS episodio);

	EpisodioS update(EpisodioS episodio);

	void remove(Long id);

	Integer getNumberOfEpisodios();

}
