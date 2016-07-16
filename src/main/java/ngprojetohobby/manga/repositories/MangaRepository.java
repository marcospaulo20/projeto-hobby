package ngprojetohobby.manga.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.manga.domain.Manga;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class MangaRepository extends RepositoryImpl<Manga> {

	public MangaRepository() {
		super(Manga.class);
	}

}
