package ngprojetohobby.anime.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.anime.domain.EpisodioA;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class EpisodioARepository extends RepositoryImpl<EpisodioA> {

	public EpisodioARepository() {
		super(EpisodioA.class);
	}

}
