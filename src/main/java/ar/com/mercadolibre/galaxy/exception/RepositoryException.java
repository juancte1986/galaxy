package ar.com.mercadolibre.galaxy.exception;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public RepositoryException() {
		super();
	}

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(Throwable ex) {
		super(ex);
	}

}
