package br.com.brasili.api.objetoarte.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "objeto_arte_artista")
public class Artista {

	@EmbeddedId
	private ArtistaId artistaId;

	public ArtistaId getArtistaId() {
		return artistaId;
	}

	public void setArtistaId(ArtistaId artistaId) {
		this.artistaId = artistaId;
	}
}
