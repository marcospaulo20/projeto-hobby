package ngprojetohobby.comic.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.comic.domain.CapituloC;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class CapituloCRepository extends RepositoryImpl<CapituloC> {

	public CapituloCRepository() {
		super(CapituloC.class);
	}

}
