package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public Page findbyIdPage(String idPage) {
	
		return pageRepository.findById(idPage).orElse(null);
	}

	@Override
	public Page save(Page page) {
		
		return pageRepository.save(page);
	}
	public Optional<Page> getByIdPage(String idPage){
		return pageRepository.findByidPage(idPage);
	}
	
	public boolean existsIdPage(String IdPage) {
		return pageRepository.existsByidPage(IdPage);
	}
	

	@Override
	public Optional<Page> findByIdPage(String idPage) {
		return pageRepository.findByidPage(idPage);
	}

}
