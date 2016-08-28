package ngprojetohobby.anime.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.anime.domain.Arco;
import ngprojetohobby.anime.domain.EpisodioA;
import ngprojetohobby.anime.repositories.ArcoRepository;
import ngprojetohobby.anime.repositories.EpisodioARepository;

@javax.enterprise.context.RequestScoped
public class EpisodioServiceImpl implements EpisodioService {

	@Inject
	private EpisodioARepository episodioRepository;

	@Inject
	private ArcoRepository arcoRepository;

	@Override
	public List<EpisodioA> getAllEpisodios(Long id) {
		return this.episodioRepository.findAllByCol(EpisodioA.class, "arco", id);
	}

	@Override
	public EpisodioA getById(Long id) {
		return this.episodioRepository.getById(id);
	}

	@Override
	public EpisodioA create(EpisodioA episodio) {
		episodio = this.episodioRepository.create(episodio);

		Arco arco = arcoRepository.getById(episodio.getArco());
		arco.getEpisodios().add(episodio);
		arcoRepository.update(arco);

		return episodio;
	}

	@Override
	public EpisodioA update(EpisodioA episodio) {
		return this.episodioRepository.update(episodio);
	}

	@Override
	public void remove(Long id) {
		EpisodioA episodio = episodioRepository.getById(id);
		Arco arco = arcoRepository.getById(episodio.getArco());

		for (int i = 0; i < arco.getEpisodios().size(); i++) {
			EpisodioA e = arco.getEpisodios().get(i);
			if (e.getId().equals(episodio.getId())) {
				arco.getEpisodios().remove(e);
				arcoRepository.update(arco);
				break;
			}
		}
		episodioRepository.remove(EpisodioA.class, id);
	}

	@Override
	public Integer getNumberOfEpisodios() {
		return this.episodioRepository.countAll(EpisodioA.class);
	}

}
