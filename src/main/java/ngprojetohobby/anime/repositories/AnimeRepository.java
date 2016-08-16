package ngprojetohobby.anime.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.anime.domain.Anime;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class AnimeRepository extends RepositoryImpl<Anime> {

	public AnimeRepository() {
		super(Anime.class);
	}

}
