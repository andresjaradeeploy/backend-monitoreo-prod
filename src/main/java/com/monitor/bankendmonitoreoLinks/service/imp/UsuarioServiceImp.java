package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.monitor.bankendmonitoreoLinks.entity.admin.Usuario;
import com.monitor.bankendmonitoreoLinks.repository.IUsuarioRepository;
import com.monitor.bankendmonitoreoLinks.service.IUsuarioService;

@Service
@Transactional
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	IUsuarioRepository usuarioRepository;

	public Optional<Usuario> getByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	public boolean existsEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario crear(Usuario usuario) {

		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> listarUsers() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public void delete(Integer idUsuario) {
		usuarioRepository.deleteById(idUsuario);

	}

}