package br.com.brasili.api.museu.controller;

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

import br.com.brasili.api.commons.dto.BadRequestResponse;
import br.com.brasili.api.commons.dto.ConflictResponse;
import br.com.brasili.api.museu.dto.MuseuRegisteringRequest;
import br.com.brasili.api.museu.dto.MuseuUpdatingRequest;
import br.com.brasili.api.museu.model.Museu;
import br.com.brasili.api.museu.service.MuseuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/museus")
public class MuseuRestController {

	private final MuseuService service;

	public MuseuRestController(MuseuService service) {
		this.service = service;
	}

	@Operation(summary = "Recupera todos os museus cadastrados")
	@ApiResponse(responseCode = "200", description = "Lista de museus cadastrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Museu.class)))
	@ApiResponse(responseCode = "404", description = "Nenhum museu encontrado", content = @Content)
	@GetMapping
	public ResponseEntity<List<Museu>> getAll() {
		List<Museu> all = this.service.findAll();
		if (all != null && !all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(all);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Recupera um museu pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Museu encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Museu.class)))
	@ApiResponse(responseCode = "404", description = "Museu não encontrado", content = @Content)
	@GetMapping("/{idMuseu}")
	public ResponseEntity<Museu> getOne(@PathVariable(name = "idMuseu", required = true) UUID idMuseu) {
		Optional<Museu> optional = this.service.findById(idMuseu);
		if (optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Cadastra um novo museu")
	@ApiResponse(responseCode = "201", description = "Museu cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Museu.class)))
	@ApiResponse(responseCode = "409", description = "O museu já se encontra cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody MuseuRegisteringRequest museuRequest, BindingResult result) {
		if (result.hasErrors()) {
			List<BadRequestResponse> fields = result.getFieldErrors().stream().map(e -> new BadRequestResponse(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fields);
		}

		if (this.service.findByNomeAndCidade(museuRequest.getNome(), museuRequest.getEndereco().getCidade()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("O museu " + museuRequest.getNome() + " já está cadastrado na cidade " + museuRequest.getEndereco().getCidade());
		}

		Museu museu = this.service.create(museuRequest);

		return ResponseEntity.ok(museu);
	}

	@Operation(summary = "Atualiza um museu pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Museu atualizado com sucesso", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Museu.class)) })
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content)
	@ApiResponse(responseCode = "409", description = "O nome informado já se encontra cadastrado em outro Museu na mesma cidade", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@PutMapping("/{idMuseu}")
	public ResponseEntity<Object> update(@PathVariable("idMuseu") UUID idMuseu, @RequestBody MuseuUpdatingRequest museuUpdatingRequest) {
		if (this.service.findByNomeAndCidade(museuUpdatingRequest.getNome(), museuUpdatingRequest.getEndereco().getCidade()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("O museu " + museuUpdatingRequest.getNome() + " já está cadastrado na cidade " + museuUpdatingRequest.getEndereco().getCidade());
		}

		Museu museu = this.service.update(museuUpdatingRequest);

		return ResponseEntity.ok(museu);
	}

	@Operation(summary = "Exclui um museu pelo seu identificador")
	@ApiResponse(responseCode = "204", description = "Museu excluído com sucesso", content = @Content)
	@DeleteMapping("/{idMuseu}")
	public ResponseEntity<Object> delete(@PathVariable("idMuseu") UUID idMuseu) {
		this.service.deleteById(idMuseu);

		return ResponseEntity.noContent().build();
	}
}
