package ngprojetohobby.manga.services;

import java.util.List;

import ngprojetohobby.manga.domain.CapituloM;

public interface CapituloMService {

	public List<CapituloM> getAllCapitulosM(Long id);

	CapituloM getById(Long id);

	CapituloM create(CapituloM capituloM);

	CapituloM update(CapituloM capituloM);

	void remove(Long id);

	Integer getNumberOfCapitulosM();

	List<CapituloM> findAllPage(int startPosition, int maxResults, String sortFields);

}
