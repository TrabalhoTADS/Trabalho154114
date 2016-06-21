package br.univel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProdutoModel extends AbstractTableModel {

	private List<Produto> lista;

	public ProdutoModel(List<?> lista) {
		this.lista = (List<Produto>) lista;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Produto a = lista.get(row);

		switch (col) {
			case 0:
				return a.getId();
			case 1:
				return a.getDescricao();
			case 2:
				return a.getPreco();
		}

		return null;
	}

	public Produto geProdutoNaLinha(int index) {
		return lista.get(index);
	}

	public void removerProduto(Produto c) {
		int idx = this.lista.indexOf(c);
		this.lista.remove(c);
		super.fireTableRowsDeleted(idx, idx);
	}

	public void adicionarProduto(Produto c) {
		this.lista.add(c);
		super.fireTableRowsInserted(lista.size() - 1, lista.size() - 1);

	}

}