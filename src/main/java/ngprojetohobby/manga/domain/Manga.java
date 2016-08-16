package ngprojetohobby.manga.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="manga")
public class Manga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 255, nullable = false)
	private String nome;

	@Column(length = 30, nullable = false)
	private String classificacao;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = TituloM.class)
	@JoinTable(name = "manga_titulo", joinColumns = {
			@JoinColumn(name = "manga_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "titulo_id", referencedColumnName = "id", unique = true) })
	@Fetch(FetchMode.SUBSELECT)
	private List<TituloM> titulosM;

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

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public List<TituloM> getTitulosM() {
		return titulosM;
	}

	public void setTitulosM(List<TituloM> titulosM) {
		this.titulosM = titulosM;
	}

}
