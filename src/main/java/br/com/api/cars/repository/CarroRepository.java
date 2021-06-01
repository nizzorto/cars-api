package br.com.api.cars.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.cars.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{
	
	List<Carro> findByMarca(String marca);

	List<Carro> findByNome(String nome);
	
	List<Carro> findByCor(String cor);
	
	@Query("SELECT c FROM Carro c WHERE valor = (SELECT MAX(valor) FROM Carro)")
	List<Carro> maisCaro();
	
	@Query("SELECT c FROM Carro c WHERE valor = (SELECT MIN(valor) FROM Carro)")
	List<Carro> maisBarato();
	
	
}
