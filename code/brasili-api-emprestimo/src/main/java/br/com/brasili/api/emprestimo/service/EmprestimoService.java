package br.com.brasili.api.emprestimo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.emprestimo.dto.EmprestimoRegisteringRequest;
import br.com.brasili.api.emprestimo.dto.EmprestimoUpdatingRequest;
import br.com.brasili.api.emprestimo.model.Emprestimo;
import br.com.brasili.api.emprestimo.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

	private final EmprestimoRepository repository;

	public EmprestimoService(EmprestimoRepository repository) {
		this.repository = repository;
	}

	public List<Emprestimo> findAll() {
		return this.repository.findAll();
	}

	public Optional<Emprestimo> findById(UUID idEmprestimo) {
		return this.repository.findById(idEmprestimo);
	}

	public Emprestimo create(EmprestimoRegisteringRequest emprestimoRegisteringRequest) {
		Emprestimo emprestimo = new Emprestimo();
		return save(emprestimo);
	}

	public Emprestimo update(EmprestimoUpdatingRequest emprestimoUpdatingRequest) {
		Emprestimo emprestimo = new Emprestimo();
		return save(emprestimo);
	}

	@Transactional
	private Emprestimo save(Emprestimo emprestimo) {
		return this.repository.save(emprestimo);
	}

	@Transactional
	public void deleteById(UUID idEmprestimo) {
		this.repository.deleteById(idEmprestimo);
	}
}
