package com.monitor.bankendmonitoreoLinks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monitor.bankendmonitoreoLinks.entity.pages.UsuarioEnkiEntity;


@Repository
public interface IUsuarioEnkiRepository extends JpaRepository<UsuarioEnkiEntity, String> {

}
