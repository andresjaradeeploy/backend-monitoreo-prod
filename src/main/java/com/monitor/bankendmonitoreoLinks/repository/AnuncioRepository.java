package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM anuncio " + "where cuenta_fb = :idCuenta and status = 'ACTIVE' and impresiones > 0")

	List<Anuncio> findAnunciobycuentaFB(@Param("idCuenta") String idCuenta);

}
