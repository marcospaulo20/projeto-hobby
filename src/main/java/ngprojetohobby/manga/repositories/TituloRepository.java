package ngprojetohobby.manga.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.manga.domain.Titulo;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class TituloRepository extends RepositoryImpl<Titulo> {

	public TituloRepository() {
		super(Titulo.class);
	}

}
