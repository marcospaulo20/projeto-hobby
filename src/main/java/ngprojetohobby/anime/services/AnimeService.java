package ngprojetohobby.anime.services;

import java.util.List;

import ngprojetohobby.anime.domain.Anime;

public interface AnimeService {
	
	public List<Anime> getAllAnimes();

	Anime getById(Long id);

	Anime create(Anime anime);

	Anime update(Anime anime);

	void remove(Long id);

	Integer getNumberOfAnimes();

}
