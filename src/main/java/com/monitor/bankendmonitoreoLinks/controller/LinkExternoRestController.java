package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.bankendmonitoreoLinks.entity.monitor.LinkExterno;
import com.monitor.bankendmonitoreoLinks.service.ILinkExternoService;

@RestController
@RequestMapping("/linkExterno")
@CrossOrigin(origins = { "https://monitoreo-ads-fb.web.app", "https://enki.com.co", "http://localhost:4200",
		"https://localhost:4200", "https://sonar.enki.com.co" })
//@CrossOrigin(origins = { "http://localhost:4200", "https://monitoreo-ads-fb.web.app","*" })
public class LinkExternoRestController {

	@Autowired
	private ILinkExternoService iLinkExternoService;

	@GetMapping("/")
	public List<LinkExterno> index() {
		return (List<LinkExterno>) iLinkExternoService.findAll();

	}

	@GetMapping("/buscar/{idLink}")
	public LinkExterno listarByCuentaFB(@PathVariable Long idLink) {
		return iLinkExternoService.findById(idLink);

	}

	@PostMapping("/save")
	public LinkExterno save(@RequestBody LinkExterno linkExterno) {

		return iLinkExternoService.save(linkExterno);

	}
}
