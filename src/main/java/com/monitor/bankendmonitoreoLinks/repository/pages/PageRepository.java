package com.monitor.bankendmonitoreoLinks.repository.pages;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monitor.bankendmonitoreoLinks.entity.pages.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, String>{

	Optional<Page> findByidPage(String idPage);
	boolean existsByidPage(String idPage);
}
