package com.monitor.bankendmonitoreoLinks.dao;



import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;



public interface ITagDao {
	public int guardar(Tags tag);
	public boolean verificarSiExisteTag(String tag,String idTag);

}
