package br.com.brasili.api.objetoarte.dto;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class EmprestimoUpdatingRequest {

	private UUID idColecao;

	@NotNull
	private LocalDate dataEmprestimo;

	@NotNull
	private LocalDate dataDevolucao;

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

	public UUID getIdColecao() {
		return idColecao;
	}

	public void setIdColecao(UUID idColecao) {
		this.idColecao = idColecao;
	}
}
