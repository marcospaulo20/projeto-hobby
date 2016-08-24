package ngprojetohobby.serie.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ngprojetohobby.comic.domain.Comic;

@Entity
@Table(name = "titulo_s")
public class TituloS implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;
	@Column(nullable = false)
	private String classificacao;

	@Column(length = 100)
	private String emissora;

	@Column(name = "ano_original")
	@Temporal(value = TemporalType.DATE)
	private Date anoOriginal;

	@Column(name = "pais_origem")
	private String paisOrigem;

	private String status;

	@OneToOne(fetch = FetchType.EAGER, targetEntity = Comic.class)
	@JoinTable(name = "serie_comic", joinColumns = {
			@JoinColumn(name = "serie_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "comic_id", referencedColumnName = "id") })
	private Comic comic;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "generos_s", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "tipo")
	private List<String> generos;

	@OneToMany(mappedBy = "tituloS", fetch = FetchType.EAGER)
	private Set<Temporada> temporadas;

	@JoinColumn(name = "serie_id", nullable = false)
	private Long serie;

	public TituloS() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getEmissora() {
		return emissora;
	}

	public void setEmissora(String emissora) {
		this.emissora = emissora;
	}

	public Date getAnoOriginal() {
		return anoOriginal;
	}

	public void setAnoOriginal(Date anoOriginal) {
		this.anoOriginal = anoOriginal;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public Set<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(Set<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

	public Long getSerie() {
		return serie;
	}

	public void setSerie(Long serie) {
		this.serie = serie;
	}

}
