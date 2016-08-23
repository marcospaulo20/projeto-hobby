package ngprojetohobby.comic.services;

import java.util.List;

import ngprojetohobby.comic.domain.CapituloC;

public interface CapituloCService {

	public List<CapituloC> getAllCapitulosC(Long id);

	CapituloC getById(Long id);

	CapituloC create(CapituloC capituloC);

	CapituloC update(CapituloC capituloC);

	void remove(Long id);

	Integer getNumberOfCapitulosC();

}
