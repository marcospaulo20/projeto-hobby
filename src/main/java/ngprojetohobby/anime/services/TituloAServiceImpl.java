package ngprojetohobby.anime.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.anime.domain.Anime;
import ngprojetohobby.anime.domain.TituloA;
import ngprojetohobby.anime.repositories.AnimeRepository;
import ngprojetohobby.anime.repositories.TituloARepository;

@javax.enterprise.context.RequestScoped
public class TituloAServiceImpl implements TituloAService {

	@Inject
	private TituloARepository tituloRepository;
	
	@Inject
	private AnimeRepository animeRepository;

	@Override
	public List<TituloA> getAllTitulos(Long id) {
		return this.tituloRepository.findAllByCol(TituloA.class, "anime", id);
	}

	@Override
	public TituloA getById(Long id) {
		return this.tituloRepository.getById(id);
	}

	@Override
	public TituloA create(TituloA titulo) {
		List<String> generos = new ArrayList<String>();
		for(String g : titulo.getGeneros()) {
			generos.add(g);
		}
		
		titulo.setGeneros(generos);
		titulo = this.tituloRepository.create(titulo);
	
		Anime anime = animeRepository.getById(titulo.getAnime());
		anime.getTitulosA().add(titulo);
		animeRepository.update(anime);
		
		return titulo;
	}

	@Override
	public TituloA update(TituloA titulo) {
		return this.tituloRepository.update(titulo);
	}

	@Override
	public void remove(Long id) {
		this.tituloRepository.remove(TituloA.class, id);
	}

	@Override
	public Integer getNumberOfTitulos() {
		return this.tituloRepository.countAll(TituloA.class);
	}
}