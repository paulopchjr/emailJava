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
		email.enviarEmail();

	}

	@Test
	public void listaTeste() throws Exception {

		String nPessoas = JOptionPane.showInputDialog("Quantas pessoas você quer mandar um email?");

		int[] posicoesPessoa = new int[Integer.parseInt(nPessoas)];

		//String op = "";
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

			email.enviarEmail();
		}

		JOptionPane.showMessageDialog(null, "Emails enviados");

	}

}
