package br.com.brasili.api.colecao.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.colecao.dto.ColecaoRegisteringRequest;
import br.com.brasili.api.colecao.dto.ColecaoUpdatingRequest;
import br.com.brasili.api.colecao.model.Colecao;
import br.com.brasili.api.colecao.repository.ColecaoRepository;

@Service
public class ColecaoService {

	private final ColecaoRepository repository;

	public ColecaoService(ColecaoRepository repository) {
		this.repository = repository;
	}

	public List<Colecao> findAll() {
		return this.repository.findAll();
	}

	public Optional<Colecao> findById(UUID id) {
		return this.repository.findById(id);
	}

	public List<Colecao> findByIdMuseu(UUID idMuseu) {
		return this.repository.findByIdMuseu(idMuseu);
	}

	public Optional<Colecao> findByNomeAndIdMuseu(String nome, UUID idMuseu) {
		return this.repository.findByNomeAndIdMuseu(nome, idMuseu);
	}

	public Colecao create(ColecaoRegisteringRequest colecaoRequest) {
		Colecao colecao = new Colecao();
		colecao.setNome(colecaoRequest.getNome());
		colecao.setIdMuseu(colecaoRequest.getIdMuseu());
		return save(colecao);
	}

	public Colecao update(ColecaoUpdatingRequest colecaoUpdatingRequest) {
		return null;
	}

	@Transactional
	public Colecao save(Colecao colecao) {
		return this.repository.save(colecao);
	}

	@Transactional
	public void deleteById(UUID idColecao) {
		this.repository.deleteById(idColecao);
	}
}
