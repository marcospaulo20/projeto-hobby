package ngprojetohobby.manga.repositories;

import javax.ejb.Stateless;

import ngprojetohobby.manga.domain.Volume;
import ngprojetohobby.repositories.contract.RepositoryImpl;

@Stateless
public class VolumeRepository extends RepositoryImpl<Volume> {

	public VolumeRepository() {
		super(Volume.class);
	}

}
