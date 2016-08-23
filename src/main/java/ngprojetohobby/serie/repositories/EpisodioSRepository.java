package ngprojetohobby.serie.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.repositories.contract.RepositoryImpl;
import ngprojetohobby.serie.domain.EpisodioS;

@Stateless
public class EpisodioSRepository extends RepositoryImpl<EpisodioS> {

	public EpisodioSRepository() {
		super(EpisodioS.class);
	}
}
