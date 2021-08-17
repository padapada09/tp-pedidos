package nube.tp.pedidos;

import org.springframework.http.HttpStatus;

public class ControllerException extends Exception {
	public String message;
	public HttpStatus status;

	public ControllerException(String message) {
		this.message = message;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ControllerException(String message, HttpStatus errorCode) {
		this.message = message;
		this.status = errorCode;
	}

	public ControllerException() {
		this.message = "Something went wrong.";
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
}