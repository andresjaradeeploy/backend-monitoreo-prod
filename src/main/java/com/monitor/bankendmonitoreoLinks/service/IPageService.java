package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;


import com.monitor.bankendmonitoreoLinks.entity.pages.Page;

public interface IPageService {
	
	public List<Page> findAll();
	
	public Page findbyIdPage(String idPage);

}
