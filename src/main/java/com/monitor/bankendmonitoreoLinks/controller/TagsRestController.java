package com.monitor.bankendmonitoreoLinks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.bankendmonitoreoLinks.components.GoogleVision;
import com.monitor.bankendmonitoreoLinks.components.Utilidades;
import com.monitor.bankendmonitoreoLinks.components.implement.OcrImp;
import com.monitor.bankendmonitoreoLinks.components.implement.PostImp;
import com.monitor.bankendmonitoreoLinks.components.implement.TagsImp;
import com.monitor.bankendmonitoreoLinks.entity.pages.Post;
import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;
import com.monitor.bankendmonitoreoLinks.repository.ITagsRepository;
import com.monitor.bankendmonitoreoLinks.service.ITagsService;
import com.monitor.bankendmonitoreoLinks.service.imp.TagServiceImp;



@RestController
@RequestMapping("/tags")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200","https://localhost:4200"})
public class TagsRestController {

	
	private GoogleVision googleVision = new GoogleVision() ;
	
	@Autowired
	private ITagsService ITagsService;
	
	@Autowired
	private ITagsRepository iTagsRepository;

	@GetMapping("/all/{idPost}")
	public List<Tags> listarTagsByPost(@PathVariable String idPost) {
		return iTagsRepository.findTagsbyPost(idPost);
	}
	
	@GetMapping("/insight/{tag}")
	public List<Tags> agrupacionTags(@PathVariable String tag)
	{
		return iTagsRepository.findPostWithTags(tag);
	}
	
	@PostMapping("/guardarTag")
	public List <String> guardar(@RequestBody Post post){
		
		Utilidades utilidades = new Utilidades();
		
		TagsImp tagsImp = new TagsImp();
		PostImp postImp= new PostImp();
		
		System.out.println("picture"+post.getFull_picture()+"idpost"+post.getIdPost());
		
		try {
			utilidades.guardarImageneUrl(post.getFull_picture(),post.getIdPost());
		} catch (Exception e) {
			System.out.println("Ya existe imagen ");
		}
		
		
		
		List<String> tags= new ArrayList();
		//tags=obtenerTags(post.getFull_picture(), post.getIdPost());
		String nameImage=postImp.obtenerNameImageById(post.getIdPost());
		String filePath = "C:/home/images/"+nameImage;
		try {
			tags=googleVision.detectLabels(filePath);
			for (String tag : tags) {
				boolean ifOnlyString=utilidades.verificarSiSoloLetras(tag);
				if (ifOnlyString==true) {
					System.out.println("tag"+tag);
					boolean ifExistTag=tagsImp.verificarSiExisteTag(tag,post.getIdPost());
					if (ifExistTag==false) {
						Tags tagNew= new Tags();
						Post postNew= new Post();
						postNew.setIdPost(post.getIdPost());
						tagNew.setNameTag(tag);
						tagNew.setPost(postNew);
						//tagsImp.guardar(tagNew);
						ITagsService.save(tagNew);
						
						System.out.println("tamano"+tags.size());
						
					}
					else
						System.out.println("El tag ya existe");
				}
				else
					System.err.println("tag no es label");
				
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return tags;
		
	}
	
	
	
	
	
public List<String>  obtenerTags(String full_picture,String idPost) {
	Utilidades utilidades= new Utilidades();
	TagsImp tagsImp = new TagsImp();
	PostImp postImp= new PostImp();
	List<String> tags= new ArrayList();
	String nameImage=postImp.obtenerNameImageById(idPost);
	String filePath = "C:/home/images/"+nameImage;
	try {
		tags=googleVision.detectLabels(filePath);
		for (String tag : tags) {
			boolean ifOnlyString=utilidades.verificarSiSoloLetras(tag);
			if (ifOnlyString==true) {
				boolean ifExistTag=tagsImp.verificarSiExisteTag(tag,idPost);
				if (ifExistTag==false) {
					Tags tagNew= new Tags();
					Post post= new Post();
					post.setIdPost(idPost);
					tagNew.setNameTag(tag);
					tagNew.setPost(post);
					tagsImp.guardar(tagNew);
					tags.add(tag);
					
				}
				else
					System.out.println("El tag ya existe");
			}
			else
				System.err.println("tag no es label");
			
		}
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	
	return tags;
}
}
