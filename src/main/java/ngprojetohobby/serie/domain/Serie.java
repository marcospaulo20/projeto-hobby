package ngprojetohobby.serie.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "serie")
public class Serie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "serie", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<TituloS> titulosS;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	public Serie() {
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

	public List<TituloS> getTitulosS() {
		return titulosS;
	}

	public void setTitulosS(List<TituloS> titulosS) {
		this.titulosS = titulosS;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

}
