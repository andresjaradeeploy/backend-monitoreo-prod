package com.monitor.bankendmonitoreoLinks.repository.pages;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitor.bankendmonitoreoLinks.entity.pages.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM post " + "where page_id_page= :idPage "
			+ "order by created_time desc limit 10")
	List<Post> findPostbyPage(@Param("idPage") String idPage);
}
