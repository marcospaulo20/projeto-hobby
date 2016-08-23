package ngprojetohobby.comic.services;

import java.util.List;

import ngprojetohobby.comic.domain.Comic;

public interface ComicService {

	List<Comic> getAllComics();

	Comic getById(Long id);

	Comic create(Comic comic);

	Comic update(Comic comic);

	void remove(Long id);

	Integer getNumberOfComics();

}
