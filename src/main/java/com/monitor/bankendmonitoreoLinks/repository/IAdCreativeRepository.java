package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monitor.bankendmonitoreoLinks.entity.monitor.AdCreative;

public interface IAdCreativeRepository extends JpaRepository<AdCreative, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM ad_creative " + "where cuenta_fb = :idCuenta")

	List<AdCreative> findAnunciobycuentaFB(@Param("idCuenta") String idCuenta);

}
