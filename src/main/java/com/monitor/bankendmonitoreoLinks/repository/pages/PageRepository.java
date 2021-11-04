package com.monitor.bankendmonitoreoLinks.repository.pages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monitor.bankendmonitoreoLinks.entity.pages.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, String>{

}
