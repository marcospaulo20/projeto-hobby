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
@Table(name = "volume")
public class Volume implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;
	@Column(length = 100)
	private String arco;

	@Column(name = "ano_JP")
	@Temporal(value = TemporalType.DATE)
	private Date anoJP;
	@Column(name = "ano_BR")
	@Temporal(value = TemporalType.DATE)
	private Date anoBR;

	private Integer paginas;

	private String status;

	private Boolean statusColecao;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	@JoinColumn(name = "tituloM_id", nullable = false)
	private Long tituloM;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = CapituloM.class)
	@JoinTable(name = "volume_capitulo_m", joinColumns = {
			@JoinColumn(name = "volume_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "capitulo_m_id", referencedColumnName = "id", unique = true) })
	@Fetch(FetchMode.SUBSELECT)
	private List<CapituloM> capitulosM;

	public Volume() {
		super();
		this.statusColecao = Boolean.FALSE;
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

	public Date getAnoJP() {
		return anoJP;
	}

	public void setAnoJP(Date anoJP) {
		this.anoJP = anoJP;
	}

	public Date getAnoBR() {
		return anoBR;
	}

	public void setAnoBR(Date anoBR) {
		this.anoBR = anoBR;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getStatusColecao() {
		return statusColecao;
	}

	public void setStatusColecao(Boolean statusColecao) {
		this.statusColecao = statusColecao;
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
