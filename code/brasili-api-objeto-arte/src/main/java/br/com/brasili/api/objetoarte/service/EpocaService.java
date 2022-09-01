package br.com.brasili.api.objetoarte.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.brasili.api.objetoarte.model.Epoca;
import br.com.brasili.api.objetoarte.repository.EpocaRepository;

@Service
public class EpocaService {

	private final EpocaRepository repository;

	public EpocaService(EpocaRepository repository) {
		this.repository = repository;
	}

	public Optional<Epoca> findByDescricao(String descricao) {
		return this.repository.findByDescricao(descricao);
	}
}
