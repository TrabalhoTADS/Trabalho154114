package br.univel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class parserCliente {

	private NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));

	public List<Cliente> getCliente(List<String> listaStr) {
		List<Cliente> listaPrd = new ArrayList<>();

		Pattern p = Pattern
				.compile("[0-9]+.*"); /*
										 * tenha no minimo 1 digito + qualquer
										 * caracter + 0 ou mais coisas
										 */

		listaStr.forEach(e -> {

			if (!e.startsWith("----")) {
				Matcher m = p.matcher(e);
				if (m.matches()) {
					listaPrd.add(getCliente(e));
				}
			}

		});

		return listaPrd;
	}

	private Cliente getCliente(String str) {

		String[] itens = str.split("|");
		JOptionPane.showMessageDialog(null, itens[1]);
		int id = Integer.parseInt(itens[0]);
		String nome = itens[1];
		String endereco = itens[2];
		int numero = Integer.parseInt(itens[3]);
		String complemento = itens[4];
		String bairro = itens[5];
		String cidade = itens[6];
		String estado = itens[7];
		int cep = Integer.parseInt(itens[8]);
		String telefone = itens[9];
		String celular = itens[10];

		/*
		 * int indexPrimeiroEspaco = str.indexOf(' '); String subStringCodigo =
		 * str.substring(0, indexPrimeiroEspaco); int id =
		 * Integer.parseInt(subStringCodigo);
		 */

		Cliente c = new Cliente(id, nome, endereco, numero, complemento, bairro, cidade, estado, cep, telefone,
				celular);
		return c;

	}

}
