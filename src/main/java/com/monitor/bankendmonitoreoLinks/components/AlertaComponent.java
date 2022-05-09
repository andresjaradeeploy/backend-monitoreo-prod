package com.monitor.bankendmonitoreoLinks.components;

import java.util.ArrayList;

import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;
import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoLinkExterno;
import com.monitor.bankendmonitoreoLinks.service.SendMailService;

public class AlertaComponent {

	private SendMailService mailService = new SendMailService();

	public void enviarAlertaDown(ArrayList<String> correo, String asunto, String cuerpo, String fecha,
		EstadoAnuncio estadoAnuncio) {
		Mail mail = new Mail();
		mail.sendEmail(correo, asunto, cuerpo, fecha, estadoAnuncio);
	}
	
	public void enviarAlertaDownExterno(ArrayList<String> correo, String asunto, String cuerpo, String fecha,EstadoLinkExterno estadoLinkExterno) {
			Mail mail = new Mail();
			mail.sendEmailExterno(correo, asunto, cuerpo, fecha, estadoLinkExterno);
		}
	
	public void enviarAlertaUp(ArrayList<String> correo, String asunto, String cuerpo, String fecha,Integer code,
			EstadoAnuncio estadoAnuncio) {
		MailUp mail = new MailUp();
		mail.sendEmail(correo, asunto, cuerpo, fecha, code,estadoAnuncio);
	}
	
	public void enviarAlertaUpExterno(ArrayList<String> correo, String asunto, String cuerpo, String fecha,Integer code,
			EstadoLinkExterno estadoLinkExterno) {
		MailUp mail = new MailUp();
		mail.sendEmailExterno(correo, asunto, cuerpo, fecha, code,estadoLinkExterno);
	}

	public String mensajeAlerta() {

		String alerta = "Enviando Correo de alerta";

		return alerta;
	}

	public SendMailService getMailService() {
		return mailService;
	}

	public void setMailService(SendMailService mailService) {
		this.mailService = mailService;
	}

}
