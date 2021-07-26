package com.monitor.bankendmonitoreoLinks.dao;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;

public interface IAdCreative {
	public List<AdCreative> obtenerAdCreative();

	public int guardar(AdCreative adCreative);

	public boolean verificarSiExisteAdCreative(Long idAdCreative);

	public List<AdCreative> listarAdCreatives();
}
