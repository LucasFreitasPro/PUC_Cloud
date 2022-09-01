package br.com.brasili.api.artista.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.artista.dto.ArtistaRegisteringRequest;
import br.com.brasili.api.artista.dto.ArtistaUpdatingRequest;
import br.com.brasili.api.artista.model.Artista;
import br.com.brasili.api.artista.model.Epoca;
import br.com.brasili.api.artista.model.Estilo;
import br.com.brasili.api.artista.repository.ArtistaRepository;

@Service
public class ArtistaService {

	private final EpocaService epocaService;
	private final EstiloService estiloService;
	private final ArtistaRepository repository;

	public ArtistaService(EpocaService epocaService, EstiloService estiloService, ArtistaRepository repository) {
		this.epocaService = epocaService;
		this.estiloService = estiloService;
		this.repository = repository;
	}

	public Optional<Artista> findByNome(String nome) {
		return this.repository.findByNome(nome);
	}

	public List<Artista> findAll() {
		return this.repository.findAll();
	}

	public Optional<Artista> findById(UUID id) {
		return this.repository.findById(id);
	}

	public Artista create(ArtistaRegisteringRequest artistaRequest) {
		Artista artista = new Artista();
		artista.setDescricao(artistaRequest.getDescricao());
		artista.setFalecimento(artistaRequest.getFalecimento());
		artista.setNascimento(artistaRequest.getNascimento());
		artista.setNome(artistaRequest.getNome());
		artista.setPaisOrigem(artistaRequest.getPaisOrigem());

		Optional<Estilo> optEstilo = this.estiloService.findByNome(artistaRequest.getEstiloDominante().toUpperCase());
		if (optEstilo.isPresent()) {
			artista.setEstilo(optEstilo.get());
		} else {
			Estilo estilo = new Estilo();
			estilo.setNome(artistaRequest.getEstiloDominante().toUpperCase());
			this.estiloService.save(estilo);
			artista.setEstilo(estilo);
		}

		Optional<Epoca> optEpoca = this.epocaService.findByDescricao(artistaRequest.getEpocaDescricao().toUpperCase());
		if (optEpoca.isPresent()) {
			artista.setEpoca(optEpoca.get());
		} else {
			Epoca epoca = new Epoca();
			epoca.setDescricao(artistaRequest.getEpocaDescricao().toUpperCase());
			this.epocaService.save(epoca);
			artista.setEpoca(epoca);
		}
		return save(artista);
	}

	public Artista update(ArtistaUpdatingRequest artistaUpdatingRequest) {
		return null;
	}

	@Transactional
	public Artista save(Artista artista) {
		return this.repository.save(artista);
	}

	@Transactional
	public void deleteById(UUID idArtista) {
		this.repository.deleteById(idArtista);
	}
}
