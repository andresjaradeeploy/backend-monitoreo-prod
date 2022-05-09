package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.LinkExterno;

public interface ILinkExternoService {
	public List<LinkExterno> findAll();

	public LinkExterno findById(Long linkExterno);

	public LinkExterno save(LinkExterno linkExterno);

	public void deleteById(Long linkExterno);

}
