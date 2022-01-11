package com.monitor.bankendmonitoreoLinks.controller;



import java.io.IOException;
import java.util.ArrayList;
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

import com.monitor.bankendmonitoreoLinks.components.GoogleVision;
import com.monitor.bankendmonitoreoLinks.components.Utilidades;
import com.monitor.bankendmonitoreoLinks.components.implement.OcrImp;
import com.monitor.bankendmonitoreoLinks.components.implement.PostImp;
import com.monitor.bankendmonitoreoLinks.components.implement.TagsImp;
import com.monitor.bankendmonitoreoLinks.entity.pages.Ocr;
import com.monitor.bankendmonitoreoLinks.entity.pages.Post;
import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;
import com.monitor.bankendmonitoreoLinks.repository.IOcrRepository;
import com.monitor.bankendmonitoreoLinks.service.IOcrService;



@RestController
@RequestMapping("/ocr")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200","https://localhost:4200"})
public class OcrRestController {

	private GoogleVision googleVision= new GoogleVision();
	
	@Autowired
	private IOcrService IOcrService;
	
	@Autowired
	private IOcrRepository IOcrRepository;

	@GetMapping("/{idPost}")
	public Ocr listarTagsByPost(@PathVariable String idPost) {
		return IOcrRepository.findOcrbyPost(idPost);
	}
	
	
	
	@PostMapping("/guardarOcr")
	public Ocr guardar(@RequestBody Post post){

		Utilidades utilidades = new Utilidades();
		Ocr ocr=new Ocr();
		OcrImp ocrImp = new OcrImp();
		PostImp postImp= new PostImp();
		
		System.out.println("picture"+post.getFull_picture()+"idpost"+post.getIdPost());
		
		try {
			utilidades.guardarImageneUrl(post.getFull_picture(),post.getIdPost());
		} catch (Exception e) {
			System.out.println("Ya existe imagen ");
		}
		
		
		
		
		//tags=obtenerTags(post.getFull_picture(), post.getIdPost());
		String nameImage=postImp.obtenerNameImageById(post.getIdPost());
		String filePath = "C:/home/images/"+nameImage;
		try {
			String ocrVision=googleVision.detectText(filePath);
			boolean ifExistsOcr=ocrImp.verificarSiExisteOcr(post.getIdPost());
			
			ocr.setDescriptionOcr(ocrVision);
			ocr.setPost(post);
			if(ifExistsOcr==false) {
				IOcrService.save(ocr);
				
				//ocrImp.guardar(ocr);
			}
			else
			{
			
			ocrImp.actualizar(ocr);
			System.out.println("Ta existe OCR de el post"+"actualizado"+post.getIdPost());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
			
		
		return ocr;
		
	}
}
