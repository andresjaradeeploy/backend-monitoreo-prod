package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.service.IEstadoAnuncioService;

@RestController
@CrossOrigin(origins = { "https://monitoreo-ads-fb.web.app" })
//@CrossOrigin(origins = { "http://localhost:4200", "https://monitoreo-ads-fb.web.app","*" })
@RequestMapping("/estadoAnuncio")
public class EstadoAnuncioRestController {

	@Autowired
	private IEstadoAnuncioService IEstadoAnuncioService;

	@GetMapping("/all")
	public List<EstadoAnuncio> index() {
		return (List<EstadoAnuncio>) IEstadoAnuncioService.findAll();
	}

	@GetMapping("/all/{idCuenta}")
	public List<EstadoAnuncio> listarByCuentaFB(@PathVariable String idCuenta) {
		return IEstadoAnuncioService.findEstadoAnunciobycuentaFB(idCuenta);
	}
	
	@GetMapping("/cantidadLinksCaidos")
	public Integer cantidadCaidos() {
		return IEstadoAnuncioService.cantidadLinksCaidos();
	}
	
	@GetMapping("/cantidadLinksArriba")
	public Integer cantidadArriba() {
		return IEstadoAnuncioService.cantidadLinksArriba();
	}
}
