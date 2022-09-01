package br.com.brasili.api.emprestimo.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ObjetoArteId implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID idObjetoArte;

	@Column(name = "id_emprestimo")
	private UUID idEmprestimo;

	public UUID getIdObjetoArte() {
		return idObjetoArte;
	}

	public void setIdObjetoArte(UUID idObjetoArte) {
		this.idObjetoArte = idObjetoArte;
	}

	public UUID getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(UUID idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}
}
