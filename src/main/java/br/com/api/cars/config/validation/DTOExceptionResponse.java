package br.com.api.cars.config.validation;

public class DTOExceptionResponse{
	private String erro;

	public String getErro() {
		return erro;
	}

	public DTOExceptionResponse(String erro) {
		this.erro = erro;
	}
	
}
