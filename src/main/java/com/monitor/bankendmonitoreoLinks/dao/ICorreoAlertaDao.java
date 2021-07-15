package com.monitor.bankendmonitoreoLinks.dao;

import java.util.List;

import com.monitor.bankendmonitoreoLinks.entity.monitor.CorreoAlerta;

public interface ICorreoAlertaDao {
	public List<CorreoAlerta> correosByCuenta(String cuenta);

}
