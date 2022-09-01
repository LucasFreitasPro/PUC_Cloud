package br.com.brasili.api.exposicao.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.exposicao.dto.ObjetoArteRegisteringRequest;
import br.com.brasili.api.exposicao.model.ObjetoArte;
import br.com.brasili.api.exposicao.model.ObjetoArteId;
import br.com.brasili.api.exposicao.repository.ObjetoArteRepository;

@Service
public class ObjetoArteService {

	private final ObjetoArteRepository repository;

	public ObjetoArteService(ObjetoArteRepository repository) {
		this.repository = repository;
	}

	public Optional<ObjetoArte> findById(UUID idObjetoArte) {
		return this.repository.findById(idObjetoArte);
	}

	public List<ObjetoArte> findByIdExposicao(UUID idExposicao) {
		return this.repository.findByIdExposicao(idExposicao);
	}

	public ObjetoArte create(@Valid ObjetoArteRegisteringRequest objetoArteRegisteringRequest, UUID idExposicao) {
		ObjetoArte objetoArte = new ObjetoArte();
		objetoArte.setObjetoArteId(new ObjetoArteId(objetoArteRegisteringRequest.getIdObjetoArte(), idExposicao));
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
