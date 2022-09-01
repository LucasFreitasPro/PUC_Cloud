package br.com.brasili.api.exposicao.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ObjetoArteId implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID idObjetoArte;

	@Column(name = "id_exposicao")
	private UUID idExposicao;

	public ObjetoArteId() {

	}

	public ObjetoArteId(UUID idObjetoArte, UUID idExposicao) {
		this.idObjetoArte = idObjetoArte;
		this.idExposicao = idExposicao;
	}

	public UUID getIdObjetoArte() {
		return idObjetoArte;
	}

	public void setIdObjetoArte(UUID idObjetoArte) {
		this.idObjetoArte = idObjetoArte;
	}

	public UUID getIdExposicao() {
		return idExposicao;
	}

	public void setIdExposicao(UUID idExposicao) {
		this.idExposicao = idExposicao;
	}
}
