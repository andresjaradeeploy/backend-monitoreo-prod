package com.monitor.bankendmonitoreoLinks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;

@Repository
public interface ITagsRepository extends JpaRepository<Tags,Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM tags " + "where post_id_post = :idPost")

	List<Tags> findTagsbyPost(@Param("idPost") String idPost);
	
	
	@Query(nativeQuery = true, value = "SELECT * "
			+ "FROM tags as ta inner join post as po on "
			+ "ta.post_id_post =po.id_post "
			+ "where name_tag LIKE :tag")

	List<Tags> findPostWithTags(@Param("tag") String tag);
	
	
	
	@Query(nativeQuery = true, value = "SELECT distinct id_tag, post_id_post,name_tag, count(name_tag) "
			+ "	FROM tags "
			+ "	group by name_tag ")

	List<Tags> labelsGroupByName();
	
	
	@Query(nativeQuery = true, value = "SELECT * "
			+ "FROM tags as ta inner join post as po on "
			+ "ta.post_id_post =po.id_post "
			+ "inner join page pa on "
			+ "po.page_id_page = pa.id_page "
			+ "where name_tag LIKE:tag  and "
			+ "pa.id_page like:page ")

	List<Tags> findTagsbyPage(@Param("page") String page,@Param("tag") String tag);
	

	@Query(nativeQuery = true, value = "SELECT * "
			+ "FROM tags as ta inner join post as po on "
			+ "ta.post_id_post =po.id_post "
			+ "inner join page pa on "
			+ "po.page_id_page = pa.id_page "
			+ "where pa.id_page like:page ")

	List<Tags> findTags(@Param("page") String page);
	
}
