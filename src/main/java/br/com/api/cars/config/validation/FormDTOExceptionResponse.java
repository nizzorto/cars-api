package br.com.api.cars.config.validation;

public class FormDTOExceptionResponse {
	
	private String campo;
	private String erro;
	
	public FormDTOExceptionResponse(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	
}
