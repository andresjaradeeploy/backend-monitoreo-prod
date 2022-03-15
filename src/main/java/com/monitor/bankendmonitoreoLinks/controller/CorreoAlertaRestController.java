package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CorreoAlerta;
import com.monitor.bankendmonitoreoLinks.service.ICorreoAlertaService;

@RestController
@RequestMapping("/correoAlerta")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200","https://localhost:4200","https://sonar.enki.com.co"})//@CrossOrigin(origins = { "http://localhost:4200", "https://monitoreo-ads-fb.web.app","*" })
public class CorreoAlertaRestController {

	@Autowired
	private ICorreoAlertaService alertaService;

	@PostMapping("/agregarCorreo")
	public CorreoAlerta agregar(@RequestBody CorreoAlerta correoAlerta) {
		return alertaService.save(correoAlerta);
	}

	@GetMapping("/all")
	public List<CorreoAlerta> index() {
		return alertaService.findAll();
	}

	@GetMapping("/all/{idCuenta}")
	public List<CorreoAlerta> listarByCuentaFB(@PathVariable String idCuenta) {
		return alertaService.findCorreoAlertabycuentaFB(idCuenta);
	}

	@DeleteMapping("/borrar/{idCorreo}")
	public void borrarCorreo(@PathVariable Long idCorreo) {

		alertaService.borrarCorreo(idCorreo);
	}
}
