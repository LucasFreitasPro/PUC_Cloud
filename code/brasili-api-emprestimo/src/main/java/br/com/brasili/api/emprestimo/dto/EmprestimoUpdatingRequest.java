package br.com.brasili.api.emprestimo.dto;

import java.time.LocalDate;

public class EmprestimoUpdatingRequest {

	private LocalDate dataDevolucao;

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
}
