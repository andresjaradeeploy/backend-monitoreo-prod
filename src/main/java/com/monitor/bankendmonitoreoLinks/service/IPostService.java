package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;


import com.monitor.bankendmonitoreoLinks.entity.pages.Post;


public interface IPostService {
	public List<Post> findPostbyPage(String idPage);
}
