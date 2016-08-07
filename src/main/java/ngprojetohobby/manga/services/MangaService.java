package ngprojetohobby.manga.services;

import java.util.List;

import ngprojetohobby.manga.domain.Manga;

public interface MangaService {

	List<Manga> getAllMangas();

	Manga getById(Long id);

	Manga create(Manga manga);

	Manga update(Manga manga);

	void remove(Long id);

	Integer getNumberOfMangas();

	List<Manga> findAllPage(int startPosition, int maxResults, String sortFields);

}
