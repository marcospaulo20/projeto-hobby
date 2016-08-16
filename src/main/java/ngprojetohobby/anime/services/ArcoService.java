package ngprojetohobby.anime.services;

import java.util.List;

import ngprojetohobby.anime.domain.Arco;

public interface ArcoService {
	
	public List<Arco> getAllArcos(Long id);

	Arco getById(Long id);

	Arco create(Arco arco);

	Arco update(Arco arco);

	void remove(Long id);

	Integer getNumberOfArcos();

}
