package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;
import java.util.Optional;


import com.monitor.bankendmonitoreoLinks.entity.pages.Post;


public interface IPostService {
	public List<Post> findPostbyPage(String idPage);
	
	public Post save(Post post);
	public List<Post> findAll();

	
	@SuppressWarnings("rawtypes")
	public Optional findByIdPost(String idPost);
}
