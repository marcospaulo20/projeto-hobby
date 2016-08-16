package ngprojetohobby.manga.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.manga.domain.TituloM;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class TituloMRepository extends RepositoryImpl<TituloM> {

	public TituloMRepository() {
		super(TituloM.class);
	}

}
