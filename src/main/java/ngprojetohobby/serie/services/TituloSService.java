package ngprojetohobby.serie.services;

import java.util.List;

import ngprojetohobby.serie.domain.TituloS;

public interface TituloSService {

	List<TituloS> getAllTitulos(Long id);

	TituloS getById(Long id);

	TituloS create(TituloS titulos);

	TituloS update(TituloS tituloS);

	void remove(Long id);

	Integer getNumberOfTitulos();
}
