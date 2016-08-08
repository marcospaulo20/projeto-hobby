package ngprojetohobby.manga.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.Manga;
import ngprojetohobby.manga.domain.Titulo;
import ngprojetohobby.manga.repositories.MangaRepository;
import ngprojetohobby.manga.repositories.TituloRepository;

@javax.enterprise.context.RequestScoped
public class TituloServiceImpl implements TituloService {

	@Inject
	private TituloRepository tituloRepository;
	
	@Inject
	private MangaRepository mangaRepository;

	@Override
	public List<Titulo> getAllTitulos(Long id) {
		return this.tituloRepository.findAllByCol(Titulo.class, "manga", id);
	}

	@Override
	public Titulo getById(Long id) {
		return this.tituloRepository.getById(id);
	}

	@Override
	public Titulo create(Titulo titulo) {
		List<String> categorias = new ArrayList<String>();
		for(String c : titulo.getCategorias()) {
			categorias.add(c);
		}
		
		titulo.setCategorias(categorias);
		titulo = this.tituloRepository.create(titulo);
	
		Manga manga = mangaRepository.getById(titulo.getManga());
		manga.getTitulos().add(titulo);
		mangaRepository.update(manga);
		
		return titulo;
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
