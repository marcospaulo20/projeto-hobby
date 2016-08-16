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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
@Table(name="volume")
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
	private Boolean status;
	private Double preco;
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	@Column(name = "titulo_m_id")
	private Long tituloM;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = CapituloM.class)
	@JoinTable(name = "volume_capitulo_m", joinColumns = {
			@JoinColumn(name = "volume_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "capitulo_m_id", referencedColumnName = "id", unique = true) })
	@Fetch(FetchMode.SUBSELECT)
	private List<CapituloM> capitulosM;

	public Volume() {
		super();
		this.status = Boolean.FALSE;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
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

	public Long getTituloM() {
		return tituloM;
	}

	public void setTituloM(Long tituloM) {
		this.tituloM = tituloM;
	}

	public List<CapituloM> getCapitulosM() {
		return capitulosM;
	}

	public void setCapitulosM(List<CapituloM> capitulosM) {
		this.capitulosM = capitulosM;
	}

}
