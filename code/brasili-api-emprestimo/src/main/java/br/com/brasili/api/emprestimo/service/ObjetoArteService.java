package br.com.brasili.api.emprestimo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.emprestimo.model.ObjetoArte;
import br.com.brasili.api.emprestimo.repository.ObjetoArteRepository;

@Service
public class ObjetoArteService {

	private final ObjetoArteRepository repository;

	public ObjetoArteService(ObjetoArteRepository repository) {
		this.repository = repository;
	}

	public List<ObjetoArte> findAll(UUID idEmprestimo) {
		return this.repository.findAll();
	}

	public Optional<ObjetoArte> findById(UUID idEmprestimo, UUID idObjetoArte) {
		return this.repository.findById(idObjetoArte);
	}

	@Transactional
	public void deleteById(UUID idEmprestimo, UUID idObjetoArte) {
		this.repository.deleteById(idObjetoArte);
	}
}
