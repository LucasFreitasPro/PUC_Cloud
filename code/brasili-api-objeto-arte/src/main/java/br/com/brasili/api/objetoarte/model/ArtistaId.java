package br.com.brasili.api.objetoarte.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArtistaId implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID idArtista;

	@Column(name = "id_objeto_arte")
	private UUID idObjetoArte;

	public UUID getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(UUID idArtista) {
		this.idArtista = idArtista;
	}

	public UUID getIdObjetoArte() {
		return idObjetoArte;
	}

	public void setIdObjetoArte(UUID idObjetoArte) {
		this.idObjetoArte = idObjetoArte;
	}
}
