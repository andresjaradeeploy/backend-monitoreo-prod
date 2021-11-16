package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.pages.Page;
import com.monitor.bankendmonitoreoLinks.service.IPageService;

@RestController
@RequestMapping("/page")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200"})
public class PageRestController {

	
	@Autowired
	private IPageService iPageService;

	@GetMapping("/all")
	public List<Page> listarPages() {
		return iPageService.findAll();
	}
	
	@GetMapping("/buscar/{idPage}")
	public Page buscarPage(@PathVariable String idPage) {
		return iPageService.findbyIdPage(idPage);
	}
	
	
}
