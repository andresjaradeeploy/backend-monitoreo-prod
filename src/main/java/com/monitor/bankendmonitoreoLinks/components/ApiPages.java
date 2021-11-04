package com.monitor.bankendmonitoreoLinks.components;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.components.implement.AnuncioImp;
import com.monitor.bankendmonitoreoLinks.components.implement.PageImp;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.pages.Page;

public class ApiPages {
	
	
	public List<Page> ObteneryGuardarPages() {

	

		List<Page> pages = new PageImp().obtenerPageInf();

		return pages;
	}

	public void main() {
	

	}
}
