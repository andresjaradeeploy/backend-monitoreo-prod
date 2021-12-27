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

import com.monitor.bankendmonitoreoLinks.entity.pages.Page;
import com.monitor.bankendmonitoreoLinks.entity.pages.Post;
import com.monitor.bankendmonitoreoLinks.service.IPostService;
import com.monitor.bankendmonitoreoLinks.service.imp.PostServiceImp;


@RestController
@RequestMapping("/post")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200","https://localhost:4200"})
public class PostRestController {

	
	@Autowired
	private PostServiceImp iPostService;

	@GetMapping("/all/{idPage}")
	public List<Post> listarPostByPage(@PathVariable String idPage) {
		return iPostService.findPostbyPage(idPage);
	}
	
	@GetMapping("/all")
	public List<Post> listarPages() {
		return iPostService.findAll();
	}
	
	
	
	@PostMapping("/save")
	public Post save(@RequestBody Post post) {
		
		if(iPostService.existsIdPost(post.getIdPost())) {
			return null;
		}
		else
		return iPostService.save(post);
	}
	
}
