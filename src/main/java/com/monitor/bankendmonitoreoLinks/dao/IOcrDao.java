package com.monitor.bankendmonitoreoLinks.dao;



import com.monitor.bankendmonitoreoLinks.entity.pages.Ocr;



public interface IOcrDao {

	public int guardar(Ocr ocr);
	public boolean verificarSiExisteOcr(String idPost);
	
}
