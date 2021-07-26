package com.monitor.bankendmonitoreoLinks.security.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.bankendmonitoreoLinks.entity.admin.Usuario;
import com.monitor.bankendmonitoreoLinks.service.imp.UsuarioServiceImp;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioServiceImp usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.getByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("email no encontrado"));
		return UsuarioPrincipalFactory.build(usuario);
	}

}
