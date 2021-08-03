package com.monitor.bankendmonitoreoLinks.service;

import java.util.List;
import java.util.Optional;

import com.monitor.bankendmonitoreoLinks.entity.admin.Usuario;

public interface IUsuarioService {
	@SuppressWarnings("rawtypes")
	public Optional findByEmail(String email);

	public Usuario crear(Usuario usuario);

	public List<Usuario> listarUsers();

	public void delete(Integer idUsuario);
}
