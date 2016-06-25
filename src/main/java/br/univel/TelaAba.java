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

public class TelaAba extends JFrame {

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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAba frame = new TelaAba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAba() {
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
		JPanel telaProduto = new JPanel();

		tabbedPane.addTab(ABA_UM, telaProduto);
		GridBagLayout gbl_telaProduto = new GridBagLayout();
		gbl_telaProduto.columnWidths = new int[] { 70, 70, 70, 70, 20, 70, 70 };
		gbl_telaProduto.rowHeights = new int[] { 1, 1, 1, 1, 1, 30 };
		gbl_telaProduto.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
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

		JLabel lblPreoUn = new JLabel("Preço Un");
		GridBagConstraints gbc_lblPreoUn = new GridBagConstraints();
		gbc_lblPreoUn.anchor = GridBagConstraints.EAST;
		gbc_lblPreoUn.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreoUn.gridx = 2;
		gbc_lblPreoUn.gridy = 0;
		telaProduto.add(lblPreoUn, gbc_lblPreoUn);

		tfPreco = new NumberField();
		GridBagConstraints gbc_tfPreco = new GridBagConstraints();
		gbc_tfPreco.insets = new Insets(0, 0, 5, 5);
		gbc_tfPreco.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPreco.gridx = 3;
		gbc_tfPreco.gridy = 0;
		telaProduto.add(tfPreco, gbc_tfPreco);
		tfPreco.setColumns(10);

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

				if (!tfCodigo.getText().equals("") && !tfDesc.getText().equals("") && !tfPreco.getText().equals("")){
					Produto p = new Produto();
					p.setId(Integer.parseInt(tfCodigo.getText()));
					p.setDescricao(tfDesc.getText());
				    p.setPreco(new BigDecimal(tfPreco.getText()));


				    if (listaProduto == null)
				    	listaProduto = new ArrayList<>();

				    listaProduto.add(p);
				}

				/*tfCodigo.setText(Integer.toString(c.getId()));
				tfDesc.setText(c.getDescricao());
				tfPreco.setText(c.getPreco().toString());*/
			}
		});
		GridBagConstraints gbc_btnAdcionar = new GridBagConstraints();
		gbc_btnAdcionar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdcionar.gridx = 5;
		gbc_btnAdcionar.gridy = 0;
		telaProduto.add(btnAdcionar, gbc_btnAdcionar);
		GridBagConstraints gbc_btnImportarTXT = new GridBagConstraints();
		gbc_btnImportarTXT.gridwidth = 2;
		gbc_btnImportarTXT.insets = new Insets(0, 0, 5, 0);
		gbc_btnImportarTXT.gridx = 6;
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
		gbc_tfDesc.gridwidth = 3;
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
		gbc_btnAlterar.gridx = 5;
		gbc_btnAlterar.gridy = 1;
		telaProduto.add(btnAlterar, gbc_btnAlterar);
		GridBagConstraints gbc_ImportarXML = new GridBagConstraints();
		gbc_ImportarXML.insets = new Insets(0, 0, 5, 0);
		gbc_ImportarXML.gridx = 6;
		gbc_ImportarXML.gridy = 1;
		telaProduto.add(ImportarXML, gbc_ImportarXML);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		telaProduto.add(scrollPane, gbc_scrollPane);

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
		gbc_ExportarXML.gridx = 6;
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
		gbc_Serializar.gridx = 6;
		gbc_Serializar.gridy = 3;
		telaProduto.add(Serializar, gbc_Serializar);
		GridBagConstraints gbc_Desserializar = new GridBagConstraints();
		gbc_Desserializar.insets = new Insets(0, 0, 5, 0);
		gbc_Desserializar.gridx = 6;
		gbc_Desserializar.gridy = 4;
		telaProduto.add(Desserializar, gbc_Desserializar);

		JButton btnRelatorio = new JButton("Relatorio");
		GridBagConstraints gbc_btnRelatorio = new GridBagConstraints();
		gbc_btnRelatorio.gridx = 6;
		gbc_btnRelatorio.gridy = 5;
		telaProduto.add(btnRelatorio, gbc_btnRelatorio);

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
		mostraUltima();
	}

	protected void adicionaVendas() {

		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (tabbedPane.getTitleAt(i).equals(ABA_TRES)) {
				tabbedPane.setSelectedIndex(i);
				return;
			}
		}

		JPanel telaVendas = new JPanel();

		tabbedPane.addTab(ABA_TRES, telaVendas);
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
