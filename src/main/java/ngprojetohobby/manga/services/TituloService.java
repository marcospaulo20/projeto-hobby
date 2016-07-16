package ngprojetohobby.manga.services;

import java.util.List;

import ngprojetohobby.manga.domain.Titulo;

public interface TituloService {

	List<Titulo> getAllTitulos(Long id);

    Titulo getById(Long id);

    Titulo createNewTitulo(Titulo titulo);

    Titulo update(Titulo titulo);

    void remove(Long id);

    Integer getNumberOfTitulos();
    
}
