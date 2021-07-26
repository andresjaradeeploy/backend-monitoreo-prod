package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Alerta;


public interface IAlertaRepository extends JpaRepository<Alerta, Long>{

	@Query(nativeQuery = true, value = "SELECT al.id_alerta,adc.link, e.code_status,e.mensaje,al.fecha_hora,c.nombre_cuenta "
			+ "FROM alerta AS al "
			+ "INNER JOIN estado_anuncio AS e ON "
			+ "al.estado_anuncio =e.id_estado_anuncio "
			+ "INNER JOIN anuncio AS ad ON "
			+ "ad.id_anuncio = e.anuncio "
			+ "INNER JOIN ad_creative AS adc ON "
			+ "adc.id_creative= ad.ad_creative "
			+ "inner join cuentafb as c on "
			+ "c.id_cuentafb= ad.cuenta_fb  "
			+ "where ad.cuenta_fb = :idCuenta")
    List<Alerta> findAlertabycuentaFB(@Param("idCuenta")String idCuenta);
}
