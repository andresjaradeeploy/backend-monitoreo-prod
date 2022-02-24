package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Palabras;
import com.monitor.bankendmonitoreoLinks.service.IPalabraService;



@RestController
@RequestMapping("/palabras")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200","https://localhost:4200"})
public class PalabraRestController {
	
	@Autowired
	private IPalabraService iPalabraService;
	
	@GetMapping("/all/{anuncio}")
	public List<Palabras> listarTagsByAnuncio(@PathVariable String anuncio) {
		return iPalabraService.findTagsbyAnuncio(anuncio);
	}
	
	@PostMapping("/guardarPalabra")
	public Palabras guardar(@RequestBody Palabras palabras){
	return iPalabraService.save(palabras);
	}
	
	@GetMapping("/listar")
	public List<Palabras> listarPalabras() {
		return iPalabraService.findAll();
	}

}
