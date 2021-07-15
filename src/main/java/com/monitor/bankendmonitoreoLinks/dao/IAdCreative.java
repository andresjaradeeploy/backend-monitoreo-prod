package com.monitor.bankendmonitoreoLinks.dao;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;



public interface IAdCreative {
	public List<AdCreative> obtenerAdCreative();
	public int guardar(AdCreative adCreativ,CuentaFB cuentaFB);
	public boolean verificarSiExisteAdCreative(Long idAdCreative);
	public List<AdCreative> listarAdCreatives();
}
