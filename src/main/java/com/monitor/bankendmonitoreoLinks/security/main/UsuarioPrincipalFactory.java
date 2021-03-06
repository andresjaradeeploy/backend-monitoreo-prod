package com.monitor.bankendmonitoreoLinks.security.main;

import java.util.List;
import java.util.stream.Collectors;

import com.monitor.bankendmonitoreoLinks.entity.admin.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UsuarioPrincipalFactory {
	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
		return new UsuarioPrincipal(usuario.getEmail(), usuario.getPassword(), authorities);
	}
}
