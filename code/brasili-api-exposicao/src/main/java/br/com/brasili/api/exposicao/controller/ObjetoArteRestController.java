package br.com.brasili.api.exposicao.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasili.api.commons.dto.BadRequestResponse;
import br.com.brasili.api.commons.dto.ConflictResponse;
import br.com.brasili.api.exposicao.dto.ObjetoArteRegisteringRequest;
import br.com.brasili.api.exposicao.model.ObjetoArte;
import br.com.brasili.api.exposicao.service.ObjetoArteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/exposicoes/{idExposicao}/objetos-arte")
public class ObjetoArteRestController {

	private final ObjetoArteService service;

	public ObjetoArteRestController(ObjetoArteService service) {
		this.service = service;
	}

	@Operation(summary = "Recupera todos os objetos de arte cadastrados em uma exposição")
	@ApiResponse(responseCode = "200", description = "Lista de objetos de arte cadastrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjetoArte.class)))
	@ApiResponse(responseCode = "404", description = "Nenhum objeto de arte encontrado", content = @Content)
	@GetMapping
	public ResponseEntity<List<ObjetoArte>> getAll(@PathVariable("idExposicao") UUID idExposicao) {
		List<ObjetoArte> all = this.service.findByIdExposicao(idExposicao);
		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@Operation(summary = "Recupera um objeto de arte cadastrado em uma exposição")
	@ApiResponse(responseCode = "200", description = "Objeto de arte encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjetoArte.class)))
	@ApiResponse(responseCode = "404", description = "Objeto de arte não encontrado", content = @Content)
	@GetMapping("/{idObjetoArte}")
	public ResponseEntity<ObjetoArte> getOne(@PathVariable("idExposicao") UUID idExposicao, @PathVariable("idObjetoArte") UUID idObjetoArte) {
		Optional<ObjetoArte> optObjetoArte = this.service.findById(idObjetoArte);
		if (!optObjetoArte.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(optObjetoArte.get());
		}
	}

	@Operation(summary = "Cadastra um novo objeto de arte em uma exposição")
	@ApiResponse(responseCode = "201", description = "Objeto de arte cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjetoArte.class)))
	@ApiResponse(responseCode = "409", description = "O objeto de arte já se encontra cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
	@PostMapping
	public ResponseEntity<Object> create(@PathVariable("idExposicao") UUID idExposicao, @Valid @RequestBody ObjetoArteRegisteringRequest objetoArteRegisteringRequest, BindingResult result) {
		if (result.hasErrors()) {
			List<BadRequestResponse> fields = result.getFieldErrors().stream().map(e -> new BadRequestResponse(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fields);
		}

		Optional<ObjetoArte> optObjetoArte = this.service.findById(objetoArteRegisteringRequest.getIdObjetoArte());
		if (optObjetoArte.isPresent() && optObjetoArte.get().getObjetoArteId().getIdExposicao().equals(idExposicao)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Objeto arte já está cadastrado para a coleção");
		}

		ObjetoArte objetoArte = this.service.create(objetoArteRegisteringRequest, idExposicao);

		return ResponseEntity.status(HttpStatus.CREATED).body(objetoArte);
	}

	@Operation(summary = "Exclui um objeto de arte que está em uma exposição")
	@ApiResponse(responseCode = "204", description = "Objeto de arte excluído com sucesso", content = @Content)
	@DeleteMapping("/{idObjetoArte}")
	public ResponseEntity<Object> delete(@PathVariable("idExposicao") UUID idExposicao, @PathVariable("idObjetoArte") UUID idObjetoArte) {
		this.service.deleteById(idObjetoArte);
		return ResponseEntity.noContent().build();
	}
}
