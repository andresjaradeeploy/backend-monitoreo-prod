package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.monitor.LinkExterno;
import com.monitor.bankendmonitoreoLinks.repository.ILinkExternoRepository;
import com.monitor.bankendmonitoreoLinks.service.ILinkExternoService;


@Service
public class LinkExternoServiceImp implements ILinkExternoService {

	@Autowired
	private ILinkExternoRepository iLinkExternoRepository;
	
	@Override
	public List<LinkExterno> findAll() {
		return iLinkExternoRepository.findAll();
	}

	@Override
	public LinkExterno findById(Long linkExterno) {
	return iLinkExternoRepository.findById(linkExterno).orElse(null);
	}

	@Override
	public LinkExterno save(LinkExterno linkExterno) {
	return iLinkExternoRepository.save(linkExterno);
	}

	@Override
	public void deleteById(Long linkExterno) {
		 iLinkExternoRepository.deleteById(linkExterno);
		
	}

}
