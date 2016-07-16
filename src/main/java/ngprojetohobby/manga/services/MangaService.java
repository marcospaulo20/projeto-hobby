package ngprojetohobby.manga.services;

import java.util.List;

import ngprojetohobby.manga.domain.Manga;

public interface MangaService {

	List<Manga> getAllMangas();

	Manga getById(Long id);

	Manga createNewManga(Manga Manga);

	Manga update(Manga Manga);

	void remove(Long id);

	Integer getNumberOfMangas();

	List<Manga> findAllPage(int startPosition, int maxResults, String sortFields);

}
