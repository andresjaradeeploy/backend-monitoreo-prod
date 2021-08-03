package com.monitor.bankendmonitoreoLinks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.monitor.bankendmonitoreoLinks.entity.admin.Rol;
import com.monitor.bankendmonitoreoLinks.enums.RolNombre;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByRolNombre(RolNombre rolNombre);

	boolean existsByRolNombre(RolNombre rolNombre);

}
