package ngprojetohobby.comic.services;

import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.comic.domain.CapituloC;
import ngprojetohobby.comic.domain.Comic;
import ngprojetohobby.comic.domain.TituloC;
import ngprojetohobby.comic.repositories.CapituloCRepository;
import ngprojetohobby.comic.repositories.ComicRepository;
import ngprojetohobby.comic.repositories.TituloCRepository;

@javax.enterprise.context.RequestScoped
public class CapituloCServiceImpl implements CapituloCService {

	@Inject
	private CapituloCRepository capituloCRepository;
	
	@Inject
	private TituloCRepository tituloCRepository;

	@Inject
	private ComicRepository comicRepository;
	
	@Override
	public List<CapituloC> getAllCapitulosC(Long id) {
		return this.capituloCRepository.findAllByCol(CapituloC.class, "tituloC", id);
	}

	@Override
	public CapituloC getById(Long id) {
		return this.capituloCRepository.getById(id);
	}

	@Override
	public CapituloC create(CapituloC capituloC) {		
		if(capituloC.getPrimeiroCapitulo()) {
			TituloC tituloC = tituloCRepository.getById(capituloC.getTituloC());
			if(capituloC.getImagem() != null) {
				Comic comic = comicRepository.getById(tituloC.getComic());
				comic.setImagem(capituloC.getImagem());
				comicRepository.update(comic);
			}
		}
		
		capituloC = this.capituloCRepository.create(capituloC); 
			
		TituloC tituloC = tituloCRepository.getById(capituloC.getTituloC());
		tituloC.getCapitulosC().add(capituloC);
		tituloCRepository.update(tituloC);
		
		return capituloC;
	}

	@Override
	public CapituloC update(CapituloC capituloM) {
		return this.capituloCRepository.update(capituloM);
	}

	@Override
	public void remove(Long id) {
		CapituloC capituloC = capituloCRepository.getById(id);
		TituloC tituloC = tituloCRepository.getById(capituloC.getTituloC());
		
		for(int i = 0; i < tituloC.getCapitulosC().size(); i++) {
			CapituloC c = tituloC.getCapitulosC().get(i);
			if(c.getId().equals(capituloC.getId())) {
				tituloC.getCapitulosC().remove(c);
				tituloCRepository.update(tituloC);
				break;
			}
		}
		capituloCRepository.remove(CapituloC.class, id);
	}

	@Override
	public Integer getNumberOfCapitulosC() {
		return this.capituloCRepository.countAll(CapituloC.class);
	}

}
