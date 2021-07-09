package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;


public interface AnuncioRepository  extends JpaRepository<Anuncio, String>{
	
	
	@Query(value = "SELECT a FROM Anuncio a WHERE a.cuentaFB =:#{#CuentaFB.idCuenta}")
	public List<Anuncio> findByCuentaFB(@Param("CuentaFB") CuentaFB cuentaFB);
	

}
