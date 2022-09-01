package br.com.brasili.api.museu.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasili.api.museu.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {

}
