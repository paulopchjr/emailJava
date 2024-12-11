package Java_mail.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {

	private String email = "paulopjr1990@gmail.com";
	private String senha = "ddxr dzcf kjof apgd";

	private String listaDestinatarios = "";

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

		if (emailhtml == true) {
			message.setContent(texttoEmail, "text/html; charset=utf-8");
		} else {
			message.setText(texttoEmail);
		}

		Transport.send(message);

	}

	public void enviarEmailAnexoPDf(boolean emailhtml) throws Exception {

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

		MimeBodyPart corpoEmail = new MimeBodyPart();

		if (emailhtml == true) {
			corpoEmail.setContent(texttoEmail, "text/html; charset=utf-8");
		} else {
			corpoEmail.setText(texttoEmail);
		}

		// parte 2 do emailque sao anexos
		MimeBodyPart anexoEmail = new MimeBodyPart();
		anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorPDF(), "apllication/pdf")));
		anexoEmail.setFileName("arquivo.pdf");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		multipart.addBodyPart(anexoEmail);

		message.setContent(multipart);

		Transport.send(message);

	}

	private FileInputStream simuladorPDF() throws IOException, DocumentException {

		Document document = new Document();
		File file = new File("arquivo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("<style=\"darkred\">Minha Bella, que saudades ♥"));
		document.close();

		return new FileInputStream(file);

	}

	public void enviarListaEmailAnexoPDf(boolean emailhtml) throws Exception {

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true"); /* Autorização */
		properties.put("mail.smtstarttls", "true"); /* Autenticação */
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

		MimeBodyPart corpoEmail = new MimeBodyPart();

		if (emailhtml == true) {
			corpoEmail.setContent(texttoEmail, "text/html; charset=utf-8");
		} else {
			corpoEmail.setText(texttoEmail);
		}

		String qtd = JOptionPane.showInputDialog("Quantos arquivos no email?");

		List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
		for (int i = 0; i < Integer.parseInt(qtd); i++) {
			arquivos.add(simuladorPDF());

		}

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);

		int index = 0;
		for (FileInputStream fileInputStream : arquivos) {
			// parte 2 do emailque sao anexos
			MimeBodyPart anexoEmail = new MimeBodyPart();
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "apllication/pdf")));
			anexoEmail.setFileName("arquivo" + index + ".pdf");
			multipart.addBodyPart(anexoEmail);

			index++;

		}

		message.setContent(multipart);

		Transport.send(message);

	}

}
