package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.pages.Page;
import com.monitor.bankendmonitoreoLinks.service.IPageService;
import com.monitor.bankendmonitoreoLinks.service.imp.PageServiceImp;

@RestController
@RequestMapping("/page")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200","https://localhost:4200"})
public class PageRestController {
//s
	
	@Autowired
	private PageServiceImp iPageService;

	@GetMapping("/all")
	public List<Page> listarPages() {
		return iPageService.findAll();
	}
	
	@GetMapping("/buscar/{idPage}")
	public Page buscarPage(@PathVariable String idPage) {
		return iPageService.findbyIdPage(idPage);
	}
	
	@PostMapping("/save")
	public Page save(@RequestBody Page page) {
		
		/*if(iPageService.existsIdPage(page.getIdPage())) {
			System.out.println("ya existe page");
			return update(page);
		}
		else*/
		return iPageService.save(page);


	
	
}
	
	
	@PutMapping("/update")
		public Page update(@RequestBody Page page) {
		Page pageNew= new Page();
			
				
				
				pageNew.setAccess_token(page.getAccess_token());
				pageNew.setCategory(page.getCategory());
				pageNew.setCover(page.getCover());
				pageNew.setFan_count(page.getFan_count());
				pageNew.setIdPage(page.getIdPage());
				pageNew.setLink(page.getLink());
				pageNew.setName(page.getName());
				pageNew.setPicture(page.getPicture());
				pageNew.setSocial_sentence(page.getSocial_sentence());
				return iPageService.save(pageNew);
			
	
			}
}
