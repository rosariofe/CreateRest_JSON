package br.usjt.rest_json.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usjt.rest_json.model.Cidade;

public interface CidadeRepository extends JpaRepository <Cidade, Long>{

	Cidade findByLatitudeAndLongitude(Double latitude, Double longitude);
	
	List<Cidade> findByNomeStartingWith(String s);
}
