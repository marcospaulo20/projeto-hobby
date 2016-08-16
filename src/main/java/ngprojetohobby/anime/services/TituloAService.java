package ngprojetohobby.anime.services;

import java.util.List;

import ngprojetohobby.anime.domain.TituloA;

public interface TituloAService {
	
	public List<TituloA> getAllTitulos(Long id);

	TituloA getById(Long id);

	TituloA create(TituloA titulo);

	TituloA update(TituloA titulo);

	void remove(Long id);

	Integer getNumberOfTitulos();

}
