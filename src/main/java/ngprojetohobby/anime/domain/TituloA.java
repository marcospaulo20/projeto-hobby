package ngprojetohobby.anime.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

import ngprojetohobby.manga.domain.Manga;

@Entity
@Table(name = "titulo_a")
public class TituloA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;
	@Column(nullable = false)
	private String classificacao;

	private String emissora;

	@Column(name = "ano_original")
	@Temporal(value = TemporalType.DATE)
	private Date anoOriginal;

	@Column(nullable = false)
	private String formato;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "generos_a", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "tipo")
	private List<String> generos;

	@JoinColumn(name = "anime_id", nullable = false)
	private Long anime;

	@OneToOne(fetch = FetchType.EAGER, targetEntity = Manga.class)
	@JoinTable(name = "anime_manga", joinColumns = {
			@JoinColumn(name = "anime_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "manga_id", referencedColumnName = "id") })
	private Manga manga;

	@OneToMany(mappedBy = "tituloA", fetch = FetchType.EAGER)
	private List<Arco> arcos;

	public TituloA() {
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

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public Long getAnime() {
		return anime;
	}

	public void setAnime(Long anime) {
		this.anime = anime;
	}

	public Manga getManga() {
		return manga;
	}

	public void setManga(Manga manga) {
		this.manga = manga;
	}

	public List<Arco> getArcos() {
		return arcos;
	}

	public void setArcos(List<Arco> arcos) {
		this.arcos = arcos;
	}

}
