package ngprojetohobby.anime.services;

import java.util.List;

import ngprojetohobby.anime.domain.EpisodioA;

public interface EpisodioService {

	List<EpisodioA> getAllEpisodios(Long id);

	EpisodioA getById(Long id);

	EpisodioA create(EpisodioA episodio);

	EpisodioA update(EpisodioA episodio);

	void remove(Long id);

	Integer getNumberOfEpisodios();

}
