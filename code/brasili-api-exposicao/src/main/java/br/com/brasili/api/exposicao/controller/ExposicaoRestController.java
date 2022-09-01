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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasili.api.commons.dto.BadRequestResponse;
import br.com.brasili.api.commons.dto.ConflictResponse;
import br.com.brasili.api.exposicao.dto.ExposicaoRegisteringRequest;
import br.com.brasili.api.exposicao.dto.ExposicaoUpdatingRequest;
import br.com.brasili.api.exposicao.model.Exposicao;
import br.com.brasili.api.exposicao.service.ExposicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/exposicoes")
public class ExposicaoRestController {

	private final ExposicaoService service;

	public ExposicaoRestController(ExposicaoService service) {
		this.service = service;
	}

	@Operation(summary = "Recupera todas as exposições cadastradas")
	@ApiResponse(responseCode = "200", description = "Lista de exposições cadastradas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exposicao.class)))
	@ApiResponse(responseCode = "404", description = "Nenhuma exposição encontrada", content = @Content)
	@GetMapping
	public ResponseEntity<List<Exposicao>> getAll() {
		List<Exposicao> all = this.service.findAll();
		if (all != null && !all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(all);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Recupera uma exposição pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Exposição encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exposicao.class)))
	@ApiResponse(responseCode = "404", description = "Exposição não encontrada", content = @Content)
	@GetMapping("/{idExposicao}")
	public ResponseEntity<Exposicao> getOne(@PathVariable("idExposicao") UUID idExposicao) {
		Optional<Exposicao> optional = this.service.findById(idExposicao);
		if (optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Cadastra uma nova exposição")
	@ApiResponse(responseCode = "201", description = "Exposição cadastrada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exposicao.class)))
	@ApiResponse(responseCode = "409", description = "A exposição já se encontra cadastrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestResponse.class)))
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ExposicaoRegisteringRequest exposicaoRequest, BindingResult result) {
		if (result.hasErrors()) {
			List<BadRequestResponse> fields = result.getFieldErrors().stream().map(e -> new BadRequestResponse(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fields);
		}

		if (this.service.findByNomeAndDataInicio(exposicaoRequest.getNome(), exposicaoRequest.getDataInicio()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("A exposição " + exposicaoRequest.getNome() + " já está cadastrada na data informada");
		}

		if (exposicaoRequest.getDataInicio().isAfter(exposicaoRequest.getDataEncerramento())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("O início da exposição não pode ser depois do encerramento");
		}

		Exposicao exposicao = this.service.create(exposicaoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(exposicao);
	}

	@Operation(summary = "Atualiza uma exposição pelo seu identificador")
	@ApiResponse(responseCode = "200", description = "Exposição atualizada com sucesso", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Exposicao.class)) })
	@ApiResponse(responseCode = "400", description = "Payload inválido", content = @Content)
	@ApiResponse(responseCode = "409", description = "O nome informado já se encontra cadastrado em outra Exposição", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflictResponse.class)))
	@PutMapping("/{idExposicao}")
	public ResponseEntity<Object> update(@PathVariable("idExposicao") UUID idExposicao, @RequestBody ExposicaoUpdatingRequest exposicaoRequest) {
		Exposicao exposicao = this.service.update(exposicaoRequest);
		return ResponseEntity.ok(exposicao);
	}

	@Operation(summary = "Exclui uma exposição pelo seu identificador")
	@ApiResponse(responseCode = "204", description = "Exposição excluída com sucesso", content = @Content)
	@DeleteMapping("/{idExposicao}")
	public ResponseEntity<Object> delete(@PathVariable("idExposicao") UUID idExposicao) {
		this.service.deleteById(idExposicao);
		return ResponseEntity.noContent().build();
	}
}
