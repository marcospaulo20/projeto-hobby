package ngprojetohobby.manga.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.manga.domain.Capitulo;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class CapituloRepository extends RepositoryImpl<Capitulo> {

	public CapituloRepository() {
		super(Capitulo.class);
	}

}
