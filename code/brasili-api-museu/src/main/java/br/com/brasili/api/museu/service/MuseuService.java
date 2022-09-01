package br.com.brasili.api.museu.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasili.api.museu.dto.MuseuRegisteringRequest;
import br.com.brasili.api.museu.dto.MuseuUpdatingRequest;
import br.com.brasili.api.museu.model.Contato;
import br.com.brasili.api.museu.model.Endereco;
import br.com.brasili.api.museu.model.Museu;
import br.com.brasili.api.museu.repository.MuseuRepository;

@Service
public class MuseuService {

	private final MuseuRepository repository;

	public MuseuService(MuseuRepository repository) {
		this.repository = repository;
	}

	public List<Museu> findAll() {
		return this.repository.findAll();
	}

	public Optional<Museu> findByNomeAndCidade(String nome, String cidade) {
		return this.repository.findByNomeAndCidade(nome, cidade);
	}

	public Optional<Museu> findById(UUID idMuseu) {
		return this.repository.findById(idMuseu);
	}

	public Museu create(MuseuRegisteringRequest museuRequest) {
		Museu museu = new Museu();
		museu.setNome(museuRequest.getNome());
		museu.setSigla(museuRequest.getSigla());

		Endereco endereco = new Endereco();
		endereco.setBairro(museuRequest.getEndereco().getBairro());
		endereco.setCep(museuRequest.getEndereco().getCep());
		endereco.setCidade(museuRequest.getEndereco().getCidade());
		endereco.setComplemento(museuRequest.getEndereco().getComplemento());
		endereco.setNumero(museuRequest.getEndereco().getNumero());
		endereco.setEstado(museuRequest.getEndereco().getEstado());
		endereco.setLogradouro(museuRequest.getEndereco().getLogradouro());
		endereco.setTipoLogradouro(museuRequest.getEndereco().getTipoLogradouro());
		museu.setEndereco(endereco);

		Contato contato = new Contato();
		contato.setDescricao(museuRequest.getContato().getDescricao());
		contato.setNome(museuRequest.getContato().getNome());
		contato.setTelefone(museuRequest.getContato().getTelefone());
		contato.setEmail(museuRequest.getContato().getEmail());
		museu.setContato(contato);

		return save(museu);
	}

	public Museu update(MuseuUpdatingRequest museuUpdatingRequest) {
		return null;
	}

	@Transactional
	public Museu save(Museu museu) {
		return this.repository.save(museu);
	}

	@Transactional
	public void deleteById(UUID idMuseu) {
		this.repository.deleteById(idMuseu);
	}
}
