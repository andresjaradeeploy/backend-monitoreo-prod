package com.monitor.bankendmonitoreoLinks.components.implement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.monitor.bankendmonitoreoLinks.components.GoogleVision;
import com.monitor.bankendmonitoreoLinks.components.Utilidades;
import com.monitor.bankendmonitoreoLinks.components.conector.Conector;
import com.monitor.bankendmonitoreoLinks.dao.PostDao;
import com.monitor.bankendmonitoreoLinks.entity.pages.Ocr;
import com.monitor.bankendmonitoreoLinks.entity.pages.Page;
import com.monitor.bankendmonitoreoLinks.entity.pages.Post;
import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;

public class PostImp implements PostDao {

	private static final String SQL_INSERT = "INSERT INTO post(id_post,created_time,message,page_id_page)"
			+ " VALUES(?, ?, ?, ?)";

	private static final FacebookImp FACEBOOK_IMP = new FacebookImp();

	private static final String SQL_SELECT_BY_ID = "SELECT id_post " + " FROM post WHERE id_post = ?";
	
	private static final String SQL_SELECT_POST_BY_ID = "SELECT name_image " + " FROM post WHERE id_post = ?";

	private static final String SQL_UPDATE = "UPDATE post"
			+ " SET full_picture=?, permalink_url=?, picture=?, shares=?, name_image=? WHERE id_post=?";
	
	private GoogleVision googleVision= new GoogleVision();

	public List<Post> obtenerAllPost() {

		ArrayList<String> idPages = new ArrayList<String>();
		PageImp pageImp = new PageImp();
		Utilidades utilidades = new Utilidades();
		TagsImp tagsImp = new TagsImp();
		OcrImp ocrImp = new OcrImp();
		idPages = pageImp.obtenerPagesBD();
		List<Post> posts = new ArrayList<>();
		String post = null;
		String post2 = null;
		JSONObject resut;
		for (int i = 0; i < idPages.size(); i++) {

			post = "[";
			post = post + FACEBOOK_IMP.apiGraphPage(idPages.get(i) + "?fields=posts.limit(10)");
			post = post + "]";
			Page page = new Page();
			page.setIdPage(idPages.get(i));
			JSONArray respuesta = new JSONArray(post);
			resut = respuesta.getJSONObject(0);
			JSONObject data = null;
			JSONArray objeto = null;
			try {
				data = resut.getJSONObject("posts");
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				objeto = data.getJSONArray("data");
			} catch (Exception e) {
				// TODO: handle exception
			}

			//System.out.println("data" + objeto.length());

			if(objeto!=null) {
			for (int j = 0; j < objeto.length(); j++) {
				Post postNew = new Post();
				JSONObject resultadoPost = objeto.getJSONObject(j);
				postNew.setIdPost(resultadoPost.getString("id"));
				postNew.setCreated_time(resultadoPost.getString("created_time"));
				try {
					postNew.setMessage(resultadoPost.getString("message"));
				} catch (Exception e) {
					System.err.println("error al obtener mensaje");
				}

				postNew.setPage(page);
				boolean ifExists = verificarSiExistePost(postNew.getIdPost());
				if (ifExists == false)

					guardar(postNew, page);
				else {
					System.out.println("ya existe el post");
					post2 = "[";
					try {
						post2 = post2 + FACEBOOK_IMP
								.apiGraphPage(postNew.getIdPost() + "?fields=full_picture,permalink_url,picture,shares");
					} catch (Exception e) {
						
					}
					
					post2 = post2 + "]";

					String full_picture = null;
					String permalink_url = null;
					String picture = null;
					String shares;
					List<String> tags= new ArrayList();
					Integer sharesNumber = 0;
					JSONArray arrayData = new JSONArray(post2);
					JSONObject labels = arrayData.getJSONObject(0);
					try {
						full_picture = labels.getString("full_picture");
					} catch (Exception e) {
						full_picture = "No Tiene Picture";
					}
					try {
						permalink_url = labels.getString("permalink_url");
					} catch (Exception e) {
						permalink_url = "No tiene permalink_url";
					}
					try {
						picture = labels.getString("picture");
					} catch (Exception e) {
						picture = "No tiene picture";
					}
					try {
						shares = labels.getString("shares");
					} catch (Exception e) {
						shares = "No tiene shares";
					}
					String idPost = labels.getString("id");
					if (shares != "No tiene shares") {
						sharesNumber = Integer.parseInt(shares);
					}

					utilidades.guardarImageneUrl(full_picture, idPost);

					System.out.println("full_picture" + full_picture);
					System.out.println("permalink_url" + permalink_url);
					System.out.println("picture" + picture);
					System.out.println("shares" + shares);
					postNew.setFull_picture(full_picture);
					postNew.setPermalink_url(permalink_url);
					postNew.setPicture(picture);
					postNew.setShares(sharesNumber);
					postNew.setIdPost(idPost);
					postNew.setNameImage(obtenerNameImageById(idPost));
					String filePath = "C:/home/images/"+postNew.getNameImage();
					try {
						tags=googleVision.detectLabels(filePath);
						for (String string : tags) {
							boolean ifOnlyString=utilidades.verificarSiSoloLetras(string);
							if (ifOnlyString==true) {
								boolean ifExistTag=tagsImp.verificarSiExisteTag(string,postNew.getIdPost());
								if (ifExistTag==false) {
									Tags tag= new Tags();
									tag.setNameTag(string);
									tag.setPost(postNew);
									tagsImp.guardar(tag);
								}
								else
									System.out.println("El tag ya existe");
							}
							else
								System.err.println("tag no es label");
							
						}
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
					
					try {
						String ocrVision=googleVision.detectText(filePath);
						boolean ifExistsOcr=ocrImp.verificarSiExisteOcr(idPost);
						if(ifExistsOcr==false) {
							Ocr ocr=new Ocr();
							ocr.setDescriptionOcr(ocrVision);
							ocr.setPost(postNew);
							ocrImp.guardar(ocr);
						}
						else
							System.out.println("Ta existe OCR de el post"+postNew.getIdPost());
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					postNew.setNameImage(idPost + ".jpg");
					actualizar(postNew);
				}
			}
			}
			else
				System.err.println("no tiene datos");
			//JSONObject dataposts = objeto.getJSONObject(i);
			//System.out.println("tamaño" + idPages.get(i) + objeto.length());

			post = "";

		}

		return posts;
	}

	@Override
	public int guardar(Post post, Page page) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);

			stmt.setString(1, post.getIdPost());
			stmt.setString(2, post.getCreated_time());
			stmt.setString(3, post.getMessage());
			stmt.setString(4, page.getIdPage());

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println("error al guardar post" + ex);
		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;

	}

