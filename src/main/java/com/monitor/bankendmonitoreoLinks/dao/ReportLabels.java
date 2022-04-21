package com.monitor.bankendmonitoreoLinks.dao;

public class ReportLabels {

	private String nameTag;;
	private Integer cantidadTags;
	private Integer reactions;
	private double impressions;
	private double tasaInteracciones;

	public String getNameTag() {
		return nameTag;
	}

	public void setNameTag(String nameTag) {
		this.nameTag = nameTag;
	}

	public Integer getCantidadTags() {
		return cantidadTags;
	}

	public void setCantidadTags(Integer cantidadTags) {
		this.cantidadTags = cantidadTags;
	}

	public Integer getReactions() {
		return reactions;
	}

	public void setReactions(Integer reactions) {
		this.reactions = reactions;
	}

	public double getTasaInteracciones() {
		return tasaInteracciones;
	}

	public void setTasaInteracciones(double tasaInteracciones) {
		this.tasaInteracciones = tasaInteracciones;
	}

	public double getImpressions() {
		return impressions;
	}

	public void setImpressions(double impressions) {
		this.impressions = impressions;
	}

}
