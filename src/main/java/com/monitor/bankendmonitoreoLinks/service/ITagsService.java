package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;



public interface ITagsService {

	public List<Tags> findTagsbypost(String idPost);
	
	public Tags save(Tags tag);
}
