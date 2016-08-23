package ngprojetohobby.comic.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.comic.domain.Comic;
import ngprojetohobby.comic.repositories.ComicRepository;

@javax.enterprise.context.RequestScoped
public class ComicServiceImpl implements ComicService {

	@Inject
	private ComicRepository comicRepository;

	@Override
	public List<Comic> getAllComics() {
		return this.comicRepository.getAll(Comic.class);
	}

	@Override
	public Comic getById(Long id) {
		return this.comicRepository.getById(id);
	}

	@Override
	public Comic create(Comic comic) {
		return this.comicRepository.create(comic);
	}

	@Override
	public Comic update(Comic comic) {
		return this.comicRepository.update(comic);
	}

	@Override
	public void remove(Long id) {
		this.comicRepository.remove(Comic.class, id);
	}

	@Override
	public Integer getNumberOfComics() {
		return this.comicRepository.countAll(Comic.class);
	}

}
