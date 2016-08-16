package ngprojetohobby.anime.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.anime.domain.Anime;
import ngprojetohobby.anime.repositories.AnimeRepository;

@javax.enterprise.context.RequestScoped
public class AnimeServiceImpl implements AnimeService {

	@Inject
	private AnimeRepository animeRepository;
	
	@Override
	public List<Anime> getAllAnimes() {
		return this.animeRepository.getAll(Anime.class);
	}

	@Override
	public Anime getById(Long id) {
		return this.animeRepository.getById(id);
	}

	@Override
	public Anime create(Anime anime) {
		return this.animeRepository.create(anime);
	}

	@Override
	public Anime update(Anime anime) {
		return this.animeRepository.update(anime);
	}

	@Override
	public void remove(Long id) {
		this.animeRepository.remove(Anime.class, id);
	}

	@Override
	public Integer getNumberOfAnimes() {
		return this.animeRepository.countAll(Anime.class);
	}

}
