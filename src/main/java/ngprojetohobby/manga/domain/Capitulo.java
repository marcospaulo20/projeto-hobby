package ngprojetohobby.manga.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Capitulo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;
	@Column(nullable = false)
	private Integer numero;
	@Column(name = "ano_publicacao_jp")
	@Temporal(value = TemporalType.DATE)
	private Date anoPublicacaoJP;

	private Boolean status;

	@Column(name = "volume_id")
	private Long volume;

	public Capitulo() {
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getAnoPublicacaoJP() {
		return anoPublicacaoJP;
	}

	public void setAnoPublicacaoJP(Date anoPublicacaoJP) {
		this.anoPublicacaoJP = anoPublicacaoJP;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

}
