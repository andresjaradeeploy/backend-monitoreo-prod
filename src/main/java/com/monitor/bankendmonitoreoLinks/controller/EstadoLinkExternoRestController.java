package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;
import com.monitor.bankendmonitoreoLinks.service.IEstadoLinkExternoService;

@RestController
@RequestMapping("/estadolinkExterno")
@CrossOrigin(origins = { "https://monitoreo-ads-fb.web.app", "https://enki.com.co", "http://localhost:4200",
		"https://localhost:4200", "https://sonar.enki.com.co" })
public class EstadoLinkExternoRestController {

	
	@Autowired 
	private IEstadoLinkExternoService iEstadoLinkExternoService;

	@GetMapping("/listar")
	public List<EstadoLinkExterno> index() {
		return (List<EstadoLinkExterno>) iEstadoLinkExternoService.obtenerAllEstados();
	}

	@GetMapping("/listar/{idCuenta}")
	public List<EstadoLinkExterno> listarByCuentaFB(@PathVariable Long idCuenta) {
		return iEstadoLinkExternoService.findEstadoLinKExternobyCuenta(idCuenta);
	}
}
