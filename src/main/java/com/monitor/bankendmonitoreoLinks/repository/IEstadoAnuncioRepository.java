package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public interface IEstadoAnuncioRepository extends JpaRepository<EstadoAnuncio, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM estado_anuncio AS es " + "inner join anuncio as a on "
			+ "a.id_anuncio =es.anuncio " + "INNER JOIN ad_creative AS ad ON " + "ad.id_creative= a.ad_creative "
			+ "INNER JOIN estado AS e ON " + "e.id_estado = es.estado " + "inner join cuentafb as c on "
			+ "a.cuenta_fb=c.id_cuentafb " + "where a.cuenta_fb = :idCuenta and a.status = 'ACTIVE'")
	List<EstadoAnuncio> findAlertabycuentaFB(@Param("idCuenta") String idCuenta);
	
	@Query(nativeQuery = true,value="SELECT count(*) FROM estado_anuncio "
			+ "where estado=1 ")
	Integer cantidadLinksArriba();
	
	@Query(nativeQuery = true,value="SELECT count(*) FROM estado_anuncio "
			+ "where estado=2 ")
	Integer cantidadLinksCaidos();

}
