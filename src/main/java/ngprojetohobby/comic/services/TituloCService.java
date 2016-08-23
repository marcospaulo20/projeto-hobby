package ngprojetohobby.comic.services;

import java.util.List;

import ngprojetohobby.comic.domain.TituloC;

public interface TituloCService {

	List<TituloC> getAllTitulos(Long id);

    TituloC getById(Long id);

    TituloC create(TituloC tituloC);

    TituloC update(TituloC tituloC);

    void remove(Long id);

    Integer getNumberOfTitulos();
}
