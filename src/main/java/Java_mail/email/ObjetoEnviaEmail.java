package Java_mail.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ObjetoEnviaEmail {

	private String email = "paulopjr1990@gmail.com";
	private String senha = "ddxr dzcf kjof apgd";

	private String listaDestinatarios="";

	private String remetente = "";
	private String assuntoEmail = "";
	private String texttoEmail = "";

	public ObjetoEnviaEmail(String destinatarioslist, String nomeRemetente, String emailAssunto, String txtEmail) {

		this.listaDestinatarios = destinatarioslist;
		this.remetente = nomeRemetente;
		this.assuntoEmail = emailAssunto;
		this.texttoEmail = txtEmail;

	}

	public void enviarEmail(boolean emailhtml) throws Exception {

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true"); /* Autorização */
		properties.put("mail.smtp.starttls", "true"); /* Autenticação */
		properties.put("mail.smtp.host", "smtp.gmail.com");/* Servidor gmail Google */
		properties.put("mail.smtp.port", "465");
		properties.put("mai.smtp.socketFactory.port", "465");/* Porta ser conectada pelo do soket */
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); /* classe Socket de conexao SMTP */

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, senha);
			}
		});

		Address[] toUser = InternetAddress.parse(listaDestinatarios);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email, remetente));/* Quem está enviando */
		message.setRecipients(Message.RecipientType.TO, toUser); /* quem está recebendod */
		message.setSubject(assuntoEmail);/* assunto do email */
		
		if(emailhtml == true) {
			message.setContent(texttoEmail, "text/html; charset=utf-8");
		}else {
			message.setText(texttoEmail);
		}

		Transport.send(message);

	}

}
