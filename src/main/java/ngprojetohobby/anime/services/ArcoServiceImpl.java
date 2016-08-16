package ngprojetohobby.anime.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.anime.domain.Arco;
import ngprojetohobby.anime.domain.EpisodioA;
import ngprojetohobby.anime.domain.TituloA;
import ngprojetohobby.anime.repositories.ArcoRepository;
import ngprojetohobby.anime.repositories.EpisodioARepository;
import ngprojetohobby.anime.repositories.TituloARepository;

@javax.enterprise.context.RequestScoped
public class ArcoServiceImpl implements ArcoService {

	@Inject
	private ArcoRepository arcoRepository;

	@Inject
	private TituloARepository tituloRepository;
	
	@Inject
	private EpisodioARepository episodioRepository;

	@Override
	public List<Arco> getAllArcos(Long id) {
		return this.arcoRepository.findAllByCol(Arco.class, "tituloA", id);
	}

	@Override
	public Arco getById(Long id) {
		return this.arcoRepository.getById(id);
	}

	@Override
	public Arco create(Arco arco) {
		arco = this.arcoRepository.create(arco);

		TituloA titulo = tituloRepository.getById(arco.getTituloA());
		titulo.getArcos().add(arco);
		tituloRepository.update(titulo);

		return arco;
	}

	@Override
	public Arco update(Arco arco) {
		
		if(arco.getStatus() == true) {
			for(EpisodioA e : arco.getEpisodios()) {
				e.setStatus(true);
				this.episodioRepository.update(e);
			}
		}
		return this.arcoRepository.update(arco);
	}

	@Override
	public void remove(Long id) {
		this.arcoRepository.remove(Arco.class, id);
	}

	@Override
	public Integer getNumberOfArcos() {
		return this.arcoRepository.countAll(Arco.class);
	}
}
