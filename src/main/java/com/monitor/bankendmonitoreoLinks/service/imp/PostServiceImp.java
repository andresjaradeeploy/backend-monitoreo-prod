package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.pages.Page;
import com.monitor.bankendmonitoreoLinks.entity.pages.Post;

import com.monitor.bankendmonitoreoLinks.repository.pages.PostRepository;
import com.monitor.bankendmonitoreoLinks.service.IPostService;




@Service
public class PostServiceImp implements IPostService{
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public List<Post> findPostbyPage(String idPage){
		return  postRepository.findPostbyPage(idPage);
	}


	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}


	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Optional<Post> getByIdPost(String idPost){
		return postRepository.findByidPost(idPost);
	}
	
	public boolean existsIdPost(String IdPost) {
		return postRepository.existsByidPost(IdPost);
	}

	@Override
	public Optional<Post> findByIdPost(String idPost) {
		return postRepository.findByidPost(idPost);
	}


	@Override
	public Post findById(String idPost) {
		return postRepository.findById(idPost).orElse(null);
	}
}
