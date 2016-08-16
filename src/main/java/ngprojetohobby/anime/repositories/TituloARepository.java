package ngprojetohobby.anime.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.anime.domain.TituloA;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class TituloARepository extends RepositoryImpl<TituloA> {

	public TituloARepository() {
		super(TituloA.class);
	}

}
