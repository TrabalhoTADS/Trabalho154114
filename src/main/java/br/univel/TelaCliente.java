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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class TelaCliente  {

	private JTable table;
	private List<Cliente> listaCliente;
	private String arquivo = "ListaClientes";

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
		gbl_contentPane.rowHeights = new int[] { 12, 12, 12, 12, 12, 12, 12};
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnNewButton = new JButton("Preenche");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencheTabela(new File(arquivo + ".txt"));
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		JButton btnLerXml = new JButton("Ler Xml");
		btnLerXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setListaCliente(LerClienteXML(new File(arquivo + ".xml")));
			}
		});
		GridBagConstraints gbc_btnLerXml = new GridBagConstraints();
		gbc_btnLerXml.insets = new Insets(0, 0, 5, 0);
		gbc_btnLerXml.gridx = 0;
		gbc_btnLerXml.gridy = 1;
		contentPane.add(btnLerXml, gbc_btnLerXml);

		JButton btnSalvarrXml = new JButton("Salvar Xml");
		btnSalvarrXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalvarClienteXML(new File(arquivo + ".xml"));
			}
		});
		GridBagConstraints gbc_btnSalvarrXml = new GridBagConstraints();
		gbc_btnSalvarrXml.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvarrXml.gridx = 0;
		gbc_btnSalvarrXml.gridy = 2;
		contentPane.add(btnSalvarrXml, gbc_btnSalvarrXml);

		JButton btnLerSerial = new JButton("Ler Serializacao");
		btnLerSerial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setListaCliente(LerClienteSerializacao(new File(arquivo + ".xml")));
			}
		});
		GridBagConstraints gbc_btnLerSerial = new GridBagConstraints();
		gbc_btnLerSerial.insets = new Insets(0, 0, 5, 0);
		gbc_btnLerSerial.gridx = 0;
		gbc_btnLerSerial.gridy = 3;
		contentPane.add(btnLerSerial, gbc_btnLerSerial);

		JButton btnExpSerial = new JButton("Exporta Serializacao");
		btnExpSerial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalvarClienteSerializacao(new File(arquivo + ".xml"));
			}
		});
		GridBagConstraints gbc_btnExpSerial = new GridBagConstraints();
		gbc_btnExpSerial.insets = new Insets(0, 0, 5, 0);
		gbc_btnExpSerial.gridx = 0;
		gbc_btnExpSerial.gridy = 4;
		contentPane.add(btnExpSerial, gbc_btnExpSerial);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
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

	public void preencheTabela(File file) {

		/*
		 * ReaderURL reader = new ReaderURL(); List<String> lista =
		 * reader.lerUrl();
		 */
		ReaderArquivo reader = new ReaderArquivo();
		List<String> lista = reader.lerArquivo(file);

		ParserCliente parser = new ParserCliente();
		setListaCliente(parser.getCliente(lista));

		ClienteModel model = new ClienteModel(getListaCliente());
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
			wrapper.setList(getListaCliente());

			m.marshal(wrapper, file);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro na exportacao!");
		}
	}

	public List<Cliente> LerClienteXML(File file) {
		List <Cliente> _list = null;
		try {
			JAXBContext context = JAXBContext.newInstance(ClienteWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			ClienteWrapper wrapper = (ClienteWrapper) um.unmarshal(file);

			if (_list != null)
				_list.clear();
			else
				_list = new ArrayList<>();

			_list.addAll(wrapper.getList());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Arquivo vazio ou nao encontrado!");
			//e.printStackTrace();
		}

		return _list;
	}

	public void SalvarClienteSerializacao(File file) {
		try {

			FileOutputStream fout = new FileOutputStream(file);

			ObjectOutputStream oos = new ObjectOutputStream(fout);

			oos.writeObject(getListaCliente());

			oos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<Cliente> LerClienteSerializacao(File file) {
		List <Cliente> _list = null;
		try {

			FileInputStream fin = new FileInputStream(file);

			ObjectInputStream ois = new ObjectInputStream(fin);

			if (_list != null)
				_list.clear();
			else
				_list = new ArrayList<>();

			_list = (List<Cliente>) ois.readObject();

			ois.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Arquivo vazio ou nao encontrado!");
			ex.printStackTrace();
		}

		return _list;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
}
