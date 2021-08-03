package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.repository.IAdCreativeRepository;
import com.monitor.bankendmonitoreoLinks.service.IAdCreativeService;

@Service
public class AdCreativeServiceImp implements IAdCreativeService {

	@Autowired
	private IAdCreativeRepository iAdCreativeRepository;

	@Override
	public List<AdCreative> findAdCreativebycuentaFB(String idCuenta) {

		return (List<AdCreative>) iAdCreativeRepository.findAnunciobycuentaFB(idCuenta);
	}

}
