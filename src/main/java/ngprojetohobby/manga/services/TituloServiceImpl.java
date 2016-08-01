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
	public Titulo createOrUpdateTitulo(Titulo titulo) {
		if(titulo.getId() == null) {
			titulo = this.tituloRepository.create(titulo); 
			
			Manga manga = mangaRepository.getById(titulo.getManga());
			
			List<Titulo> tList = new ArrayList<Titulo>();
			tList.add(titulo);
			
			for(Titulo titulo_ : manga.getTitulos()) {
				tList.add(titulo_);
			}
			
			manga.setTitulos(tList);
			mangaRepository.update(manga);
		} else {
			titulo = tituloRepository.update(titulo);
		}
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
