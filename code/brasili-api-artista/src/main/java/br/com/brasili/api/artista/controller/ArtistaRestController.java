package br.com.brasili.api.artista.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasili.api.artista.dto.ArtistaRegisteringRequest;
import br.com.brasili.api.artista.dto.ArtistaUpdatingRequest;
import br.com.brasili.api.artista.model.Artista;
import br.com.brasili.api.artista.service.ArtistaService;
import br.com.brasili.api.commons.dto.BadRequestResponse;
import br.com.brasili.api.commons.dto.ConflictResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaRestController {

	private final ArtistaService service;

	public ArtistaRestController(ArtistaService service) {
		this.service = service;
	}

	@Operation(summary = "Recupera todos os artistas cadastrados")
	@ApiResponse(responseCode = "200", description = "Lista de artistas cadastrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Artista.class)))
	@ApiResponse(responseCode = "404", description = "Nenhum artista encontrado", content = @Content)
	@GetMapping
	public ResponseEntity<List<Artista>> getAll() {
		List<Artista> all = this.service.findAll();
		if (all != null && !all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(all);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Recupera artista pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Artista encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Artista.class)))
	@ApiResponse(responseCode = "404", description = "Artista não encontrado", content = @Content)
	@GetMapping("/{idArtista}")
	public ResponseEntity<Artista> getOne(@PathVariable("idArtista") UUID idArtista) {
		Optional<Artista> optional = this.service.findById(idArtista);
		if (optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Cadastra um novo artista")
	@ApiResponse(responseCode = "201", description = "Artista cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Artista.class)))
	@ApiResponse(responseCode = "409", description = "O artista já se encontra cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ArtistaRegisteringRequest artistaRequest, BindingResult result) {
		if (result.hasErrors()) {
			List<BadRequestResponse> fields = result.getFieldErrors().stream().map(e -> new BadRequestResponse(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fields);
		}

		if (this.service.findByNome(artistaRequest.getNome()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ConflictResponse("O artista " + artistaRequest.getNome() + " já está cadastrado"));
		}

		Artista artista = this.service.create(artistaRequest);

		return ResponseEntity.ok(artista);
	}

	@Operation(summary = "Atualiza um artista pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Artista atualizado com sucesso", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Artista.class)) })
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content)
	@ApiResponse(responseCode = "409", description = "O nome informado já se encontra cadastrado em outro Artista", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@PutMapping("/{idArtista}")
	public ResponseEntity<Object> update(@PathVariable("idArtista") UUID idArtista, @RequestBody ArtistaUpdatingRequest artistaUpdatingRequest) {
		Artista artista = this.service.update(artistaUpdatingRequest);
		return ResponseEntity.ok(artista);
	}

	@Operation(summary = "Exclui um artista pelo seu identificador")
	@ApiResponse(responseCode = "204", description = "Artista excluído com sucesso", content = @Content)
	@DeleteMapping("/{idArtista}")
	public ResponseEntity<Object> delete(@PathVariable("idArtista") UUID idArtista) {
		this.service.deleteById(idArtista);
		return ResponseEntity.noContent().build();
	}
}
