package ngprojetohobby.manga.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.TituloM;
import ngprojetohobby.manga.repositories.TituloMRepository;

@javax.enterprise.context.RequestScoped
public class TituloMServiceImpl implements TituloMService {

	@Inject
	private TituloMRepository tituloMRepository;

	@Override
	public List<TituloM> getAllTitulos(Long id) {
		return this.tituloMRepository.findAllByCol(TituloM.class, "manga", id);
	}

	@Override
	public TituloM getById(Long id) {
		return this.tituloMRepository.getById(id);
	}

	@Override
	public TituloM create(TituloM titulo) {
		if(!titulo.getCategorias().isEmpty()) {
			List<String> categorias = new ArrayList<String>();
			for (String c : titulo.getCategorias()) {
				categorias.add(c);
			}
			titulo.setCategorias(categorias);
		}
		
		titulo = this.tituloMRepository.create(titulo);
		
		return titulo;
	}

	@Override
	public TituloM update(TituloM titulo) {
		return this.tituloMRepository.update(titulo);
	}

	@Override
	public void remove(Long id) {
		this.tituloMRepository.remove(TituloM.class, id);
	}

	@Override
	public Integer getNumberOfTitulos() {
		return this.tituloMRepository.countAll(TituloM.class);
	}
}
