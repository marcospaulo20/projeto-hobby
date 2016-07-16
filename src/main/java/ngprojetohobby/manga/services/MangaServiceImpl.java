package ngprojetohobby.manga.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.manga.domain.Manga;
import ngprojetohobby.manga.repositories.MangaRepository;

@javax.enterprise.context.RequestScoped
public class MangaServiceImpl implements MangaService {

	@Inject
	private MangaRepository mangaRepository;

	@Override
	public List<Manga> getAllMangas() {
		return this.mangaRepository.getAll(Manga.class);
	}

	@Override
	public Manga getById(Long id) {
		return this.mangaRepository.getById(id);
	}

	@Override
	public Manga createNewManga(Manga Manga) {
		return this.mangaRepository.create(Manga);
	}

	@Override
	public Manga update(Manga manga) {
		return this.mangaRepository.update(manga);
	}

	@Override
	public void remove(Long id) {
		this.mangaRepository.remove(Manga.class, id);
	}

	@Override
	public Integer getNumberOfMangas() {
		return this.mangaRepository.countAll(Manga.class);
	}

	@Override
	public List<Manga> findAllPage(int startPosition, int maxResults, String sortFields) {
		return mangaRepository.findAll(Manga.class, startPosition, maxResults, sortFields);
	}
}
