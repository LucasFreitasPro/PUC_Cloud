package br.com.brasili.api.objetoarte.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.objetoarte.dto.ObjetoArteRegisteringRequest;
import br.com.brasili.api.objetoarte.dto.ObjetoArteUpdatingRequest;
import br.com.brasili.api.objetoarte.model.ObjetoArte;
import br.com.brasili.api.objetoarte.repository.ObjetoArteRepository;

@Service
public class ObjetoArteService {

	private final ObjetoArteRepository repository;

	public ObjetoArteService(ObjetoArteRepository repository) {
		this.repository = repository;
	}

	public List<ObjetoArte> findAll(UUID idProprietario, UUID idColecao) {
		return this.repository.findAll(idProprietario, idColecao);
	}

	public List<ObjetoArte> findAll() {
		return this.repository.findAll();
	}

	public Optional<ObjetoArte> findById(UUID id) {
		return this.repository.findById(id);
	}

	public Optional<ObjetoArte> findByTitulo(UUID idProprietario, UUID idColecao, String titulo) {
		return this.repository.findByTitulo(idProprietario, idColecao, titulo);
	}

	public ObjetoArte create(ObjetoArteRegisteringRequest objetoArteRequest) {
		ObjetoArte objetoArte = new ObjetoArte();
		objetoArte.setAnoCriacao(objetoArteRequest.getAnoCriacao());
		objetoArte.setCustoAquisicao(objetoArteRequest.getCustoAquisicao());
		objetoArte.setDataAquisicao(objetoArteRequest.getDataAquisicao());
		objetoArte.setDescricao(objetoArteRequest.getDescricao());
		objetoArte.setExposto(objetoArteRequest.getExposto());
		objetoArte.setIdentificador("GERAR");
		objetoArte.setPaisOrigem(objetoArteRequest.getPaisOrigem());
		objetoArte.setTitulo(objetoArteRequest.getTitulo());
		objetoArte.setIdColecao(objetoArteRequest.getIdColecao());

		objetoArte.getObraArte();
		// TODO 4 validacoes

		objetoArte.getEpoca();

		return save(objetoArte);
	}

	public ObjetoArte update(ObjetoArteUpdatingRequest objetoArteUpdatingRequest) {
		ObjetoArte objetoArte = new ObjetoArte();
		return save(objetoArte);
	}

	@Transactional
	public ObjetoArte save(ObjetoArte objetoArte) {
		return this.repository.save(objetoArte);
	}

	@Transactional
	public void deleteById(UUID idObjetoArte) {
		this.repository.deleteById(idObjetoArte);
	}
}
