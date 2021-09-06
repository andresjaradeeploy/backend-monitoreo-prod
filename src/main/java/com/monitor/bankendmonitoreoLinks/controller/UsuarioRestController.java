package com.monitor.bankendmonitoreoLinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitor.bankendmonitoreoLinks.entity.admin.Usuario;
import com.monitor.bankendmonitoreoLinks.service.IUsuarioService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = { "https://monitoreo-ads-fb.web.app" })
//@CrossOrigin(origins = { "http://localhost:4200", "https://monitoreo-ads-fb.web.app","*" })
public class UsuarioRestController {

	@Autowired
	private IUsuarioService iUsuarioService;

	@PostMapping("/agregarUsuario")
	public Usuario agregar(@RequestBody Usuario user) {
		return iUsuarioService.crear(user);
	}

	@GetMapping("/listar")
	public List<Usuario> listar() {
		return (List<Usuario>) iUsuarioService.listarUsers();
	}

	@DeleteMapping("/delete/{idUsuario}")
	public void deleteUsuario(@PathVariable Integer idUsuario) {
		iUsuarioService.delete(idUsuario);
	}
}
