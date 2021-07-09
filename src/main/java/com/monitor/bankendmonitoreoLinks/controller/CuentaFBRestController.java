package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;
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
}
