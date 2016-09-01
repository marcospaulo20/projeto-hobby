package ngprojetohobby.anime.domain;

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
@Table(name = "arco")
public class Arco implements Serializable {

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
	@Column(name = "capa")
	private Boolean capa;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	@JoinColumn(name = "tituloA_id", nullable = false)
	private Long tituloA;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = EpisodioA.class)
	@JoinTable(name = "arco_episodio", joinColumns = {
			@JoinColumn(name = "arco_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "episodio_id", referencedColumnName = "id", unique = true) })
	@Fetch(FetchMode.SUBSELECT)
	private List<EpisodioA> episodios;

	public Arco() {
		super();
		this.status = Boolean.FALSE;
		this.capa = Boolean.FALSE;
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

	public Boolean getCapa() {
		return capa;
	}

	public void setCapa(Boolean capa) {
		this.capa = capa;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Long getTituloA() {
		return tituloA;
	}

	public void setTituloA(Long tituloA) {
		this.tituloA = tituloA;
	}

	public List<EpisodioA> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<EpisodioA> episodios) {
		this.episodios = episodios;
	}

}
