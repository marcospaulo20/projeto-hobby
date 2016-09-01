package ngprojetohobby.comic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "capitulo_c")
public class CapituloC implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String nome;

	@Column(length = 20, nullable = false)
	private String numero;

	@Column(length = 200)
	private String escritor;
	@Column(length = 200)
	private String arte;
	@Column(length = 200)
	private String colorista;
	@Column(length = 200)
	private String editor;

	@Column(name = "ano")
	@Temporal(value = TemporalType.DATE)
	private Date ano;

	private Boolean status;
	private Boolean statusVirtual;

	@Column(name = "capa")
	private Boolean capa;

	@Column(name = "titulo_c_id", nullable = false)
	private Long tituloC;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] imagem;

	public CapituloC() {
		super();
		this.status = Boolean.FALSE;
		this.statusVirtual = Boolean.FALSE;
		this.capa = Boolean.FALSE;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEscritor() {
		return escritor;
	}

	public void setEscritor(String escritor) {
		this.escritor = escritor;
	}

	public String getArte() {
		return arte;
	}

	public void setArte(String arte) {
		this.arte = arte;
	}

	public String getColorista() {
		return colorista;
	}

	public void setColorista(String colorista) {
		this.colorista = colorista;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getStatusVirtual() {
		return statusVirtual;
	}

	public void setStatusVirtual(Boolean statusVirtual) {
		this.statusVirtual = statusVirtual;
	}

	public Boolean getCapa() {
		return capa;
	}

	public void setCapa(Boolean capa) {
		this.capa = capa;
	}

	public Long getTituloC() {
		return tituloC;
	}

	public void setTituloC(Long tituloC) {
		this.tituloC = tituloC;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

}
