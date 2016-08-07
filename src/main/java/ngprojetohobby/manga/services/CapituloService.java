package ngprojetohobby.manga.services;

import java.util.List;

import ngprojetohobby.manga.domain.Capitulo;

public interface CapituloService {

	public List<Capitulo> getAllCapitulos(Long id);

	Capitulo getById(Long id);

	Capitulo create(Capitulo capitulo);

	Capitulo update(Capitulo capitulo);

	void remove(Long id);

	Integer getNumberOfCapitulos();

	List<Capitulo> findAllPage(int startPosition, int maxResults, String sortFields);

}
