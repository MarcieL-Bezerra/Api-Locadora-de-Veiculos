package br.com.locadora.exception;

public class LocadoraException extends RuntimeException {

	private static final long serialVersionUID = 2758782160805163385L;

	private String message;

	public LocadoraException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
