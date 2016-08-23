package ngprojetohobby.serie.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.repositories.contract.RepositoryImpl;
import ngprojetohobby.serie.domain.Serie;

@Stateless
public class SerieRepository extends RepositoryImpl<Serie> {

	public SerieRepository() {
		super(Serie.class);
	}

}
