package ngprojetohobby.manga.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.Capitulo;
import ngprojetohobby.manga.repositories.CapituloRepository;

@javax.enterprise.context.RequestScoped
public class CapituloServiceImpl implements CapituloService {

	@Inject
	private CapituloRepository capituloRepository;

	@Override
	public List<Capitulo> getAllCapitulos(Long id) {
		return this.capituloRepository.findAllByCol(Capitulo.class, "volume", id);
	}

	@Override
	public Capitulo getById(Long id) {
		return this.capituloRepository.getById(id);
	}

	@Override
	public Capitulo createNewCapitulo(Capitulo capitulo) {
		return this.capituloRepository.create(capitulo);
	}

	@Override
	public Capitulo update(Capitulo capitulo) {
		return this.capituloRepository.update(capitulo);
	}

	@Override
	public void remove(Long id) {
		this.capituloRepository.remove(Capitulo.class, id);
	}

	@Override
	public Integer getNumberOfCapitulos() {
		return this.capituloRepository.countAll(Capitulo.class);
	}

	@Override
	public List<Capitulo> findAllPage(int startPosition, int maxResults, String sortFields) {
		return capituloRepository.findAll(Capitulo.class, startPosition, maxResults, sortFields);
	}
}
