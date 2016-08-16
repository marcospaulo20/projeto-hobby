package ngprojetohobby.anime.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "genero", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "tipo")
	private List<String> generos;

	@Column(length = 40, nullable = false)
	private String formato;

	@Column(name = "ano")
	@Temporal(value = TemporalType.DATE)
	private Date ano;

	@Column(name = "anime_id")
	private Long anime;

	@OneToOne(fetch = FetchType.EAGER, targetEntity = Manga.class)
	@JoinTable(name = "anime_manga", joinColumns = {
			@JoinColumn(name = "anime_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "manga_id", referencedColumnName = "id") })
	private Manga manga;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Arco.class)
	@JoinTable(name = "titulo_arco", joinColumns = {
			@JoinColumn(name = "titulo_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "arco_id", referencedColumnName = "id", unique = true) })
	@Fetch(FetchMode.SUBSELECT)
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

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
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
