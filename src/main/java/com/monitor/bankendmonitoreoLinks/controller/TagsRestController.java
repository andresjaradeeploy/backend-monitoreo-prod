package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;
import com.monitor.bankendmonitoreoLinks.repository.ITagsRepository;



@RestController
@RequestMapping("/tags")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200"})
public class TagsRestController {

	
	@Autowired
	private ITagsRepository iTagsRepository;

	@GetMapping("/all/{idPost}")
	public List<Tags> listarTagsByPost(@PathVariable String idPost) {
		return iTagsRepository.findTagsbyPost(idPost);
	}
}
