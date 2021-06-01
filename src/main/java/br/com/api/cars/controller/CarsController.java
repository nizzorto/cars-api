package br.com.api.cars.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.cars.controller.form.CarroForm;
import br.com.api.cars.dto.CarroDTO;
import br.com.api.cars.service.CarroService;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	@Autowired
	private CarroService carroService;
	
	@RequestMapping
	public ResponseEntity<?> cars() {
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(params = "marca")
	public List<CarroDTO> marca(@RequestParam String marca) {
		return carroService.buscarPorMarca(marca);
	}
	
	@RequestMapping(params = "nome")
	public List<CarroDTO> nome(@RequestParam String nome) {
		return carroService.buscarPorNome(nome);
	}
	
	@RequestMapping(params = "cor")
	public List<CarroDTO> cor(@RequestParam String cor) {
		return carroService.buscarPorCor(cor);
	}
	
	@RequestMapping(params = "maiscaro")
	public List<CarroDTO> maiscaro(@RequestParam String maiscaro) {
		return carroService.buscarMaisCaro();
	}
	
	@RequestMapping(params = "maisbarato")
	public List<CarroDTO> maisbarato(@RequestParam String maisbarato) {
		return carroService.buscarMaisBarato();
	}
	
	@RequestMapping(params = "ordenarnome")
	public List<CarroDTO> ordenarnome(@RequestParam String ordenarnome) {
		return carroService.ordenarNome();
		
	}
	
	@RequestMapping(params = "ordenarvalor")
	public List<CarroDTO> ordenarvalor(@RequestParam String ordenarvalor) {
		return carroService.ordenarValor();
	}
	
	@RequestMapping(params = "ordenarano")
	public List<CarroDTO> ordenarAno(@RequestParam String ordenarano) {
		return carroService.ordenarAno();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CarroDTO> postCar(@RequestBody @Valid CarroForm carroForm, UriComponentsBuilder uriBuilder) {
		return carroService.inserir(carroForm, uriBuilder);
	}
}
