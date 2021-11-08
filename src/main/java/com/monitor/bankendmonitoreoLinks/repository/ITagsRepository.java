package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;

public interface ITagsRepository extends JpaRepository<Tags,Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM tags " + "where post_id_post = :idPost")

	List<Tags> findTagsbyPost(@Param("idPost") String idPost);
}
