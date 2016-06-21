package br.univel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class ParserProduto {

	private NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));

	public List<Produto> getProduto(List<String> listaStr) {
		List<Produto> listaPrd = new ArrayList<>();

		Pattern p = Pattern.compile("[0-9]+.*");

		listaStr.forEach(e -> {

			if (!e.startsWith("----")) {
					listaPrd.add(getProduto(e));

			}

		});

		return listaPrd;
	}

	private Produto getProduto(String str) {
		//MathContext mc = new MathContext(1);

		String[] itens = str.split(";");
		int id = Integer.parseInt(itens[0]);
		String descricao = itens[1].trim();
		BigDecimal preco = itens[2].trim();


		Produto c = new Produto(id, descricao, preco);
		return c;

	}

}