	@Override
	public boolean verificarSiExistePost(String idPost) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setString(1, idPost);
			rs = stmt.executeQuery();

			if (rs.next())
				res = true;
			else
				res = false;

		} catch (Exception e) {
			System.err.print("Ha ocurrido un error: " + e.getMessage());

		} finally {
			Conector.close(conn);
			Conector.close(stmt);
			Conector.close(rs);
		}
		return res;
	}

	
	@Override
	public String obtenerNameImageById(String idPost) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String name_image= null;
		try {
			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_POST_BY_ID);
			stmt.setString(1, idPost);
			rs = stmt.executeQuery();

			if (rs.next())
				
			name_image=rs.getString("name_image");
			else
				System.err.println("no existe post de id"+idPost);

		} catch (Exception e) {
			System.err.print("Ha ocurrido un error: " + e.getMessage());

		} finally {
			Conector.close(conn);
			Conector.close(stmt);
			Conector.close(rs);
		}
		return name_image;
	}

	
	@Override
	public int actualizar(Post post) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {

			conn = Conector.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);

			stmt.setString(1, post.getFull_picture());
			stmt.setString(2, post.getPermalink_url());
			stmt.setString(3, post.getPicture());
			stmt.setInt(4, post.getShares());
			stmt.setString(5, post.getNameImage());
			stmt.setString(6, post.getIdPost());

			rows = rows + stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);

		} finally {

			Conector.close(stmt);
			Conector.close(conn);
		}
		return rows;
	}

	public static void main(String[] args) {
		PostImp postImp = new PostImp();
		postImp.obtenerAllPost();
	}

}
