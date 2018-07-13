package ar.com.mercadolibre.galaxy.exception;

public class FactoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public FactoryException() {
		super();
	}

	public FactoryException(String message) {
		super(message);
	}

	public FactoryException(Throwable ex) {
		super(ex);
	}

}
