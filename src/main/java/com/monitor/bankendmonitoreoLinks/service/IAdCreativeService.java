package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;



public interface IAdCreativeService {
	
	public List<AdCreative> findAdCreativebycuentaFB(String idCuenta);

}
