package ngprojetohobby.serie.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "temporada")
public class Temporada implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;

	@Column(name = "ano")
	@Temporal(value = TemporalType.DATE)
	private Date ano;

	private Boolean status;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	@JoinColumn(name = "titulo_s_id", nullable = false)
	private Long tituloS;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = EpisodioS.class)
	@JoinTable(name = "temporada_episodio_s", joinColumns = {
			@JoinColumn(name = "temporada_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "episodio_s_id", referencedColumnName = "id", unique = true) })
	@Fetch(FetchMode.SUBSELECT)
	private List<EpisodioS> episodiosS;

	public Temporada() {
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

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Long getTituloS() {
		return tituloS;
	}

	public void setTituloS(Long tituloS) {
		this.tituloS = tituloS;
	}

	public List<EpisodioS> getEpisodiosS() {
		return episodiosS;
	}

	public void setEpisodiosS(List<EpisodioS> episodiosS) {
		this.episodiosS = episodiosS;
	}

}
