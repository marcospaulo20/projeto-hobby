package ngprojetohobby.manga.domain;

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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
public class Titulo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String titulo;
	@Column(length = 30, nullable = false)
	private String status;
	@Column(length = 30)
	private String termo;
	@Column(name = "ano")
	@Temporal(value = TemporalType.DATE)
	private Date ano;
	@Column(length = 100, nullable = false)
	private String autor;
	@Column(length = 100, nullable = false)
	private String desenhista;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="categoria", 
		joinColumns = @JoinColumn(name="id")
	)
	@Column(name = "tipo")
	private List<String> categorias;
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;
	@Column(name = "manga_id")
	private Long manga;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Volume.class)
	@JoinTable(name = "titulo_volume", joinColumns = {
			@JoinColumn(name = "titulo_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "volume_id", referencedColumnName = "id", unique = true) })
	@Fetch(FetchMode.SUBSELECT)
	private List<Volume> volumes;

	public Titulo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTermo() {
		return termo;
	}

	public void setTermo(String termo) {
		this.termo = termo;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDesenhista() {
		return desenhista;
	}

	public void setDesenhista(String desenhista) {
		this.desenhista = desenhista;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
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
