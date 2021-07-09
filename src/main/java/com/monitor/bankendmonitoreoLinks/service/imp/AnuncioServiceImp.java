package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;
import com.monitor.bankendmonitoreoLinks.repository.AnuncioRepository;
import com.monitor.bankendmonitoreoLinks.service.IAnuncioService;

@Service
public class AnuncioServiceImp implements IAnuncioService {

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Anuncio> findAll() {
		return (List<Anuncio>) anuncioRepository.findAll();
	}

	@Override
	public Anuncio findById(String id_anuncio) {

		return anuncioRepository.findById(id_anuncio).orElse(null);
	}

	@Override
	public Anuncio save(Anuncio anuncio) {
		return anuncioRepository.save(anuncio);
	}

	@Override
	public void deleteById(String id_anuncio) {
		anuncioRepository.deleteById(id_anuncio);
	}

	@Override
	public List<Anuncio> findByCuentaFB(CuentaFB cuentaFB) {

		return anuncioRepository.findByCuentaFB(cuentaFB);
	}

}
