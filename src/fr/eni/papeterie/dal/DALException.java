package fr.eni.papeterie.dal;

public class DALException extends Exception {
	
	public DALException() {
	}

	// Chaînage des Exceptions
	public DALException(String message, Throwable cause) {
		super(message,cause);
	}
}
