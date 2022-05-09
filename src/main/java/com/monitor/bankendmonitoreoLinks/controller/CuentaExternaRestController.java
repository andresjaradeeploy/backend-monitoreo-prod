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
import com.monitor.bankendmonitoreoLinks.entity.monitor.Cuenta;
import com.monitor.bankendmonitoreoLinks.service.ICuentaExternaService;

@RestController
@RequestMapping("/cuentaExterna")
@CrossOrigin(origins = { "https://monitoreo-ads-fb.web.app", "https://enki.com.co", "http://localhost:4200",
		"https://localhost:4200", "https://sonar.enki.com.co" })
public class CuentaExternaRestController {

	@Autowired
	private ICuentaExternaService iCuentaExternaService;
	

	@GetMapping("/listar")
	public List<Cuenta> index() {
		return iCuentaExternaService.findAll();
	}

	@PostMapping("/crear")
	public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
			return iCuentaExternaService.save(cuenta);
	}

	@DeleteMapping("/borrar/{idCuenta}")
	public void borrarCuenta(@PathVariable Long idCuenta) {
		iCuentaExternaService.deleteById(idCuenta);
	}
}
