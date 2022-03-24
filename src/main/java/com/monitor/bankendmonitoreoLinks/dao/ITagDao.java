package com.monitor.bankendmonitoreoLinks.dao;



import java.util.ArrayList;
import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.pages.Tags;



public interface ITagDao {
	public int guardar(Tags tag);
	public boolean verificarSiExisteTag(String tag,String idTag);
	public List<Label> obtenerLabels(String page);
	public List<ReportLabels> obtenerReportByPage(String page);
}
