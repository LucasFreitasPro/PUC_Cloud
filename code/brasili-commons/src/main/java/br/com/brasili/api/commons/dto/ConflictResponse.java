package br.com.brasili.api.commons.dto;

public class ConflictResponse {

	private String message;

	public ConflictResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
