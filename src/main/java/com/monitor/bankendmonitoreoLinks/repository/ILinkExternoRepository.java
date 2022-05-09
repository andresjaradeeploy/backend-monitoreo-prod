package com.monitor.bankendmonitoreoLinks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monitor.bankendmonitoreoLinks.entity.monitor.LinkExterno;


@Repository
public interface ILinkExternoRepository  extends JpaRepository<LinkExterno, Long> {

}
