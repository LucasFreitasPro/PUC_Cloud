package br.com.brasili.api.emprestimo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emprestimo_objeto_arte")
public class ObjetoArte {

	@EmbeddedId
	private ObjetoArteId objetoArteId;

	public ObjetoArteId getObjetoArteId() {
		return objetoArteId;
	}

	public void setObjetoArteId(ObjetoArteId objetoArteId) {
		this.objetoArteId = objetoArteId;
	}
}
