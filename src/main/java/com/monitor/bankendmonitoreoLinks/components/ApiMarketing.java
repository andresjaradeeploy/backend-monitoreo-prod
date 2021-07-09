package com.monitor.bankendmonitoreoLinks.components;

import java.util.ArrayList;
import java.util.List;
import java.net.MalformedURLException;
import org.springframework.stereotype.Component;

import com.monitor.bankendmonitoreoLinks.components.implement.AnuncioImp;
import com.monitor.bankendmonitoreoLinks.components.implement.CuentaFBImp;
import com.monitor.bankendmonitoreoLinks.components.implement.CuentaFbDeveloperImp;
import com.monitor.bankendmonitoreoLinks.components.implement.EstadoAnuncioImp;
import com.monitor.bankendmonitoreoLinks.components.implement.FacebookImp;
import com.monitor.bankendmonitoreoLinks.components.implement.MonitorImp;
import com.monitor.bankendmonitoreoLinks.dao.IApiMarketing;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Anuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFB;
import com.monitor.bankendmonitoreoLinks.entity.monitor.CuentaFbDeveloper;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Estado;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.Monitor;

@Component
public class ApiMarketing implements IApiMarketing {

	private static CuentaFbDeveloper cuentaFbDeveloper = new CuentaFbDeveloper(1000001);
	private static CuentaFbDeveloperImp cuentaFbDeveloperImp = new CuentaFbDeveloperImp();
	private static FacebookImp FACEBOOK_IMP = new FacebookImp();
	private CuentaFBImp cuentaFBImp = new CuentaFBImp();
	private static final String concatenar = "?";
	private static final String enlaceField = "%2C";
	private static final String name = enlaceField + "name";
	private static final String tracking_specs = enlaceField + "tracking_specs";
	private static final String preview_shareable = enlaceField + "preview_shareable";
	private static final String effective_status = enlaceField + "effective_status";
	private static final String campaign = enlaceField + "campaign";

	@Override
	public ArrayList<String> ConsultaCuentasAsociadas() {
				
		ArrayList<String> cuentas=CuentaFBImp.obtenerCuentas();
		String cuentas2=cuentaFBImp.obtenerObjetosDeCuentas();
		System.out.println("cuentas"+cuentas2);
		return cuentas;

	}

	public List<Anuncio> ObteneryGuardarCuentasFByAnuncios() {
		
		 cuentaFBImp.guardarCuentas();
				
		List<Anuncio> anuncios = new AnuncioImp().obtenerAnunciosInf();
		 /*for (Anuncio model : anuncios) {
	            System.out.println(model.getNombre());
	            System.out.println(model.getIdAnuncio());
	            System.out.println(model.getCuentaFB().getIdCuenta());
	        }*/
		
		 	return anuncios;
	}
	
	public List<EstadoAnuncio> obteneryGuardarEstadoAnuncios() {
		
		EstadoAnuncioImp estadoAnuncioImp = new EstadoAnuncioImp();
		Monitor monitor= new Monitor();
		monitor.setIdMonitor(1);
		
		
		List<EstadoAnuncio> estados = new ArrayList<>();
		List<Anuncio> anuncios = new AnuncioImp().listarAnuncios();
		 for (Anuncio model : anuncios) {
			 EstadoAnuncio estadoAnuncio= new EstadoAnuncio();
			 
			 			 
			 
			 estados.add(estadoAnuncio);
			 boolean ifExists= estadoAnuncioImp.verificarSiExisteEstadoAnuncio(model.getIdAnuncio());
			 if(ifExists==false)
				 
			 estadoAnuncioImp.guardar(estadoAnuncio,model);
			 
			 else
				 System.out.println("Ya existe se debe actualizar estado");
	        }
		return estados;
		
	}
	
	public List<Monitor> guardarMonitores(){
		List<Monitor> monitores = new MonitorImp().listarMonitores();
		MonitorImp imp= new MonitorImp();
		
		for (Monitor monitor : monitores) {
			boolean ifExists= imp.verificarSiExisteMonitor(monitor.getIdMonitor());
					
			
			imp.guardar();
					
		}
		
		
		
		return monitores;
		
	}
	
	/*public static void main(String[] args) {
		ApiMarketing apiMarketing= new ApiMarketing();
		apiMarketing.ConsultaCuentasAsociadas();
	}*/
	
	public void main()
	{
		ObteneryGuardarCuentasFByAnuncios();
		obteneryGuardarEstadoAnuncios();
	}

}
