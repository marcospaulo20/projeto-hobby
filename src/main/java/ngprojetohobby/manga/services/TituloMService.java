package ngprojetohobby.manga.services;

import java.util.List;

import ngprojetohobby.manga.domain.TituloM;

public interface TituloMService {

	List<TituloM> getAllTitulos(Long id);

    TituloM getById(Long id);

    TituloM create(TituloM tituloM);

    TituloM update(TituloM tituloM);

    void remove(Long id);

    Integer getNumberOfTitulos();
    
}
