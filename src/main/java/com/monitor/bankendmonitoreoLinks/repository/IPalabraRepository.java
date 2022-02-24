package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Palabras;


public interface IPalabraRepository  extends JpaRepository<Palabras, Long>{
	@Query(nativeQuery = true, value = "SELECT * FROM palabras " + "where anuncio_id_anuncio = :id_anuncio")

	List<Palabras> findTagsbyAnuncio(@Param("id_anuncio") String id_anuncio);
	
	
}
