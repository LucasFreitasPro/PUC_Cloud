package br.com.brasili.api.artista.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.artista.model.Epoca;
import br.com.brasili.api.artista.repository.EpocaRepository;

@Service
public class EpocaService {

	private final EpocaRepository repository;

	public EpocaService(EpocaRepository repository) {
		this.repository = repository;
	}

	public Optional<Epoca> findByDescricao(String descricao) {
		return this.repository.findByDescricao(descricao);
	}

	@Transactional
	public void save(Epoca epoca) {
		this.repository.save(epoca);
	}
}
