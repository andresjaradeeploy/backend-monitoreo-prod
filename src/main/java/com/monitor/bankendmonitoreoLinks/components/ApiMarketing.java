package com.monitor.bankendmonitoreoLinks.components;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.monitor.bankendmonitoreoLinks.components.implement.AdCreativeImp;
import com.monitor.bankendmonitoreoLinks.components.implement.AnuncioImp;
import com.monitor.bankendmonitoreoLinks.components.implement.CuentaFBImp;

import com.monitor.bankendmonitoreoLinks.components.implement.EstadoAnuncioImp;

import com.monitor.bankendmonitoreoLinks.dao.IApiMarketing;

import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;

import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

@Component
public class ApiMarketing implements IApiMarketing {

	private CuentaFBImp cuentaFBImp = new CuentaFBImp();



	@Override
	public ArrayList<String> ConsultaCuentasAsociadas() {

		/*ArrayList<String> cuentas = CuentaFBImp.obtenerCuentas();
		String cuentas2 = cuentaFBImp.obtenerObjetosDeCuentas();
		System.out.println("cuentas" + cuentas2);
		return cuentas;*/
		return null;

	}

	public List<Anuncio> ObteneryGuardarAnuncios() {

		// cuentaFBImp.guardarCuentas();

		List<Anuncio> anuncios = new AnuncioImp().obtenerAnunciosInf();

		return anuncios;
	}

	public List<EstadoAnuncio> obteneryGuardarEstadoAnuncios() {

		EstadoAnuncioImp estadoAnuncioImp = new EstadoAnuncioImp();

		List<EstadoAnuncio> estados = new ArrayList<>();
		List<Anuncio> anuncios = new AnuncioImp().listarAnuncios();
		for (Anuncio ads : anuncios) {
			EstadoAnuncio estadoAnuncio = new EstadoAnuncio();

			estados.add(estadoAnuncio);
			boolean ifExists = estadoAnuncioImp.verificarSiExisteEstadoAnuncio(ads.getIdAnuncio());
			if (ifExists == false) {
				estadoAnuncioImp.guardar(estadoAnuncio, ads);

			}

			else
				System.out.println("Ya existe se debe actualizar estado");
		}
		return estados;

	}

	public static void main(String[] args) {

	}

	public void main() {
		AdCreativeImp adCreativeImp = new AdCreativeImp();
		adCreativeImp.obtenerAdCreativesInf();
		ObteneryGuardarAnuncios();

		obteneryGuardarEstadoAnuncios();

	}

}
