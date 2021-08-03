package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monitor.bankendmonitoreoLinks.entity.monitor.CorreoAlerta;

public interface ICorreoAlertaRepository extends JpaRepository<CorreoAlerta, Long> {

	@Query(nativeQuery = true, value = "select * from correo_alerta  where cuenta_fb = :idCuenta")
	List<CorreoAlerta> findCorreoAlertabycuentaFB(@Param("idCuenta") String idCuenta);
}
