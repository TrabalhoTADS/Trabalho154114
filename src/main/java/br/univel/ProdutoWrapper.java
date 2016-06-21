package br.univel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listaprodutos")
public class ProdutoWrapper {


		private List<Produto> produtos;

		public List<Produto> getProduto() {
			return produtos;
		}

		public void setProduto(List<Produto> produtos) {
			this.produtos = produtos;
		}
	}
