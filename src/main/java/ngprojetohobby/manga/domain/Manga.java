package ngprojetohobby.manga.domain;

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
	@Transient
	private byte[] Imagem;

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
		byte[] image = null;
		if (!this.getTitulosM().isEmpty() && !this.getTitulosM().get(0).getVolumes().isEmpty()) {
			image = this.getTitulosM().get(0).getVolumes().get(0).getImagem();
		}
		return image;
	}

}
