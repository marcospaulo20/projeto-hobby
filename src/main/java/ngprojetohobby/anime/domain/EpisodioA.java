package ngprojetohobby.anime.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "episodio_a")
public class EpisodioA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;
	@Column(length = 20, nullable = false)
	private String numero;
	@Column(name = "tipo", nullable = false)
	private String tipo;

	private Boolean status;

	private Boolean statusVirtual;

	@Column(name = "arco_id", nullable = false)
	private Long arco;

	public EpisodioA() {
		super();
		this.status = Boolean.FALSE;
		this.statusVirtual = Boolean.FALSE;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Long getArco() {
		return arco;
	}

	public void setArco(Long arco) {
		this.arco = arco;
	}

}
