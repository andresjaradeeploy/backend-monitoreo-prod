package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.bankendmonitoreoLinks.entity.pages.Post;
import com.monitor.bankendmonitoreoLinks.service.IPostService;


@RestController
@RequestMapping("/post")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200"})
public class PostRestController {

	
	@Autowired
	private IPostService iPostService;

	@GetMapping("/all/{idPage}")
	public List<Post> listarPostByPage(@PathVariable String idPage) {
		return iPostService.findPostbyPage(idPage);
	}
}
