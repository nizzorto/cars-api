package br.com.api.cars.service;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.cars.config.validation.DTOException;
import br.com.api.cars.controller.form.CarroForm;
import br.com.api.cars.dto.CarroDTO;
import br.com.api.cars.model.Carro;
import br.com.api.cars.repository.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepos;
	
	public ResponseEntity<CarroDTO> inserir(@Valid CarroForm carroForm, UriComponentsBuilder uriBuilder) {
		Carro carro = carroForm.toCarro();
		carroRepos.save(carro);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(carro.getId()).toUri();
		return ResponseEntity.created(uri).body(new CarroDTO(carro));
	}
	
	public List<CarroDTO> buscarTudo() {
		List<Carro> carros = carroRepos.findAll();
		if(carros.isEmpty()) {
			throw new DTOException("Falha na busca de todos os carros");
		}
		return Carro.toListCarroDTO(carros);
	}

	public List<CarroDTO> buscarPorMarca(String marca) {
		List<Carro> carros = carroRepos.findByMarca(marca.toLowerCase());
		if(carros.isEmpty()) {
			throw new DTOException("Não encontrados carros da marca " + marca);
		}
		return Carro.toListCarroDTO(carros);
	}

	public List<CarroDTO> buscarPorNome(String nome) {
		List<Carro> carros = carroRepos.findByNome(nome.toLowerCase());
		if(carros.isEmpty()) {
			throw new DTOException("Carro " + nome + " não encontrado");
		}
		return Carro.toListCarroDTO(carros);
	}
	
	public List<CarroDTO> buscarPorCor(String cor) {
		List<Carro> carros = carroRepos.findByCor(cor.toLowerCase());
		if(carros.isEmpty()) {
			throw new DTOException("Não encontrados carros da cor " + cor);
		}
		return Carro.toListCarroDTO(carros);
	}
	
	public List<CarroDTO> buscarMaisCaro() {
		List<Carro> carros = carroRepos.maisCaro();
		if(carros.isEmpty()) {
			throw new DTOException("Falha na busca do carro mais caro");
		}
		return Carro.toListCarroDTO(carroRepos.maisCaro());
	}
	
	public List<CarroDTO> buscarMaisBarato() {
		return Carro.toListCarroDTO(carroRepos.maisBarato());
	}
	
	public List<CarroDTO> ordenarNome() {
		List<CarroDTO> carroDTO = this.buscarTudo();
		carroDTO.sort(Comparator.comparing(CarroDTO::getNome));
		return carroDTO;
	}
	
	public List<CarroDTO> ordenarValor() {
		List<CarroDTO> carroDTO = this.buscarTudo();
		carroDTO.sort(Comparator.comparing(CarroDTO::getValor));
		return carroDTO;
	}
	
	public List<CarroDTO> ordenarAno() {
		List<CarroDTO> carroDTO = this.buscarTudo();
		carroDTO.sort(Comparator.comparing(CarroDTO::getAno));
		return carroDTO;
	}
}
