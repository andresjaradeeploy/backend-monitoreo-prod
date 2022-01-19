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
	
	
	
	@PostMapping("/save")
	public Post save(@RequestBody Post post) {
		
		return iPostService.save(post);
	}
	
	@PutMapping("/update")
	public Post update(@RequestBody Post post) {
		
		Post postNew= new Post();
		postNew.setIdPost(post.getIdPost());
		postNew.setPost_reactions_like_total(post.getPost_reactions_like_total());
		postNew.setPost_reactions_love_total(post.getPost_reactions_love_total());
		postNew.setPost_reactions_wow_total(post.getPost_reactions_wow_total());
		postNew.setPost_negative_feedback(post.getPost_negative_feedback());
		postNew.setPost_engaged_users(post.getPost_engaged_users());
		postNew.setPost_engaged_fan(post.getPost_engaged_fan());
		postNew.setPost_clicks_unique(post.getPost_clicks_unique());
		postNew.setPost_clicks(post.getPost_clicks());
		return iPostService.save(postNew);
	}
	
}
