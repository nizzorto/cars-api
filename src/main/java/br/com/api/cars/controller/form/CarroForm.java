package br.com.api.cars.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import br.com.api.cars.model.Carro;



public class CarroForm {
	
	@NotBlank
	private String chassi;
	@NotBlank
	private String nome;
	@NotBlank
	private String marca;
	@NotBlank
	private String cor;
	@NotNull @Range(min=0, max=10000000)
	private BigDecimal valor;
	@NotNull @Range(min=1890, max=2100)
	private int ano;
	
	public String getChassi() {
		return chassi;
	}
	
	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public String getCor() {
		return cor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getAno() {
		return ano;
	}

	public Carro toCarro() {
		 return new Carro(this.chassi.toUpperCase(), this.nome.toLowerCase(),
				 this.marca.toLowerCase(), this.cor.toLowerCase(),
				 this.valor, this.ano);
		 
	}
}
