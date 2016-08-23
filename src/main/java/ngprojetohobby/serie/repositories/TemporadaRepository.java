package ngprojetohobby.serie.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.repositories.contract.RepositoryImpl;
import ngprojetohobby.serie.domain.Temporada;

@Stateless
public class TemporadaRepository extends RepositoryImpl<Temporada> {

	public TemporadaRepository() {
		super(Temporada.class);
	}

}
