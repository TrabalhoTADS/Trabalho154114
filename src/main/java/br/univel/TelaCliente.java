package br.univel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class TelaCliente extends TelaPrincipal {

	private JTable table;
	private List<Cliente> listaCliente;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 12, 12, 12, 12, 12 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnNewButton = new JButton("Preenche");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencheTabela();
				File xml = new File("C:\\Users\\Eduardo\\git\\Trabalho154114\\ListaClientes.xml");
				SalvarClienteXML(xml);
				LerClienteXML(xml);
				ClienteModel model = new ClienteModel(listaCliente);
				table.setModel(model);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		/*
		 * JButton btnRemove = new JButton("Remove");
		 * btnRemove.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { removerSelecionado(); } });
		 * GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		 * gbc_btnRemove.insets = new Insets(0, 0, 5, 0); gbc_btnRemove.gridx =
		 * 0; gbc_btnRemove.gridy = 1; contentPane.add(btnRemove,
		 * gbc_btnRemove);
		 */

		JButton btnAdiciona = new JButton("Adiciona");

		GridBagConstraints gbc_btnAdiciona = new GridBagConstraints();
		gbc_btnAdiciona.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdiciona.gridx = 1;
		gbc_btnAdiciona.gridy = 0;
		contentPane.add(btnAdiciona, gbc_btnAdiciona);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable() {

			@Override
			public String getToolTipText(MouseEvent e) {

				String tip = null;

				Point p = e.getPoint();

				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);

				if (rowIndex == -1 || colIndex == -1) {
					return null;
				}

				try {
					tip = getValueAt(rowIndex, colIndex).toString();
				} catch (RuntimeException e1) {

				}

				return tip;

			}

		};

		scrollPane.setViewportView(table);

		// preencheTabela();

		// final
		// configuraTabela();
	}

	/*
	 * protected void removerSelecionado() { Produto c =
	 * getProdutoSelecionadoNaTabela(); if (c != null) { ((ProdutoModel)
	 * table.getModel()).removerProduto(c); }
	 *
	 * }
	 */

	/*
	 * private void configuraTabela() { table.addMouseListener(new
	 * MouseAdapter() {
	 *
	 * @Override public void mouseClicked(MouseEvent e) {
	 *
	 * if (e.getClickCount() == 2) { Produto c =
	 * getProdutoSelecionadoNaTabela(); if (c != null) {
	 * JOptionPane.showMessageDialog(null, "Produto: " + c.toString()); }
	 *
	 * } }
	 *
	 * });
	 *
	 * }
	 */

	protected void preencheTabela() {

		/*
		 * ReaderURL reader = new ReaderURL(); List<String> lista =
		 * reader.lerUrl();
		 */
		ReaderArquivo reader = new ReaderArquivo();
		List<String> lista = reader.lerArquivo("C:\\Users\\Eduardo\\git\\Trabalho154114\\listaclientes.txt");

		parserCliente parser = new parserCliente();
		listaCliente = parser.getCliente(lista);

		ClienteModel model = new ClienteModel(listaCliente);
		table.setModel(model);

	}

	/*
	 * private Cliente getProdutoSelecionadoNaTabela() { Cliente c = null; int
	 * index = table.getSelectedRow(); if (index == -1) {
	 * JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada!"); } else
	 * { c = ((ClienteModel) table.getModel()).getProdutoNaLinha(index); }
	 * return c; }
	 */

	public void SalvarClienteXML(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ClienteWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ClienteWrapper wrapper = new ClienteWrapper();
			wrapper.setClientes(listaCliente);

			m.marshal(wrapper, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void LerClienteXML(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ClienteWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			ClienteWrapper wrapper = (ClienteWrapper) um.unmarshal(file);

			listaCliente.clear();

			listaCliente.addAll(wrapper.getClientes());

		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
		}
	}

}
