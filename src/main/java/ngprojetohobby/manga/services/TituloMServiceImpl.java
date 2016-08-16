package ngprojetohobby.manga.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.Manga;
import ngprojetohobby.manga.domain.TituloM;
import ngprojetohobby.manga.repositories.MangaRepository;
import ngprojetohobby.manga.repositories.TituloMRepository;

@javax.enterprise.context.RequestScoped
public class TituloMServiceImpl implements TituloMService {

	@Inject
	private TituloMRepository tituloMRepository;
	
	@Inject
	private MangaRepository mangaRepository;

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
		List<String> categorias = new ArrayList<String>();
		for(String c : titulo.getCategorias()) {
			categorias.add(c);
		}
		
		titulo.setCategorias(categorias);
		titulo = this.tituloMRepository.create(titulo);
	
		Manga manga = mangaRepository.getById(titulo.getManga());
		manga.getTitulosM().add(titulo);
		mangaRepository.update(manga);
		
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
