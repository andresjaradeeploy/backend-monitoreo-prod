package com.monitor.bankendmonitoreoLinks.dao;

import java.util.ArrayList;

import com.monitor.bankendmonitoreoLinks.entity.pages.Page;

public interface PagesDao {
	public int guardar(Page page);
	public ArrayList<String> obtenerPagesBD();
	public int actualizar(Page page);
}
