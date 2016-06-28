package br.univel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

//import br.univel.exemplos.tabela.Cliente;

public class ClienteModel extends AbstractTableModel {

	private List<Cliente> lista;

	public ClienteModel(List<?> lista) {
		this.lista = (List<Cliente>) lista;
	}

	@Override
	public int getColumnCount() {
		return 11;
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Cliente c = lista.get(row);

		switch (col) {
			case 0:
				return c.getId();
			case 1:
				return c.getNome();
			case 2:
				return c.getEndereco();
			case 3:
				return c.getNumero();
			case 4:
				return c.getComplemento();
			case 5:
				return c.getBairro();
			case 6:
				return c.getCidade();
			case 7:
				return c.getEstado();
			case 8:
				return c.getCep();
			case 9:
				return c.getTelefone();
			case 10:
				return c.getCelular();
		}

		return null;
	}

	public Cliente getClienteNaLinha(int index) {
		return lista.get(index);
	}

	public void removerCliente(Cliente c) {
		int idx = this.lista.indexOf(c);
		this.lista.remove(c);
		super.fireTableRowsDeleted(idx, idx);
	}

	public void adicionarCliente(Cliente c) {
		this.lista.add(c);
		super.fireTableRowsInserted(lista.size() - 1, lista.size() - 1);

	}

}
