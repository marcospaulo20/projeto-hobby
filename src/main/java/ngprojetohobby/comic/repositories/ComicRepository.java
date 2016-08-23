package ngprojetohobby.comic.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.comic.domain.Comic;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class ComicRepository extends RepositoryImpl<Comic> {

	public ComicRepository() {
		super(Comic.class);
	}

}
