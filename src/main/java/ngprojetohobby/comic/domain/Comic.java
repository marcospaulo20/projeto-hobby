package ngprojetohobby.comic.domain;

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
@Table(name = "comic")
public class Comic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "comic", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<TituloC> titulosC;
	@Transient
	private byte[] Imagem;
	
	public Comic() {
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

	public List<TituloC> getTitulosC() {
		return titulosC;
	}

	public void setTitulosC(List<TituloC> titulosC) {
		this.titulosC = titulosC;
	}
	
	public byte[] getImagem() {
		byte[] image = null;
		if (!this.getTitulosC().isEmpty() && !this.getTitulosC().get(0).getCapitulosC().isEmpty()) {
			image = this.getTitulosC().get(0).getCapitulosC().get(0).getImagem();			
		}
		return image;
	}

}
