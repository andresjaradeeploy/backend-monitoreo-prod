package com.monitor.bankendmonitoreoLinks.components;

import com.monitor.bankendmonitoreoLinks.components.implement.PageImp;
import com.monitor.bankendmonitoreoLinks.components.implement.PostImp;

public class ApiPages {
	
	private PostImp postimp= new  PostImp();
	private PageImp pageImp = new PageImp();

	public void mainPage() {
		pageImp.obtenerPageInf();
		pageImp.obtenerAllPageInf();
		

	}
	public void mainPost() {
		postimp.obtenerAllPost();
	}
}
