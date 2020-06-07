package br.usjt.rest_json.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.rest_json.model.Cidade;
import br.usjt.rest_json.repository.CidadeRepository;


@RestController
@RequestMapping ("/cidade")
public class CidadeResource {

	@Autowired
	private CidadeRepository cidadeRepo;
	
	
	//Listar todas as Cidades
	@GetMapping("/lista")
	public List<Cidade> todasCidades(){
		
		return cidadeRepo.findAll();
	}
	
	//Salvar via Post a Cidade
	@PostMapping("/salvar")
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade, HttpServletResponse response) {
		Cidade c =cidadeRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).body(c);
		
	}
	
	@GetMapping ("/{id}")
	public Cidade buscarPeloId (@PathVariable Long id) {
	return cidadeRepo.getOne(id);
	}
	
	
	// Buscar Cidade Pelo Latitude e Longitude 
	@GetMapping("/BuscaCidade/latitude={lat}&longitude={lon}")
	public Cidade findCidadeLatAndLon(@PathVariable Double lat, @PathVariable Double lon){
		
		return cidadeRepo.findByLatitudeAndLongitude(lat, lon);
	}
	
	// Busca Cidade pela inicial 
	@GetMapping("/BuscaCidadeInicial/{s}")
	public List<Cidade> buscaCidadePelaInicial(@PathVariable String s){
		
		return cidadeRepo.findByNomeStartingWith(s);
	}
	
}
