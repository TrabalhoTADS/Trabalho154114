package br.univel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JTable table;

	private List<Produto> listaProduto;

	private String ABA_UM = "Produtos";
	private String ABA_DOIS = "Clientes";
	private String ABA_TRES = "Vendas";
	private NumberField tfCodigo;
	private NumberField tfPreco;
	private JTextField tfDesc;
	private JScrollPane PainelProdutos;
	private JScrollPane PainelVendas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 3;
		gbc_tabbedPane.insets = new Insets(0, 0, 0, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		contentPane.add(tabbedPane, gbc_tabbedPane);

		adicionaProdutos();
		adicionaClientes();
		adicionaVendas();

		tabbedPane.setSelectedIndex(0);
	}

	protected void adicionaProdutos() {

		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (tabbedPane.getTitleAt(i).equals(ABA_UM)) {
				tabbedPane.setSelectedIndex(i);
				return;
			}
		}

		mostraUltima();

	}

	protected void adicionaClientes() {

		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (tabbedPane.getTitleAt(i).equals(ABA_DOIS)) {
				tabbedPane.setSelectedIndex(i);
				return;
			}
		}

		JPanel telaClientes = new JPanel();

		tabbedPane.addTab(ABA_DOIS, telaClientes);
		GridBagLayout gbl_telaClientes = new GridBagLayout();
		gbl_telaClientes.columnWidths = new int[] { 75, 95, 65, 25, 67, 3, 97, 75, 0 };
		gbl_telaClientes.rowHeights = new int[] { 23, 23, 0, 0, 0, 0, 0, 0 };
		gbl_telaClientes.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_telaClientes.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		telaClientes.setLayout(gbl_telaClientes);

		JButton button = new JButton("Adcionar");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTHWEST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 6;
		gbc_button.gridy = 0;
		telaClientes.add(button, gbc_button);

		JButton button_1 = new JButton("Importar TXT");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 7;
		gbc_button_1.gridy = 0;
		telaClientes.add(button_1, gbc_button_1);

		JButton button_2 = new JButton("Alterar");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 6;
		gbc_button_2.gridy = 1;
		telaClientes.add(button_2, gbc_button_2);

		JButton button_3 = new JButton("Importar XML");
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_3.insets = new Insets(0, 0, 5, 0);
		gbc_button_3.gridwidth = 2;
		gbc_button_3.gridx = 7;
		gbc_button_3.gridy = 1;
		telaClientes.add(button_3, gbc_button_3);

		JScrollPane PainelClientes = new JScrollPane();
		GridBagConstraints gbc_PainelClientes = new GridBagConstraints();
		gbc_PainelClientes.gridheight = 5;
		gbc_PainelClientes.gridwidth = 5;
		gbc_PainelClientes.insets = new Insets(0, 0, 0, 5);
		gbc_PainelClientes.fill = GridBagConstraints.BOTH;
		gbc_PainelClientes.gridx = 0;
		gbc_PainelClientes.gridy = 2;
		telaClientes.add(PainelClientes, gbc_PainelClientes);

		JButton button_4 = new JButton("Exportar XML");
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_4.insets = new Insets(0, 0, 5, 5);
		gbc_button_4.gridx = 7;
		gbc_button_4.gridy = 2;
		telaClientes.add(button_4, gbc_button_4);

		JButton button_5 = new JButton("Serializar");
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_5.insets = new Insets(0, 0, 5, 5);
		gbc_button_5.gridx = 7;
		gbc_button_5.gridy = 3;
		telaClientes.add(button_5, gbc_button_5);

		JButton button_6 = new JButton("Desserializar");
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.anchor = GridBagConstraints.NORTHEAST;
		gbc_button_6.insets = new Insets(0, 0, 5, 5);
		gbc_button_6.gridx = 7;
		gbc_button_6.gridy = 4;
		telaClientes.add(button_6, gbc_button_6);

		JButton button_7 = new JButton("Relatorio");
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.insets = new Insets(0, 0, 5, 0);
		gbc_button_7.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_7.gridx = 7;
		gbc_button_7.gridy = 5;
		telaClientes.add(button_7, gbc_button_7);
		mostraUltima();
	}

	protected void adicionaVendas() {

		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (tabbedPane.getTitleAt(i).equals(ABA_TRES)) {
				tabbedPane.setSelectedIndex(i);
				return;
			}
		}
		JPanel telaProduto = new JPanel();

		tabbedPane.addTab(ABA_UM, telaProduto);
		GridBagLayout gbl_telaProduto = new GridBagLayout();
		gbl_telaProduto.columnWidths = new int[] { 30, 95, 65, 30, 67, 30, 97, 75, 30 };
		gbl_telaProduto.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_telaProduto.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_telaProduto.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		telaProduto.setLayout(gbl_telaProduto);

		JLabel lblCodigo = new JLabel("Codigo");
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.anchor = GridBagConstraints.EAST;
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo.gridx = 0;
		gbc_lblCodigo.gridy = 0;
		telaProduto.add(lblCodigo, gbc_lblCodigo);

		tfCodigo = new NumberField();
		GridBagConstraints gbc_tfCodigo = new GridBagConstraints();
		gbc_tfCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_tfCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCodigo.gridx = 1;
		gbc_tfCodigo.gridy = 0;
		telaProduto.add(tfCodigo, gbc_tfCodigo);
		tfCodigo.setColumns(10);

		JButton btnImportarTXT = new JButton("Importar TXT");
		btnImportarTXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preencheTabela();

				ProdutoModel model = new ProdutoModel(listaProduto);

				if (model != null)
					table.setModel(model);

			}
		});

		JButton btnAdcionar = new JButton("Adcionar");
		btnAdcionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!tfCodigo.getText().equals("") && !tfDesc.getText().equals("") && !tfPreco.getText().equals("")) {
					Produto p = new Produto();
					p.setId(Integer.parseInt(tfCodigo.getText()));
					p.setDescricao(tfDesc.getText());
					p.setPreco(new BigDecimal(tfPreco.getText()));

					if (listaProduto == null)
						listaProduto = new ArrayList<>();

					listaProduto.add(p);
				}

				/*
				 * tfCodigo.setText(Integer.toString(c.getId()));
				 * tfDesc.setText(c.getDescricao());
				 * tfPreco.setText(c.getPreco().toString());
				 */
			}
		});

		JLabel lblPreoUn = new JLabel("Preço Un");
		GridBagConstraints gbc_lblPreoUn = new GridBagConstraints();
		gbc_lblPreoUn.anchor = GridBagConstraints.EAST;
		gbc_lblPreoUn.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreoUn.gridx = 2;
		gbc_lblPreoUn.gridy = 0;
		telaProduto.add(lblPreoUn, gbc_lblPreoUn);

		tfPreco = new NumberField();
		GridBagConstraints gbc_tfPreco = new GridBagConstraints();
		gbc_tfPreco.gridwidth = 2;
		gbc_tfPreco.insets = new Insets(0, 0, 5, 5);
		gbc_tfPreco.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPreco.gridx = 3;
		gbc_tfPreco.gridy = 0;
		telaProduto.add(tfPreco, gbc_tfPreco);
		tfPreco.setColumns(10);
		GridBagConstraints gbc_btnAdcionar = new GridBagConstraints();
		gbc_btnAdcionar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdcionar.gridx = 6;
		gbc_btnAdcionar.gridy = 0;
		telaProduto.add(btnAdcionar, gbc_btnAdcionar);
		GridBagConstraints gbc_btnImportarTXT = new GridBagConstraints();
		gbc_btnImportarTXT.insets = new Insets(0, 0, 5, 0);
		gbc_btnImportarTXT.gridx = 7;
		gbc_btnImportarTXT.gridy = 0;
		telaProduto.add(btnImportarTXT, gbc_btnImportarTXT);

		JLabel lblDescrio = new JLabel("Descrição");
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.anchor = GridBagConstraints.EAST;
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrio.gridx = 0;
		gbc_lblDescrio.gridy = 1;
		telaProduto.add(lblDescrio, gbc_lblDescrio);

		tfDesc = new JTextField();
		GridBagConstraints gbc_tfDesc = new GridBagConstraints();
		gbc_tfDesc.insets = new Insets(0, 0, 5, 5);
		gbc_tfDesc.gridwidth = 4;
		gbc_tfDesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDesc.gridx = 1;
		gbc_tfDesc.gridy = 1;
		telaProduto.add(tfDesc, gbc_tfDesc);
		tfDesc.setColumns(10);

		JButton ImportarXML = new JButton("Importar XML");
		ImportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LerProdutoXML(new File("ListaProdutos.xml"));

				ProdutoModel model = new ProdutoModel(listaProduto);
				if (model != null)
					table.setModel(model);
			}
		});

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto c = getProdutoSelecionadoNaTabela();
				if (c != null) {
					tfCodigo.setText(Integer.toString(c.getId()));
					tfDesc.setText(c.getDescricao());
					tfPreco.setText(c.getPreco().toString());
				}

			}
		});
		GridBagConstraints gbc_btnAlterar = new GridBagConstraints();
		gbc_btnAlterar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAlterar.gridx = 6;
		gbc_btnAlterar.gridy = 1;
		telaProduto.add(btnAlterar, gbc_btnAlterar);
		GridBagConstraints gbc_ImportarXML = new GridBagConstraints();
		gbc_ImportarXML.insets = new Insets(0, 0, 5, 0);
		gbc_ImportarXML.gridx = 7;
		gbc_ImportarXML.gridy = 1;
		telaProduto.add(ImportarXML, gbc_ImportarXML);

		JScrollPane scrollPane;
		PainelProdutos = new JScrollPane();
		GridBagConstraints gbc_PainelProdutos = new GridBagConstraints();
		gbc_PainelProdutos.gridheight = 4;
		gbc_PainelProdutos.gridwidth = 5;
		gbc_PainelProdutos.insets = new Insets(0, 0, 0, 5);
		gbc_PainelProdutos.fill = GridBagConstraints.BOTH;
		gbc_PainelProdutos.gridx = 0;
		gbc_PainelProdutos.gridy = 2;
		telaProduto.add(PainelProdutos, gbc_PainelProdutos);

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

		PainelProdutos.setViewportView(table);

		JButton Desserializar = new JButton("Desserializar");
		Desserializar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LerProdutoSerializacao(new File("ListaProdutos.ser"));

				ProdutoModel model = new ProdutoModel(listaProduto);

				if (model != null)
					table.setModel(model);
			}
		});

		JButton ExportarXML = new JButton("Exportar XML");
		ExportarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SalvarProdutoXML(new File("ListaProdutos.xml"));
			}
		});
		GridBagConstraints gbc_ExportarXML = new GridBagConstraints();
		gbc_ExportarXML.insets = new Insets(0, 0, 5, 0);
		gbc_ExportarXML.gridx = 7;
		gbc_ExportarXML.gridy = 2;
		telaProduto.add(ExportarXML, gbc_ExportarXML);

		JButton Serializar = new JButton("Serializar");
		Serializar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalvarProdutoSerializacao(new File("ListaProdutos.ser"));
			}
		});
		GridBagConstraints gbc_Serializar = new GridBagConstraints();
		gbc_Serializar.insets = new Insets(0, 0, 5, 0);
		gbc_Serializar.gridx = 7;
		gbc_Serializar.gridy = 3;
		telaProduto.add(Serializar, gbc_Serializar);
		GridBagConstraints gbc_Desserializar = new GridBagConstraints();
		gbc_Desserializar.insets = new Insets(0, 0, 5, 0);
		gbc_Desserializar.gridx = 7;
		gbc_Desserializar.gridy = 4;
		telaProduto.add(Desserializar, gbc_Desserializar);

		JButton btnRelatorio = new JButton("Relatorio");
		GridBagConstraints gbc_btnRelatorio = new GridBagConstraints();
		gbc_btnRelatorio.gridx = 7;
		gbc_btnRelatorio.gridy = 5;
		telaProduto.add(btnRelatorio, gbc_btnRelatorio);

		JPanel telaVendas = new JPanel();

		tabbedPane.addTab(ABA_TRES, telaVendas);
		GridBagLayout gbl_telaVendas = new GridBagLayout();
		gbl_telaVendas.columnWidths = new int[] { 30, 30, 30, 30, 30, 30, 30 };
		gbl_telaVendas.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_telaVendas.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_telaVendas.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		telaVendas.setLayout(gbl_telaVendas);

		JButton button = new JButton("Adcionar");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 5;
		gbc_button.gridy = 0;
		telaVendas.add(button, gbc_button);

		JButton button_1 = new JButton("Importar TXT");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTH;
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 6;
		gbc_button_1.gridy = 0;
		telaVendas.add(button_1, gbc_button_1);

		JButton button_2 = new JButton("Alterar");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.NORTH;
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 5;
		gbc_button_2.gridy = 1;
		telaVendas.add(button_2, gbc_button_2);

		JButton button_3 = new JButton("Importar XML");
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.anchor = GridBagConstraints.NORTH;
		gbc_button_3.insets = new Insets(0, 0, 5, 0);
		gbc_button_3.gridx = 6;
		gbc_button_3.gridy = 1;
		telaVendas.add(button_3, gbc_button_3);

		JButton button_4 = new JButton("Exportar XML");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.anchor = GridBagConstraints.NORTH;
		gbc_button_4.insets = new Insets(0, 0, 5, 0);
		gbc_button_4.gridx = 6;
		gbc_button_4.gridy = 2;
		telaVendas.add(button_4, gbc_button_4);

		JScrollPane PainelVenda = new JScrollPane();
		GridBagConstraints gbc_PainelVenda = new GridBagConstraints();
		gbc_PainelVenda.gridheight = 3;
		gbc_PainelVenda.gridwidth = 4;
		gbc_PainelVenda.insets = new Insets(0, 0, 5, 5);
		gbc_PainelVenda.fill = GridBagConstraints.BOTH;
		gbc_PainelVenda.gridx = 0;
		gbc_PainelVenda.gridy = 3;
		telaVendas.add(PainelVenda, gbc_PainelVenda);

		JButton button_5 = new JButton("Serializar");
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.anchor = GridBagConstraints.NORTH;
		gbc_button_5.insets = new Insets(0, 0, 5, 0);
		gbc_button_5.gridx = 6;
		gbc_button_5.gridy = 3;
		telaVendas.add(button_5, gbc_button_5);

		JButton button_6 = new JButton("Desserializar");
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.anchor = GridBagConstraints.NORTH;
		gbc_button_6.insets = new Insets(0, 0, 5, 0);
		gbc_button_6.gridx = 6;
		gbc_button_6.gridy = 4;
		telaVendas.add(button_6, gbc_button_6);

		JButton button_7 = new JButton("Relatorio");
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.anchor = GridBagConstraints.NORTH;
		gbc_button_7.gridx = 6;
		gbc_button_7.gridy = 5;
		telaVendas.add(button_7, gbc_button_7);
		mostraUltima();

	}

	private void mostraUltima() {
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
	}

	protected void preencheTabela() {

		ReaderArquivo reader = new ReaderArquivo();
		List<String> lista = reader.lerArquivo(new File("listaProdutos.txt"));

		ParserProduto parser = new ParserProduto();
		listaProduto = parser.getProduto(lista);

	}

	public void SalvarProdutoXML(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ProdutoWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ProdutoWrapper wrapper = new ProdutoWrapper();
			wrapper.setProduto(listaProduto);

			m.marshal(wrapper, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void LerProdutoXML(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(ProdutoWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			ProdutoWrapper wrapper = (ProdutoWrapper) um.unmarshal(file);

			if (listaProduto != null)
				listaProduto.clear();
			else
				listaProduto = new ArrayList<>();

			listaProduto.addAll(wrapper.getProduto());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Arquivo vazio ou nao encontrado!");
		}
	}

	public void SalvarProdutoSerializacao(File file) {
		try {

			FileOutputStream fout = new FileOutputStream(file);

			ObjectOutputStream oos = new ObjectOutputStream(fout);

			oos.writeObject(listaProduto);

			oos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void LerProdutoSerializacao(File file) {
		try {

			FileInputStream fin = new FileInputStream(file);

			ObjectInputStream ois = new ObjectInputStream(fin);

			if (listaProduto != null)
				listaProduto.clear();
			else
				listaProduto = new ArrayList<>();

			listaProduto = (List<Produto>) ois.readObject();

			ois.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Arquivo vazio ou nao encontrado!");
		}
	}

	private Produto getProdutoSelecionadoNaTabela() {
		Produto c = null;
		int index = table.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada!");
		} else {
			c = ((ProdutoModel) table.getModel()).getProdutoNaLinha(index);
		}
		return c;
	}

}
