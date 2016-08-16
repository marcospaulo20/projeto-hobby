package ngprojetohobby.anime.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.anime.domain.Arco;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class ArcoRepository extends RepositoryImpl<Arco> {

	public ArcoRepository() {
		super(Arco.class);
	}

}
