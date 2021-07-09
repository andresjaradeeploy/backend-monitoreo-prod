package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Alerta;


public interface IAlertaRepository extends JpaRepository<Alerta, Long>{

	@Query(nativeQuery = true, value = "SELECT * "
			+ "FROM alerta AS al "
			+ "INNER JOIN estado_anuncio AS e ON "
			+ "al.estado_anuncio =e.id_estado_anuncio "
			+ "INNER JOIN anuncio AS a ON "
			+ "e.anuncio = a.id_anuncio "
			+ "inner join cuentafb as c on "
			+ "c.id_cuentafb= a.cuenta_fb "
			+ "where a.cuenta_fb = :idCuenta")
    List<Alerta> findAlertabycuentaFB(@Param("idCuenta")String idCuenta);
}
