package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;

public interface IEstadoLinkExternoRepository extends JpaRepository<EstadoLinkExterno, Long> {

	
	@Query(nativeQuery = true, value = "select  * from "
			+ "estado_link_externo as ele "
			+ "inner join link_externo as le on "
			+ "ele.link_externo =le.id_link  "
			+ "inner join cuenta as cu on "
			+ "le.cuenta_id_cuenta = cu.id_cuenta  "
			+ "where cu.id_cuenta = :idCuenta ")
	List<EstadoLinkExterno> findEstadoLinKExternobyCuenta(@Param("idCuenta") Long idCuenta);
}
