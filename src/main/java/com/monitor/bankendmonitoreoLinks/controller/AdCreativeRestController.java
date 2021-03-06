package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.service.IAdCreativeService;

@RestController
@RequestMapping("/adCreative")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200","https://localhost:4200","https://sonar.enki.com.co"})//@CrossOrigin(origins = { "http://localhost:4200", "https://monitoreo-ads-fb.web.app" })
public class AdCreativeRestController {

	@Autowired
	private IAdCreativeService iAdCreativeService;

	@GetMapping("/id/{idCuenta}")
	public List<AdCreative> listarByCuentaFB(@PathVariable String idCuenta) {
		return iAdCreativeService.findAdCreativebycuentaFB(idCuenta);
	}

}
