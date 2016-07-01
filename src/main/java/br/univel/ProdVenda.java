package br.univel;

import java.math.BigDecimal;

public class ProdVenda {
	private int idVenda;
	private int CodPro;
	private String DescPro;
	private int Qtd;
	private BigDecimal total;

	public String getDescPro() {
		return DescPro;
	}

	public void setDescPro(String descPro) {
		DescPro = descPro;
	}


	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getCodPro() {
		return CodPro;
	}

	public void setCodPro(int codPro) {
		CodPro = codPro;
	}

	public int getQtd() {
		return Qtd;
	}

	public void setQtd(int qtd) {
		Qtd = qtd;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
