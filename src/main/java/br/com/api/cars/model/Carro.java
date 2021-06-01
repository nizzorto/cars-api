package br.com.api.cars.model;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.api.cars.dto.CarroDTO;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "chassi")})
public class Carro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String chassi;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String marca;
	@Column(nullable = false)
	private String cor;
	private BigDecimal valor;
	private int ano;
	
	public Carro() {
		
	}
	
	public Carro(String chassi, String nome, String marca, String cor, BigDecimal valor,
			int ano) {
		
		this.chassi = chassi;
		this.nome = nome;
		this.marca = marca;
		this.cor = cor;
		this.valor = valor;
		this.ano = ano;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getChassi() {
		return chassi;
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
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getMarca() {
		return marca;
	}
	
	public static List<CarroDTO> toListCarroDTO(List<Carro> carros) {	
		List<CarroDTO> carrosDTO = carros.stream().map(CarroDTO::new).collect(Collectors.toList());
		return carrosDTO;
	}
}
