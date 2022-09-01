package br.com.brasili.api.artista.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.artista.model.Estilo;
import br.com.brasili.api.artista.repository.EstiloRepository;

@Service
public class EstiloService {

	private final EstiloRepository repository;

	public EstiloService(EstiloRepository repository) {
		this.repository = repository;
	}

	public Optional<Estilo> findByNome(String nome) {
		return this.repository.findByNome(nome);
	}

	@Transactional
	public void save(Estilo estilo) {
		this.repository.save(estilo);
	}
}
