package com.monitor.bankendmonitoreoLinks.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.bankendmonitoreoLinks.components.LinkComponent;
import com.monitor.bankendmonitoreoLinks.dao.EstadoLinkFile;
import com.monitor.bankendmonitoreoLinks.dao.LinkFile;

@RestController
@RequestMapping("/revisarLinks")
@CrossOrigin(origins = { "https://monitoreo-ads-fb.web.app", "https://enki.com.co", "http://localhost:4200",
		"https://localhost:4200", "https://sonar.enki.com.co" })
public class RevisarLinksLotesRestController {
	
	@PostMapping("/generarEstado")
	public EstadoLinkFile listarByCuentaFB(@RequestBody LinkFile linkFile ) {
		LinkComponent linkComponent = new LinkComponent();
		return linkComponent.obtenerEstadoLinkFile(linkFile.getLink(), linkFile.getPalabra1(), linkFile.getPalabra2());
	}
	

}
