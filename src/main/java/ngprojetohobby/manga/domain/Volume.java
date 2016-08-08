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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
public class Volume implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;
	@Column(length = 100)
	private String arco;

	private Integer paginas;
	@Column(name = "ano_publicacao_jp")
	@Temporal(value = TemporalType.DATE)
	private Date anoPublicacaoJP;
	@Column(name = "ano_publicacao_br")
	@Temporal(value = TemporalType.DATE)
	private Date anoPublicacaoBR;
	@Column(length = 20)
	private String status;
	private Double preco;
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	@Column(name = "titulo_id")
	private Long titulo;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Capitulo.class)
	@JoinTable(name = "volume_capitulo", joinColumns = {
			@JoinColumn(name = "volume_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "capitulo_id", referencedColumnName = "id", unique = true) })
	@Fetch(FetchMode.SUBSELECT)
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

	public String getArco() {
		return arco;
	}

	public void setArco(String arco) {
		this.arco = arco;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Date getAnoPublicacaoJP() {
		return anoPublicacaoJP;
	}

	public void setAnoPublicacaoJP(Date anoPublicacaoJP) {
		this.anoPublicacaoJP = anoPublicacaoJP;
	}

	public Date getAnoPublicacaoBR() {
		return anoPublicacaoBR;
	}

	public void setAnoPublicacaoBR(Date anoPublicacaoBR) {
		this.anoPublicacaoBR = anoPublicacaoBR;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
