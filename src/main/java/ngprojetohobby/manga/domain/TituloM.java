package ngprojetohobby.manga.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "titulo_m")
public class TituloM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String nome;
	@Column(nullable = false)
	private String classificacao;
	@Column(length = 60)
	private String editora;
	@Column(length = 100, nullable = false)
	private String escritor;
	@Column(length = 100, nullable = false)
	private String arte;

	@Column(name = "pub_original")
	@Temporal(value = TemporalType.DATE)
	private Date pubOriginal;

	@Column(length = 30, nullable = false)
	private String status;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "categorias_m", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "tipo")
	private List<String> categorias;

	@JoinColumn(name = "manga_id", nullable = false)
	private Long manga;

	@OneToMany(mappedBy = "tituloM", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Volume> volumes;

	public TituloM() {
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

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
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

	public Date getPubOriginal() {
		return pubOriginal;
	}

	public void setPubOriginal(Date pubOriginal) {
		this.pubOriginal = pubOriginal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	public Long getManga() {
		return manga;
	}

	public void setManga(Long manga) {
		this.manga = manga;
	}

	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

}
