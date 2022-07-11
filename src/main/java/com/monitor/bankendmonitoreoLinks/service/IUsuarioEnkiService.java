package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;


import com.monitor.bankendmonitoreoLinks.entity.pages.UsuarioEnkiEntity;

public interface IUsuarioEnkiService {

	public UsuarioEnkiEntity findByEmail(String email);
	
	public UsuarioEnkiEntity findById(String IdUser);

	public UsuarioEnkiEntity crear(UsuarioEnkiEntity usuarioEnkiEntity);

	public List<UsuarioEnkiEntity> listarUsers();

	public void delete(String idUsuario);
}
