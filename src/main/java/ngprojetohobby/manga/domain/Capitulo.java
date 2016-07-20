package ngprojetohobby.manga.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Capitulo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(max = 255)
	private String nome;
	@NotNull
	private Integer numero;
	@Column(name = "status_acompanhamento")
	private Boolean statusAcompanhamento;
	@Column(name = "status_verificado")
	private Boolean statusVerificado;

	@Column(name = "volume_id", nullable = false)
	private Long titulo;

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

	public Boolean getStatusAcompanhamento() {
		return statusAcompanhamento;
	}

	public void setStatusAcompanhamento(Boolean statusAcompanhamento) {
		this.statusAcompanhamento = statusAcompanhamento;
	}

	public Boolean getStatusVerificado() {
		return statusVerificado;
	}

	public void setStatusVerificado(Boolean statusVerificado) {
		this.statusVerificado = statusVerificado;
	}

	public Long getTitulo() {
		return titulo;
	}

	public void setTitulo(Long titulo) {
		this.titulo = titulo;
	}

}
