package ngprojetohobby.comic.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.comic.domain.TituloC;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class TituloCRepository extends RepositoryImpl<TituloC> {

	public TituloCRepository() {
		super(TituloC.class);
	}

}
