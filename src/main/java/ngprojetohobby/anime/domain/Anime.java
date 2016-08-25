package ngprojetohobby.anime.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "anime")
public class Anime implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "anime", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<TituloA> titulosA;
	@Transient
	private byte[] Imagem;
	
	public Anime() {
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

	public List<TituloA> getTitulosA() {
		return titulosA;
	}

	public void setTitulosA(List<TituloA> titulosA) {
		this.titulosA = titulosA;
	}

	public byte[] getImagem() {
		byte[] image = null;
		if (!this.getTitulosA().isEmpty() && !this.getTitulosA().get(0).getArcos().isEmpty()) {
			image = this.getTitulosA().get(0).getArcos().get(0).getImagem();			
		}
		return image;
	}
}