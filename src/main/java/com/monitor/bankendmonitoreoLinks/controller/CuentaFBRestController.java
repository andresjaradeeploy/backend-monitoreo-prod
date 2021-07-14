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

import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFbDeveloper;
import com.monitor.bankendmonitoreoLinks.service.ICuentaFBService;

@RestController
@RequestMapping("/cuentaFB")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CuentaFBRestController {

	@Autowired
	private ICuentaFBService iCuentaFBService;
	
	@GetMapping("/listar")
	public List<CuentaFB> index(){
		return iCuentaFBService.findAll();
	}
	
	@PostMapping("/crear")
	
	public CuentaFB crearCuenta(@RequestBody CuentaFB cuentaFB){
		CuentaFbDeveloper cuentaFbDeveloper = new CuentaFbDeveloper();
		cuentaFbDeveloper.setIdCuenta(1000001);
		cuentaFB.setCuentaFbDeveloper(cuentaFbDeveloper);
		return iCuentaFBService.save(cuentaFB);
	}
	
	@DeleteMapping("/borrar/{idCuenta}")
	public void borrarCuenta(@PathVariable String idCuenta) {
		iCuentaFBService.deleteById(idCuenta);
	}
	
}
