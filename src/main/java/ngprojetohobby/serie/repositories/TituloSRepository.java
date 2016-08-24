package ngprojetohobby.serie.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.repositories.contract.RepositoryImpl;
import ngprojetohobby.serie.domain.TituloS;

@Stateless
public class TituloSRepository extends RepositoryImpl<TituloS> {

	public TituloSRepository() {
		super(TituloS.class);
	}
}
