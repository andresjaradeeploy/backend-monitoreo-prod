package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
