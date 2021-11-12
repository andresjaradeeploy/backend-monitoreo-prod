package com.monitor.bankendmonitoreoLinks.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitor.bankendmonitoreoLinks.entity.pages.Ocr;

@Repository
public interface IOcrRepository extends JpaRepository<Ocr,Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM ocr " + "where post = :idPost")

	Ocr findOcrbyPost(@Param("idPost") String idPost);

}
