package com.monitor.bankendmonitoreoLinks.dao;

import com.monitor.bankendmonitoreoLinks.entity.pages.Page;
import com.monitor.bankendmonitoreoLinks.entity.pages.Post;

public interface PostDao {
	public int guardar(Post post,Page page);
	public boolean verificarSiExistePost(String id_post);
	public int actualizar(Post post);

}
