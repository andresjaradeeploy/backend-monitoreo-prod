package com.monitor.bankendmonitoreoLinks.dao;

import java.util.List;





public interface IPalabraDao {
	public List<String> correosByAnuncio(String anuncio);
	public List<String> correosByLinkExterno(Long linkExterno);
}
