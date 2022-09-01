package br.com.brasili.api.exposicao.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.exposicao.dto.ExposicaoRegisteringRequest;
import br.com.brasili.api.exposicao.dto.ExposicaoUpdatingRequest;
import br.com.brasili.api.exposicao.model.Exposicao;
import br.com.brasili.api.exposicao.repository.ExposicaoRepository;

@Service
public class ExposicaoService {

	private final ExposicaoRepository repository;

	public ExposicaoService(ExposicaoRepository repository) {
		this.repository = repository;
	}

	public List<Exposicao> findAll() {
		return this.repository.findAll();
	}

	public Optional<Exposicao> findById(UUID id) {
		return this.repository.findById(id);
	}

	public Optional<Exposicao> findByNomeAndDataInicio(String nome, LocalDateTime dataInicio) {
		return this.repository.findByNomeAndDataInicio(nome, dataInicio);
	}

	public Exposicao create(ExposicaoRegisteringRequest exposicaoRequest) {
		Exposicao exposicao = new Exposicao();
		exposicao.setDataEncerramento(exposicaoRequest.getDataEncerramento());
		exposicao.setDataInicio(exposicaoRequest.getDataInicio());
		exposicao.setNome(exposicaoRequest.getNome());
		return save(exposicao);
	}

	public Exposicao update(ExposicaoUpdatingRequest exposicaoUpdatingRequest) {
		Exposicao exposicao = new Exposicao();
		return save(exposicao);
	}

	@Transactional
	public Exposicao save(Exposicao exposicao) {
		return this.repository.save(exposicao);
	}

	@Transactional
	public void deleteById(UUID idExposicao) {
		this.repository.deleteById(idExposicao);
	}
}
