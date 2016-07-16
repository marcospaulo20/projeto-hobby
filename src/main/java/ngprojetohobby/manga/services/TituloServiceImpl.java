package ngprojetohobby.manga.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.Titulo;
import ngprojetohobby.manga.repositories.TituloRepository;

@javax.enterprise.context.RequestScoped
public class TituloServiceImpl implements TituloService {

	@Inject
	private TituloRepository tituloRepository;

	@Override
	public List<Titulo> getAllTitulos(Long id) {
		return this.tituloRepository.findAllByCol(Titulo.class, "manga", id);
	}

	@Override
	public Titulo getById(Long id) {
		return this.tituloRepository.getById(id);
	}

	@Override
	public Titulo createNewTitulo(Titulo titulo) {
		return this.tituloRepository.create(titulo);
	}

	@Override
	public Titulo update(Titulo titulo) {
		return this.tituloRepository.update(titulo);
	}

	@Override
	public void remove(Long id) {
		this.tituloRepository.remove(Titulo.class, id);
	}

	@Override
	public Integer getNumberOfTitulos() {
		return this.tituloRepository.countAll(Titulo.class);
	}
}
