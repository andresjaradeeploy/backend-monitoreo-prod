package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Palabras;


public interface IPalabraService {

	public List<Palabras> findAll();

	public Palabras save(Palabras palabras);
	
	public List<Palabras> findTagsbyAnuncio(String idAnuncio);
}
