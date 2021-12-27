package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;
import java.util.Optional;

import com.monitor.bankendmonitoreoLinks.entity.pages.Page;

public interface IPageService {
	
	@SuppressWarnings("rawtypes")
	public Optional findByIdPage(String idPage);
	
	public List<Page> findAll();
	
	public Page findbyIdPage(String idPage);
	
	public Page save(Page page);

}
