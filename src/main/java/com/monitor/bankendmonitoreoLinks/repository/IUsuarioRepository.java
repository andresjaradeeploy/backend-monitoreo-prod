package com.monitor.bankendmonitoreoLinks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monitor.bankendmonitoreoLinks.entity.admin.Usuario;

@Repository
public interface IUsuarioRepository  extends JpaRepository<Usuario, Integer>{

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    
  
}
