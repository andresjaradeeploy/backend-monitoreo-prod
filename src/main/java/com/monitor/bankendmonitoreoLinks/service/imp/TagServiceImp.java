package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;
import com.monitor.bankendmonitoreoLinks.repository.ITagsRepository;
import com.monitor.bankendmonitoreoLinks.service.ITagsService;

@Service
public class TagServiceImp implements ITagsService {
	
	@Autowired
	private ITagsRepository tagsRepository;

	@Override
	public List<Tags> findTagsbypost(String idPost) {
		return (List<Tags>)  tagsRepository.findTagsbyPost(idPost);
	}

}
