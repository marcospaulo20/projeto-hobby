package ngprojetohobby.serie.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ngprojetohobby.serie.domain.Serie;
import ngprojetohobby.serie.domain.TituloS;
import ngprojetohobby.serie.repositories.SerieRepository;
import ngprojetohobby.serie.repositories.TituloSRepository;

@javax.enterprise.context.RequestScoped
public class TituloSServiceImpl implements TituloSService {

	@Inject
	private TituloSRepository tituloRepository;
	
	@Inject
	private SerieRepository serieRepository;

	@Override
	public List<TituloS> getAllTitulos(Long id) {
		return this.tituloRepository.findAllByCol(TituloS.class, "serie", id);
	}

	@Override
	public TituloS getById(Long id) {
		return this.tituloRepository.getById(id);
	}

	@Override
	public TituloS create(TituloS titulo) {
		if(!titulo.getGeneros().isEmpty()) {
			List<String> generos = new ArrayList<String>();
			for(String g : titulo.getGeneros()) {
				generos.add(g);
			}
			
			titulo.setGeneros(generos);
		}
		titulo = this.tituloRepository.create(titulo);
	
		Serie serie = serieRepository.getById(titulo.getSerie());
		serie.getTitulosS().add(titulo);
		serieRepository.update(serie);
		
		return titulo;
	}

	@Override
	public TituloS update(TituloS titulo) {
		return this.tituloRepository.update(titulo);
	}

	@Override
	public void remove(Long id) {
		this.tituloRepository.remove(TituloS.class, id);
	}

	@Override
	public Integer getNumberOfTitulos() {
		return this.tituloRepository.countAll(TituloS.class);
	}

}
