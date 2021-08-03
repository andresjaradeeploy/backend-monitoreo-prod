package com.monitor.bankendmonitoreoLinks.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.bankendmonitoreoLinks.entity.admin.Rol;
import com.monitor.bankendmonitoreoLinks.enums.RolNombre;
import com.monitor.bankendmonitoreoLinks.repository.IRolRepository;

@Service
@Transactional
public class RolServiceImp {

	@Autowired
	IRolRepository rolRepository;

	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		return rolRepository.findByRolNombre(rolNombre);
	}

	public boolean existsRolNombre(RolNombre rolNombre) {
		return rolRepository.existsByRolNombre(rolNombre);
	}

	public void save(Rol rol) {
		rolRepository.save(rol);
	}
}
