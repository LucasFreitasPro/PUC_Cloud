package br.com.brasili.api.museu.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.museu.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

}
