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
	
	@GetMapping("/obtener/{idPost}")
	public Post obtenerPost(@PathVariable String idPost) {
		return iPostService.findById(idPost);
	}
	
	
	
	@PostMapping("/save")
	public Post save(@RequestBody Post post) {
		
		return iPostService.save(post);
	}
	
	@PutMapping("/update")
	public Post update(@RequestBody Post post) {
		
		Post postNew= iPostService.findById(post.getIdPost());
		
		postNew.setLikes(post.getLikes());
		postNew.setLove(post.getLove());
		postNew.setWow(post.getWow());
		postNew.setHaha(post.getHaha());
		postNew.setSorry(post.getSorry());
		postNew.setAnger(post.getAnger());
		postNew.setPost_impressions_unique(post.getPost_impressions_unique());
		
		return iPostService.save(postNew);
		

	}
	
	@PutMapping("/updatePicture")
	public Post updatePicture(@RequestBody Post post) {
		
		Post postNew= iPostService.findById(post.getIdPost());
		postNew.setIdPost(post.getIdPost());
		postNew.setFull_picture(post.getFull_picture());
		
		postNew.setPage(post.getPage());
		
		return iPostService.save(postNew);
	}
	
}
