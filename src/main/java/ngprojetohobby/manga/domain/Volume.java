package ngprojetohobby.manga.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import ngprojetohobby.util.domain.Pessoa;

@Entity
public class Volume implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 1, max = 255)
	private String nome;
	@Column(name = "ano_publicacao")
	@Temporal(value = TemporalType.DATE)
	private Date anoPublicacao;
	@Column(length = 20)
	private String status;
	@Column(length = 20)
	private String pais;
	private Double preco;
	private String revista;
	@JoinColumn(name = "roterista_id", referencedColumnName = "id")
	private Pessoa roteirista;
	@JoinColumn(name = "desenhista_id", referencedColumnName = "id")
	private Pessoa desenhista;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	@Column(name = "titulo_id")
	private Long titulo;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Capitulo.class)
	private List<Capitulo> capitulos;

	public Volume() {
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

	public Date getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Date anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getRevista() {
		return revista;
	}

	public void setRevista(String revista) {
		this.revista = revista;
	}

	public Pessoa getRoteirista() {
		return roteirista;
	}

	public void setRoteirista(Pessoa roteirista) {
		this.roteirista = roteirista;
	}

	public Pessoa getDesenhista() {
		return desenhista;
	}

	public void setDesenhista(Pessoa desenhista) {
		this.desenhista = desenhista;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Long getTitulo() {
		return titulo;
	}

	public void setTitulo(Long titulo) {
		this.titulo = titulo;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

}
