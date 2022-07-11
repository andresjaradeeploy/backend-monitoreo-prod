package com.monitor.bankendmonitoreoLinks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.bankendmonitoreoLinks.entity.admin.Usuario;
import com.monitor.bankendmonitoreoLinks.entity.pages.UsuarioEnkiEntity;
import com.monitor.bankendmonitoreoLinks.service.IUsuarioEnkiService;

@RestController
@RequestMapping("/usuarioEnki")
@CrossOrigin(origins = {"https://monitoreo-ads-fb.web.app","https://enki.com.co","http://localhost:4200","https://localhost:4200"})
public class UsuarioEnkiRestController {
	
	@Autowired
	private IUsuarioEnkiService iUsuarioEnkiService;
	
	@PostMapping("/agregarUsuario")
	public UsuarioEnkiEntity agregar(@RequestBody UsuarioEnkiEntity user) {
		

				if(iUsuarioEnkiService.findById(user.getIdUser())!=null){
					
				}
				else
				{
					
					 return iUsuarioEnkiService.crear(user);
				}
		return  null;
	}
	
	@PostMapping("/validarUsuario")
	public Boolean validarUsuario(@RequestBody UsuarioEnkiEntity user) {
		boolean autorizado=false;
			UsuarioEnkiEntity usuarioEnkiEntity= new UsuarioEnkiEntity();
			usuarioEnkiEntity= iUsuarioEnkiService.findById(user.getIdUser());
			
			if(usuarioEnkiEntity!=null){
				autorizado=usuarioEnkiEntity.isAutorizado();
			}
			else
			{
				
			 iUsuarioEnkiService.crear(user);
			 autorizado=false;
			}
		 return autorizado;
	}

}
