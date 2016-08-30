package ngprojetohobby.serie.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "episodio_s")
public class EpisodioS implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;

	@Column(length = 10, nullable = false)
	private String numero;

	private Boolean status;

	private Boolean statusVirtual;

	@Column(name = "temporada")
	private Long temporada;

	public EpisodioS() {
		super();
		this.status = Boolean.FALSE;
		this.statusVirtual = Boolean.FALSE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getStatusVirtual() {
		return statusVirtual;
	}

	public void setStatusVirtual(Boolean statusVirtual) {
		this.statusVirtual = statusVirtual;
	}

	public Long getTemporada() {
		return temporada;
	}

	public void setTemporada(Long temporada) {
		this.temporada = temporada;
	}

}
