package ngprojetohobby.manga.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Manga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 255, nullable = false)
	private String titulo;

	public Manga() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

}
