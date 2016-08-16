package ngprojetohobby.manga.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.manga.domain.CapituloM;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class CapituloMRepository extends RepositoryImpl<CapituloM> {

	public CapituloMRepository() {
		super(CapituloM.class);
	}

}
