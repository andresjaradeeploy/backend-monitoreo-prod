package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.service.IAnuncioService;

@RestController
@RequestMapping("/anuncio")
@CrossOrigin(origins = {"http://localhost:4200","*"})
public class AnuncioRestController {
	@Autowired
	private IAnuncioService anuncioService;
	
	
	@GetMapping("/")
	public List<Anuncio> index(){
		return (List<Anuncio>) anuncioService.findAll(); 
		
		
	}
	
	@GetMapping("/all/{idCuenta}")
	public List<Anuncio> listarByCuentaFB(@PathVariable String idCuenta)
	{
		return anuncioService.findAnunciobyCuentaFB(idCuenta);
	}
}
