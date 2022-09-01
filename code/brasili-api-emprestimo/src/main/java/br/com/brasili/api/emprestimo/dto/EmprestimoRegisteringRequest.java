package br.com.brasili.api.emprestimo.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmprestimoRegisteringRequest {

	@NotNull
	private UUID idMuseuAutorEmprestimo;

	@Valid
	@Size(min = 1)
	private List<ObjetoArteRegisteringRequest> objetosArte;

	@NotNull
	private LocalDate dataEmprestimo;

	@NotNull
	private LocalDate dataDevolucao;

	public UUID getIdMuseuAutorEmprestimo() {
		return idMuseuAutorEmprestimo;
	}

	public void setIdMuseuAutorEmprestimo(UUID idMuseuAutorEmprestimo) {
		this.idMuseuAutorEmprestimo = idMuseuAutorEmprestimo;
	}

	public List<ObjetoArteRegisteringRequest> getObjetosArte() {
		return objetosArte;
	}

	public void setObjetosArte(List<ObjetoArteRegisteringRequest> objetosArte) {
		this.objetosArte = objetosArte;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
}
