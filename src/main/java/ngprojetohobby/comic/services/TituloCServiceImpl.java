package ngprojetohobby.comic.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.comic.domain.TituloC;
import ngprojetohobby.comic.repositories.TituloCRepository;

@javax.enterprise.context.RequestScoped
public class TituloCServiceImpl implements TituloCService {

	@Inject
	private TituloCRepository tituloCRepository;

	@Override
	public List<TituloC> getAllTitulos(Long id) {
		return this.tituloCRepository.findAllByCol(TituloC.class, "comic", id);
	}

	@Override
	public TituloC getById(Long id) {
		return this.tituloCRepository.getById(id);
	}

	@Override
	public TituloC create(TituloC titulo) {
		List<String> generos = new ArrayList<String>();
		for (String g : titulo.getGeneros()) {
			generos.add(g);
		}

		titulo.setGeneros(generos);
		titulo = this.tituloCRepository.create(titulo);
		
		return titulo;
	}

	@Override
	public TituloC update(TituloC titulo) {
		return this.tituloCRepository.update(titulo);
	}

	@Override
	public void remove(Long id) {
		this.tituloCRepository.remove(TituloC.class, id);
	}

	@Override
	public Integer getNumberOfTitulos() {
		return this.tituloCRepository.countAll(TituloC.class);
	}
}