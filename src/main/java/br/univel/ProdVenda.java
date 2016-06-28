package br.univel;

public class ProdVenda {
	private int idVenda;
	private int CodPro;
	private String DescPro;
	public String getDescPro() {
		return DescPro;
	}
	public void setDescPro(String descPro) {
		DescPro = descPro;
	}
	private int Qtd;
	private double total;

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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
