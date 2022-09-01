package br.com.brasili.api.emprestimo.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class ObjetoArteRegisteringRequest {

	@NotNull
	private UUID idObjetoArte;

	public UUID getIdObjetoArte() {
		return idObjetoArte;
	}

	public void setIdObjetoArte(UUID idObjetoArte) {
		this.idObjetoArte = idObjetoArte;
	}
}
