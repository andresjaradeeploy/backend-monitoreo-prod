package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.bankendmonitoreoLinks.entity.pages.UsuarioEnkiEntity;
import com.monitor.bankendmonitoreoLinks.repository.IUsuarioEnkiRepository;
import com.monitor.bankendmonitoreoLinks.service.IUsuarioEnkiService;

@Service
public class UsuarioEnkiServiceImp implements IUsuarioEnkiService {
	
	@Autowired
	private IUsuarioEnkiRepository iUsuarioEnkiRepository;

	@Override
	public UsuarioEnkiEntity findByEmail(String email) {
	return null;
	}

	@Override
	public UsuarioEnkiEntity crear(UsuarioEnkiEntity usuarioEnkiEntity) {
		return iUsuarioEnkiRepository.save(usuarioEnkiEntity);
	}

	@Override
	public List<UsuarioEnkiEntity> listarUsers() {
		return iUsuarioEnkiRepository.findAll();
	}

	@Override
	public void delete(String idUsuario) {
	 iUsuarioEnkiRepository.deleteById(idUsuario);
		
	}

	@Override
	public UsuarioEnkiEntity findById(String IdUser) {
		return iUsuarioEnkiRepository.findById(IdUser).orElse(null);
	}

}
