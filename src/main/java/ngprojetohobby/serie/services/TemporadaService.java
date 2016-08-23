package ngprojetohobby.serie.services;

import java.util.List;

import ngprojetohobby.serie.domain.Temporada;

public interface TemporadaService {

	List<Temporada> getAllTemporadas(Long id);

    Temporada getById(Long id);

    Temporada create(Temporada temporada);

    Temporada update(Temporada temporada);

    void remove(Long id);

    Integer getNumberOfTemporadas();
}
