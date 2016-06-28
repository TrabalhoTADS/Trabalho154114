package br.univel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProdVendaModel extends AbstractTableModel {

	private List<ProdVenda> lista;

	public ProdVendaModel(List<?> lista) {
		this.lista = (List<ProdVenda>) lista;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	public List<ProdVenda> getLista() {
		return lista;
	}

	public void setLista(List<ProdVenda> lista) {
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		ProdVenda c = lista.get(row);

		switch (col) {
		case 0:
			return c.getCodPro();
		case 1:
			return c.getDescPro();
		case 2:
			return c.getQtd();
		case 3:
			return c.getTotal();
		}

		return null;
	}

	public ProdVenda getProdVendaNaLinha(int index) {
		return lista.get(index);
	}

	public void removerProdVenda(ProdVenda c) {
		int idx = this.lista.indexOf(c);
		this.lista.remove(c);
		super.fireTableRowsDeleted(idx, idx);
	}

	public void adicionarProdVenda(ProdVenda c) {
		this.lista.add(c);
		super.fireTableRowsInserted(lista.size() - 1, lista.size() - 1);

	}

}
