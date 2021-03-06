package ngprojetohobby.manga.domain;

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
@Table(name = "manga")
public class Manga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 255, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "manga", fetch = FetchType.EAGER, targetEntity = TituloM.class)
	@JsonIgnore
	private List<TituloM> titulosM;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	public Manga() {
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

	public List<TituloM> getTitulosM() {
		return titulosM;
	}

	public void setTitulosM(List<TituloM> titulosM) {
		this.titulosM = titulosM;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

}
