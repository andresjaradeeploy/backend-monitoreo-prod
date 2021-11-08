package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.pages.Page;
import com.monitor.bankendmonitoreoLinks.repository.pages.PageRepository;
import com.monitor.bankendmonitoreoLinks.service.IPageService;

@Service
public class PageServiceImp implements IPageService {

	@Autowired
	private PageRepository pageRepository;
	
	@Override
	public List<Page> findAll() {
			return pageRepository.findAll();
	}

}
