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

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private String email = "paulopjr1990@gmail.com";
	private String senha = "ddxr dzcf kjof apgd";

	@Test
	public void TesteJava() {
		try {

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
			
			Address[] toUser = InternetAddress.parse("paulopchjr@gmail.com");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Chegou o email enviado com Java");
			message.setText("  \r\n"
					+ "What is Lorem Ipsum?\r\n"
					+ "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\r\n"
					+ "\r\n"
					+ "Why do we use it?\r\n"
					+ "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\r\n"
					+ "\r\n"
					+ "");
			
			Transport.send(message);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			System.out.println("deu ruim: "+ e.getMessage());
		}

	}
}
