package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Palabras;
import com.monitor.bankendmonitoreoLinks.repository.IPalabraRepository;
import com.monitor.bankendmonitoreoLinks.service.IPalabraService;


@Service
public class PalabrasServiceImp implements IPalabraService{
	@Autowired
	private IPalabraRepository iPalabraRepository;
	
	
	@Override
	public List<Palabras> findAll() {
		return iPalabraRepository.findAll();
	}

	@Override
	public Palabras save(Palabras palabras) {
	return iPalabraRepository.save(palabras);
	}

	@Override
	public List<Palabras> findTagsbyAnuncio(String idAnuncio) {
		return iPalabraRepository.findTagsbyAnuncio(idAnuncio);
	}

}
