package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Alerta;
import com.monitor.bankendmonitoreoLinks.service.IAlertaService;

@RestController
@RequestMapping("/alerta")
@CrossOrigin(origins = { "https://monitoreo-ads-fb.web.app","https://dashboard-enki.web.app"  })
//@CrossOrigin(origins = { "http://localhost:4200", "https://monitoreo-ads-fb.web.app" })
public class AlertaRestController {

	@Autowired
	private IAlertaService IAlertaService;

	@GetMapping("/all/{idCuenta}")
	public List<Alerta> listarByCuentaFB(@PathVariable String idCuenta) {
		return IAlertaService.findAlertabycuentaFB(idCuenta);
	}

	@GetMapping("/cantidad")
	public Integer cantidadAlertas() {
		return IAlertaService.cantidadDeAlertas();
	}

	
}
