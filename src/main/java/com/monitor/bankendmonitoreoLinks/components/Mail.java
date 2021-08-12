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

import org.apache.log4j.Logger;

import com.monitor.bankendmonitoreoLinks.entity.monitor.EstadoAnuncio;

public class Mail {
	
	Log logObject= new Log("logs");
	Logger log = logObject.getLogger();
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

		String BODY = String.join(System.getProperty("line.separator"), "<html>", "<head>",

				"<html>\r\n" + "  <head>\r\n" + "\r\n" + "    </head>\r\n" + "  <body>\r\n"
						+ "    <table border='1' cellspacing='10' cellpadding='10' width='auto'\">\r\n"
						+ "      <tr>\r\n"
						+ "          <td colspan='2' align='center' bgcolor='#B72222'><strong>Informe de Link Caído</strong></td>\r\n"
						+ "      </tr>\r\n" + "\r\n" + "    <tr>\r\n" + "      <th bgcolor='#198754'>Item</th>\r\n"
						+ "      <th bgcolor='#198754'>Descripción</th>\r\n" + "    </tr>\r\n" + "\r\n"
						+ "    <tbody>\r\n" + "        <tr>\r\n" + "          <td>Anuncio</td>\r\n" + "          <td>"
						+ estadoAnuncio.getAnuncio().getIdAnuncio() + "</td>\r\n" + "        </tr>\r\n"
						+ "        <tr>\r\n" + "          <td>Link</td>\r\n" + "          <td bgcolor='#B72222'>"
						+ estadoAnuncio.getAnuncio().getAdCreative().getLink() + "</td>\r\n" + "        </tr>\r\n"
						+ "        <tr>\r\n" + "          <td>Link</td>\r\n" + "          <td>"
						+ estadoAnuncio.getAnuncio().getPreview_shareable_link() + "</td>\r\n" + "        </tr>\r\n"
						+ "        <tr>\r\n" + "          <td>Cuenta Facebook</td>\r\n" + "          <td>"
						+ estadoAnuncio.getAnuncio().getCuentaFB().getNombreCuenta() + "</td>\r\n" + "        </tr>\r\n"
						+ "        <tr>\r\n" + "          <td>Fecha y Hora</td>\r\n" + "          <td>" + fecha
						+ "</td>\r\n" + "        </tr>\r\n" + "        <tr>\r\n" + "          <td>Status</td>\r\n"
						+ "          <td>" + estadoAnuncio.getCode() + " - " + estadoAnuncio.getMensaje() + "</td>\r\n"
						+ "        </tr>\r\n" + "\r\n" + "\r\n" + "    </tbody>\r\n" + "    </table>\r\n"
						+ "    <br/>\r\n"
						+ "    <img src='https://static1.squarespace.com/static/5ce88653d84296000124515a/t/5ce93ca6ee6eb002e7b992ef/1614802124137/?format=1500w' width='350px' >\r\n"
						+ "  </body>\r\n" + "</html>");

		init();
		try {

			Multipart multipart = new MimeMultipart();
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
			for (String email : correos) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			}

			message.setSubject(asunto);
			message.setText(cuerpo.toString() + fecha);
			message.setContent(multipart);

			message.setContent(BODY, "text/html;charset=utf-8");

			Transport t = session.getTransport("smtp");
			t.connect((String) properties.get("mail.smtp.user"), session.getProperty("mail.smtp.password"));
			t.sendMessage(message, message.getAllRecipients());
			System.out.println("mensaje enviado");
			t.close();
		} catch (MessagingException me) {
			System.err.println("error" + me);
			log.error("Error al enviar email"+me);
			
			return;
		}

	}

}
