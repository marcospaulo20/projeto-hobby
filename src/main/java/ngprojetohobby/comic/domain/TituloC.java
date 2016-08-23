package ngprojetohobby.comic.domain;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import ngprojetohobby.manga.domain.CapituloM;

@Entity
@Table(name = "titulo_c")
public class TituloC implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;
	@Column(nullable = false)
	private String classificacao;

	private String editora;

	@Column(name = "pub_original")
	@Temporal(value = TemporalType.DATE)
	private Date pubOriginal;

	@Column(length = 200)
	private String escritor;
	@Column(length = 200)
	private String arte;

	@Column(nullable = false)
	private String paisOrigem;

	private Boolean status;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "generos_c", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "tipo")
	private List<String> generos;

	@JoinColumn(name = "comic_id", nullable = false)
	private Long comic;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = CapituloM.class)
	@JoinTable(name = "tituloc_capitulo_c", joinColumns = {
			@JoinColumn(name = "tituloc_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "capitulo_c_id", referencedColumnName = "id", unique = true) })
	@Fetch(FetchMode.SUBSELECT)
	private List<CapituloC> capitulosC;

	public TituloC() {
		super();
		this.status = Boolean.FALSE;
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

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Date getPubOriginal() {
		return pubOriginal;
	}

	public void setPubOriginal(Date pubOriginal) {
		this.pubOriginal = pubOriginal;
	}

	public String getEscritor() {
		return escritor;
	}

	public void setEscritor(String escritor) {
		this.escritor = escritor;
	}

	public String getArte() {
		return arte;
	}

	public void setArte(String arte) {
		this.arte = arte;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public Long getComic() {
		return comic;
	}

	public void setComic(Long comic) {
		this.comic = comic;
	}

	public List<CapituloC> getCapitulosC() {
		return capitulosC;
	}

	public void setCapitulosC(List<CapituloC> capitulosC) {
		this.capitulosC = capitulosC;
	}

}
