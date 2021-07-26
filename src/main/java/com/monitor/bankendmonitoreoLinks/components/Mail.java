package com.monitor.bankendmonitoreoLinks.components;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public class Mail {
	private final Properties properties = new Properties();

	private static Session session;

	private void init() {

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.mail.sender", "pruebasdesarrollodeeploy@gmail.com");
		properties.put("mail.smtp.user", "pruebasdesarrollodeeploy");
		properties.put("mail.smtp.password", "alissa1995*");
		properties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(properties);
	}

	public void sendEmail(ArrayList<String> correos, String asunto, String cuerpo, String fecha,
			EstadoAnuncio estadoAnuncio) {

		String h1 = "Se Reporta Link Caido";
		String BODY = String.join(System.getProperty("line.separator"), "<!doctype html>", "<html lang='es'>", "<head>",

				"</head>", "<h1>" + h1 + "</h1>", "<p>Se reporta que el link esta caido desde las" + fecha,
				"<table style='border:2px solid black; >",

				"<thead>", "<tr style=\"background:black\">", "<th scope='col'>Link</th>",
				"<th scope='col'>Anuncio</th>", "<th scope='col'>Fecha</th>", "<th scope='col'>Status</th>",

				"</tr>", "</thead>", "<tbody>", "<tr>",
				"<td>" + estadoAnuncio.getAnuncio().getAdCreative().getLink() + "</td>",
				"<td>" + estadoAnuncio.getAnuncio().getIdAnuncio() + "</td>", "<td>" + fecha + "</td>",
				"<td>" + estadoAnuncio.getCode() + estadoAnuncio.getMensaje() + "</td>", "</tr>",

				"</tbody>", "</table>",
				"<img src='https://static1.squarespace.com/static/5ce88653d84296000124515a/t/5ce93ca6ee6eb002e7b992ef/1614802124137/?format=1500w'>",
				" for <a href='https://deeploy.co'>Deeploy</a>.");

		init();
		try {

			Multipart multipart = new MimeMultipart();
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
			for (String email : correos) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			}
			correos.add("johan.jara@deeploy.co");
			correos.add("pruebasdesarrollodeeploy@gmail.com");

			message.setSubject(asunto);
			message.setText(cuerpo.toString() + fecha);
			message.setContent(multipart);

			message.setContent(BODY, "text/html");

			Transport t = session.getTransport("smtp");
			t.connect((String) properties.get("mail.smtp.user"), session.getProperty("mail.smtp.password"));
			t.sendMessage(message, message.getAllRecipients());
			System.out.println("mensaje enviado");
			t.close();
		} catch (MessagingException me) {
			System.err.println("eror" + me);
			return;
		}

	}

	public static void main(String[] args) {

	}

}
