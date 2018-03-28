package Exceptions;

public class ASException extends Exception{
	public ASException() {
		super();
	}

	public ASException(String message) {
		super(message);
	}

	public ASException(String message, Throwable cause) {
		super(message, cause);
	}
}
