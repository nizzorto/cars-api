package br.com.api.cars.config.validation;

public class DTOException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8627313157318153433L;
	
	private String message;
	
	public DTOException() {
		
	}
	
	public DTOException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
