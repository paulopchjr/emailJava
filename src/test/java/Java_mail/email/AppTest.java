package Java_mail.email;

import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private String emailDestino = "";
	private String nome = "";

	@Test
	public void testeEnviarEmail() throws Exception {
		ObjetoEnviaEmail email = new ObjetoEnviaEmail("paulocezarhenriquejunior1990@gmail.com", "PAulo Cezar",
				"Muda de vida", "muda de vida");
		email.enviarEmail(false);

	}

	@Test
	public void listaTeste() throws Exception {

		String nPessoas = JOptionPane.showInputDialog("Quantas pessoas você quer mandar um email?");

		int[] posicoesPessoa = new int[Integer.parseInt(nPessoas)];

		// String op = "";
		int controle = 1;
		for (int i = 0; i < posicoesPessoa.length; i++) {

			String emailD = JOptionPane.showInputDialog("Informe o email do destinatario " + (i + 1));
			String x = "";
			if (controle < posicoesPessoa.length) {
				x = ",";
				controle++;

			}
			emailDestino = emailD + x;

			ObjetoEnviaEmail email = new ObjetoEnviaEmail(emailDestino, "Paulo Junior", "Novidades no PCHJR TECNOLOGIA",
					"UHAUHSUHAHUSHAUHSUH");

			email.enviarEmail(false);
		}

		JOptionPane.showMessageDialog(null, "Emails enviados");

	}

	@Test
	public void EmailJavaHtml() throws Exception {
		StringBuilder txtEmail = new StringBuilder();
		txtEmail.append("Olá, <strong>Pequeno Gafanhoto!<br></strong>");
		txtEmail.append(
				"Nós da loja <span style=\"color: #f00;\">PCHJR</span> preparamos uma oferta especial para voce! <br>");
		txtEmail.append("<b>Para mais  informações </b><a href=\"https://www.google.com.br/\">Clique aqui</a>");
		String emailDes = JOptionPane.showInputDialog("Informe o email");
		ObjetoEnviaEmail email = new ObjetoEnviaEmail(emailDes, "Paulo", "Novidades na LOJA PCHJR TEC",
				txtEmail.toString());
		email.enviarEmail(true);
	}

}
