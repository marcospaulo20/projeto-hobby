package ngprojetohobby.manga.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "manga")
public class Manga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 255, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "manga", fetch = FetchType.EAGER)
	private Set<TituloM> titulosM;

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

	public Set<TituloM> getTitulosM() {
		return titulosM;
	}

	public void setTitulosM(Set<TituloM> titulosM) {
		this.titulosM = titulosM;
	}

}
