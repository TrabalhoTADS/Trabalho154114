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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JTable table;

	private List<Produto> listaProduto;
	private List<Cliente> listaCliente;
	private SqlGenImplementation imp;

	private String ABA_UM = "Produtos";
	private String ABA_DOIS = "Clientes";
	private String ABA_TRES = "Vendas";
	private NumberField tfCodigo;
	private NumberField tfPreco;
	private JTextField tfDesc;
	private JScrollPane PainelProdutos;
	private JScrollPane PainelVendas;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

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

		JPanel telaProduto = new JPanel();

		tabbedPane.addTab(ABA_UM, telaProduto);
		GridBagLayout gbl_telaProduto = new GridBagLayout();
		gbl_telaProduto.columnWidths = new int[] { 30, 95, 65, 30, 67, 30, 97, 30 };
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

		JButton btnNewButton_3 = new JButton("ExportarXML");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalvarProdutoXML(new File("ListaProdutos.xml"));
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 7;
		gbc_btnNewButton_3.gridy = 1;
		telaProduto.add(btnNewButton_3, gbc_btnNewButton_3);
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

		if (listaProduto == null)
			listaProduto = new ArrayList<>();

		if (listaProduto.size() == 0) {
			listarTodos(new Produto());
		}

		if (listaProduto.size() == 0) {
			importarProdutosTXT();
		}

		SetModel(1);

		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tfCodigo.getText().equals("") && !tfDesc.getText().equals("") && !tfPreco.getText().equals("")) {
					Produto p = new Produto();
					p.setId(Integer.parseInt(tfCodigo.getText()));
					p.setDescricao(tfDesc.getText());
					p.setPreco(new BigDecimal(tfPreco.getText()));

					if (buscar(p) > 0) {
						JOptionPane.showMessageDialog(null, "PRODUTO JA CADASTRADO!");
						return;
					}

					if (listaProduto == null)
						listaProduto = new ArrayList<>();

					salvar(p);

					listaProduto.add(p);

					SetModel(1);

					tfCodigo.setText("");
					tfDesc.setText("");
					tfPreco.setText("");
				}
			}
		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 2;
		telaProduto.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_4 = new JButton("ImportarXML");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LerProdutoXML(new File("ListaProdutos.xml"));
				SetModel(1);
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 7;
		gbc_btnNewButton_4.gridy = 2;
		telaProduto.add(btnNewButton_4, gbc_btnNewButton_4);

		JButton btnNewButton_2 = new JButton("Alterar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto c = getProdutoSelecionadoNaTabela();
				if (c != null) {
					if (!tfCodigo.getText().equals("") && !tfDesc.getText().equals("")
							&& !tfPreco.getText().equals("")) {
						c.setId(Integer.parseInt(tfCodigo.getText()));
						c.setDescricao(tfDesc.getText());
						c.setPreco(new BigDecimal(tfPreco.getText()));
						listaProduto.set(table.getSelectedRow(), c);

						SetModel(1);

						tfCodigo.setText("");
						tfDesc.setText("");
						tfPreco.setText("");
					}
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 6;
		gbc_btnNewButton_2.gridy = 3;
		telaProduto.add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnNewButton_6 = new JButton("Serializar");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalvarProdutoSerializacao(new File("ListaProdutos.ser"));
			}
		});
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_6.gridx = 7;
		gbc_btnNewButton_6.gridy = 3;
		telaProduto.add(btnNewButton_6, gbc_btnNewButton_6);

		JButton btnNewButton_5 = new JButton("Excluir");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerSelecionado(1);
				SetModel(1);
			}
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 6;
		gbc_btnNewButton_5.gridy = 4;
		telaProduto.add(btnNewButton_5, gbc_btnNewButton_5);

		JButton btnNewButton_7 = new JButton("Desseralizar");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LerProdutoSerializacao(new File("ListaProdutos.ser"));
				SetModel(1);
			}
		});
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_7.gridx = 7;
		gbc_btnNewButton_7.gridy = 4;
		telaProduto.add(btnNewButton_7, gbc_btnNewButton_7);

		JButton btnRelatorio = new JButton("Relatorio");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JasperPrint relat;

				try {
					relat = gerar();
					JasperViewer.viewReport(relat, false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
				}
			}
		});
		GridBagConstraints gbc_btnRelatorio = new GridBagConstraints();
		gbc_btnRelatorio.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRelatorio.gridx = 7;
		gbc_btnRelatorio.gridy = 5;
		telaProduto.add(btnRelatorio, gbc_btnRelatorio);

		mostraUltima();

		configuraTabela(1);
	}

	public JasperPrint gerar() {
		JasperPrint rel = null;
		try {
			HashMap map = new HashMap();
			String arquivoJasper = "C:\\Users\\Eduardo\\git\\Trabalho154114\\src\\main\\java\\br\\univel\\RelProdutos.jasper";
			rel = JasperFillManager.fillReport(arquivoJasper, map, getImp().getCon());
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		return rel;
	}

	protected void adicionaClientes() {

		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (tabbedPane.getTitleAt(i).equals(ABA_DOIS)) {
				tabbedPane.setSelectedIndex(i);
				return;
			}
		}

		JPanel telaCliente = new JPanel();

		tabbedPane.addTab("Clientes", telaCliente);
		GridBagLayout gbl_telaCliente = new GridBagLayout();
		gbl_telaCliente.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_telaCliente.columnWidths = new int[] { 30, 30, 30, 30, 10, 30, 30, 30, 0, 30 };
		gbl_telaCliente.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0 };
		gbl_telaCliente.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		telaCliente.setLayout(gbl_telaCliente);

		JLabel lblNewLabel_1 = new JLabel("ID: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		telaCliente.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_11 = new JTextField();
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 5);
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.gridx = 1;
		gbc_textField_11.gridy = 0;
		telaCliente.add(textField_11, gbc_textField_11);
		textField_11.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 2;
		gbc_lblNome.gridy = 0;
		telaCliente.add(lblNome, gbc_lblNome);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 0;
		telaCliente.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JButton AddCliente = new JButton("Adcionar");
		GridBagConstraints gbc_AddCliente = new GridBagConstraints();
		gbc_AddCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddCliente.insets = new Insets(0, 0, 5, 5);
		gbc_AddCliente.gridx = 7;
		gbc_AddCliente.gridy = 0;
		telaCliente.add(AddCliente, gbc_AddCliente);

		JButton ImpClienteTXT = new JButton("Importar TXT");
		GridBagConstraints gbc_ImpClienteTXT = new GridBagConstraints();
		gbc_ImpClienteTXT.fill = GridBagConstraints.HORIZONTAL;
		gbc_ImpClienteTXT.anchor = GridBagConstraints.NORTH;
		gbc_ImpClienteTXT.insets = new Insets(0, 0, 5, 0);
		gbc_ImpClienteTXT.gridx = 8;
		gbc_ImpClienteTXT.gridy = 0;
		telaCliente.add(ImpClienteTXT, gbc_ImpClienteTXT);

		JLabel lblEndereco = new JLabel("Endereco:");
		GridBagConstraints gbc_lblEndereco = new GridBagConstraints();
		gbc_lblEndereco.anchor = GridBagConstraints.EAST;
		gbc_lblEndereco.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereco.gridx = 0;
		gbc_lblEndereco.gridy = 1;
		telaCliente.add(lblEndereco, gbc_lblEndereco);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 5;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 1;
		telaCliente.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JButton AltCliente = new JButton("Alterar");
		GridBagConstraints gbc_AltCliente = new GridBagConstraints();
		gbc_AltCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_AltCliente.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_AltCliente.insets = new Insets(0, 0, 5, 5);
		gbc_AltCliente.gridx = 7;
		gbc_AltCliente.gridy = 1;
		telaCliente.add(AltCliente, gbc_AltCliente);

		JButton ImpClienteXml = new JButton("Importar XML");
		GridBagConstraints gbc_ImpClienteXml = new GridBagConstraints();
		gbc_ImpClienteXml.fill = GridBagConstraints.HORIZONTAL;
		gbc_ImpClienteXml.anchor = GridBagConstraints.NORTH;
		gbc_ImpClienteXml.insets = new Insets(0, 0, 5, 0);
		gbc_ImpClienteXml.gridx = 8;
		gbc_ImpClienteXml.gridy = 1;
		telaCliente.add(ImpClienteXml, gbc_ImpClienteXml);

		JLabel lblNumero = new JLabel("Numero:");
		GridBagConstraints gbc_lblNumero = new GridBagConstraints();
		gbc_lblNumero.anchor = GridBagConstraints.EAST;
		gbc_lblNumero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumero.gridx = 0;
		gbc_lblNumero.gridy = 2;
		telaCliente.add(lblNumero, gbc_lblNumero);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 2;
		telaCliente.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JButton ExpClienteXml = new JButton("Exportar XML");
		ExpClienteXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JLabel lblNewLabel = new JLabel("Comp: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		telaCliente.add(lblNewLabel, gbc_lblNewLabel);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 2;
		telaCliente.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JLabel lblCep = new JLabel("CEP:");
		GridBagConstraints gbc_lblCep = new GridBagConstraints();
		gbc_lblCep.anchor = GridBagConstraints.EAST;
		gbc_lblCep.insets = new Insets(0, 0, 5, 5);
		gbc_lblCep.gridx = 4;
		gbc_lblCep.gridy = 2;
		telaCliente.add(lblCep, gbc_lblCep);

		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 5;
		gbc_textField_7.gridy = 2;
		telaCliente.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto c = getProdutoSelecionadoNaTabela();
				if (c != null) {
					((ProdutoModel) table.getModel()).removerProduto(c);
				}
			}
		});
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExcluir.insets = new Insets(0, 0, 5, 5);
		gbc_btnExcluir.gridx = 7;
		gbc_btnExcluir.gridy = 2;
		telaCliente.add(btnExcluir, gbc_btnExcluir);
		GridBagConstraints gbc_ExpClienteXml = new GridBagConstraints();
		gbc_ExpClienteXml.fill = GridBagConstraints.HORIZONTAL;
		gbc_ExpClienteXml.anchor = GridBagConstraints.NORTH;
		gbc_ExpClienteXml.insets = new Insets(0, 0, 5, 0);
		gbc_ExpClienteXml.gridx = 8;
		gbc_ExpClienteXml.gridy = 2;
		telaCliente.add(ExpClienteXml, gbc_ExpClienteXml);

		JLabel lblBairro = new JLabel("Bairro: ");
		GridBagConstraints gbc_lblBairro = new GridBagConstraints();
		gbc_lblBairro.anchor = GridBagConstraints.EAST;
		gbc_lblBairro.insets = new Insets(0, 0, 5, 5);
		gbc_lblBairro.gridx = 0;
		gbc_lblBairro.gridy = 3;
		telaCliente.add(lblBairro, gbc_lblBairro);

		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.gridwidth = 2;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 3;
		telaCliente.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.anchor = GridBagConstraints.EAST;
		gbc_lblCidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCidade.gridx = 3;
		gbc_lblCidade.gridy = 3;
		telaCliente.add(lblCidade, gbc_lblCidade);

		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.gridwidth = 2;
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 4;
		gbc_textField_6.gridy = 3;
		telaCliente.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);

		JButton SerCliente = new JButton("Serializar");
		GridBagConstraints gbc_SerCliente = new GridBagConstraints();
		gbc_SerCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_SerCliente.anchor = GridBagConstraints.NORTH;
		gbc_SerCliente.insets = new Insets(0, 0, 5, 0);
		gbc_SerCliente.gridx = 8;
		gbc_SerCliente.gridy = 3;
		telaCliente.add(SerCliente, gbc_SerCliente);

		JLabel lblEstado = new JLabel("Estado:");
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.anchor = GridBagConstraints.EAST;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 4;
		telaCliente.add(lblEstado, gbc_lblEstado);

		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 1;
		gbc_textField_8.gridy = 4;
		telaCliente.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);

		JLabel lblTel = new JLabel("TEL:");
		GridBagConstraints gbc_lblTel = new GridBagConstraints();
		gbc_lblTel.anchor = GridBagConstraints.EAST;
		gbc_lblTel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTel.gridx = 2;
		gbc_lblTel.gridy = 4;
		telaCliente.add(lblTel, gbc_lblTel);

		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 3;
		gbc_textField_9.gridy = 4;
		telaCliente.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);

		JLabel lblCel = new JLabel("CEL:");
		GridBagConstraints gbc_lblCel = new GridBagConstraints();
		gbc_lblCel.anchor = GridBagConstraints.EAST;
		gbc_lblCel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCel.gridx = 4;
		gbc_lblCel.gridy = 4;
		telaCliente.add(lblCel, gbc_lblCel);

		textField_10 = new JTextField();
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10.gridx = 5;
		gbc_textField_10.gridy = 4;
		telaCliente.add(textField_10, gbc_textField_10);
		textField_10.setColumns(10);

		JButton DesCliente = new JButton("Desserializar");
		GridBagConstraints gbc_DesCliente = new GridBagConstraints();
		gbc_DesCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_DesCliente.anchor = GridBagConstraints.NORTH;
		gbc_DesCliente.insets = new Insets(0, 0, 5, 0);
		gbc_DesCliente.gridx = 8;
		gbc_DesCliente.gridy = 4;
		telaCliente.add(DesCliente, gbc_DesCliente);

		JButton RelCliente = new JButton("Relatorio");
		GridBagConstraints gbc_RelCliente = new GridBagConstraints();
		gbc_RelCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_RelCliente.anchor = GridBagConstraints.NORTH;
		gbc_RelCliente.gridx = 8;
		gbc_RelCliente.gridy = 5;
		telaCliente.add(RelCliente, gbc_RelCliente);

	}

	protected void adicionaVendas() {

		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (tabbedPane.getTitleAt(i).equals(ABA_TRES)) {
				tabbedPane.setSelectedIndex(i);
				return;
			}
		}

		JPanel telaVenda = new JPanel();

		tabbedPane.addTab("Vendas", telaVenda);
		GridBagLayout gbl_telaVenda = new GridBagLayout();
		gbl_telaVenda.columnWidths = new int[] { 10, 95, 65, 30, 67, 30, 97, 30 };
		gbl_telaVenda.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_telaVenda.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_telaVenda.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 };
		telaVenda.setLayout(gbl_telaVenda);

		JLabel lblCliente = new JLabel("Cliente:");
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 1;
		telaVenda.add(lblCliente, gbc_lblCliente);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		telaVenda.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton button = new JButton("Adcionar");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 6;
		gbc_button.gridy = 1;
		telaVenda.add(button, gbc_button);

		JButton button_1 = new JButton("Importar TXT");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.anchor = GridBagConstraints.NORTH;
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 7;
		gbc_button_1.gridy = 1;
		telaVenda.add(button_1, gbc_button_1);

		JScrollPane PainelClientes = new JScrollPane();
		GridBagConstraints gbc_PainelClientes = new GridBagConstraints();
		gbc_PainelClientes.gridheight = 5;
		gbc_PainelClientes.gridwidth = 5;
		gbc_PainelClientes.insets = new Insets(0, 0, 0, 5);
		gbc_PainelClientes.fill = GridBagConstraints.BOTH;
		gbc_PainelClientes.gridx = 0;
		gbc_PainelClientes.gridy = 2;
		telaVenda.add(PainelClientes, gbc_PainelClientes);

		JButton button_2 = new JButton("Alterar");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_2.anchor = GridBagConstraints.NORTH;
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 6;
		gbc_button_2.gridy = 2;
		telaVenda.add(button_2, gbc_button_2);

		JButton button_3 = new JButton("Importar XML");
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_3.anchor = GridBagConstraints.NORTH;
		gbc_button_3.insets = new Insets(0, 0, 5, 0);
		gbc_button_3.gridx = 7;
		gbc_button_3.gridy = 2;
		telaVenda.add(button_3, gbc_button_3);

		JButton button_4 = new JButton("Exportar XML");
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_4.anchor = GridBagConstraints.NORTH;
		gbc_button_4.insets = new Insets(0, 0, 5, 0);
		gbc_button_4.gridx = 7;
		gbc_button_4.gridy = 3;
		telaVenda.add(button_4, gbc_button_4);

		JButton button_5 = new JButton("Serializar");
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_5.anchor = GridBagConstraints.NORTH;
		gbc_button_5.insets = new Insets(0, 0, 5, 0);
		gbc_button_5.gridx = 7;
		gbc_button_5.gridy = 4;
		telaVenda.add(button_5, gbc_button_5);

		JButton button_6 = new JButton("Desserializar");
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_6.anchor = GridBagConstraints.NORTH;
		gbc_button_6.insets = new Insets(0, 0, 5, 0);
		gbc_button_6.gridx = 7;
		gbc_button_6.gridy = 5;
		telaVenda.add(button_6, gbc_button_6);

		JButton button_7 = new JButton("Relatorio");
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_7.anchor = GridBagConstraints.NORTH;
		gbc_button_7.gridx = 7;
		gbc_button_7.gridy = 6;
		telaVenda.add(button_7, gbc_button_7);
		mostraUltima();
		mostraUltima();

	}

	private void mostraUltima() {
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
	}

	protected void importarProdutosTXT() {

		ReaderArquivo reader = new ReaderArquivo();
		List<String> lista = reader.lerArquivo(new File("listaProdutos.txt"));

		ParserProduto parser = new ParserProduto();
		listaProduto = parser.getProduto(lista);

		for (Produto p : listaProduto) {
			salvar(p);
		}
		JOptionPane.showMessageDialog(null, "BANCO DE DADOS VAZIO! Os produtos foram importados do arquivo TXT");
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
		JOptionPane.showMessageDialog(null, "PRODUTOS EXPORTADOS PARA O ARQUIVO " + file);
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
		JOptionPane.showMessageDialog(null, "PRODUTOS EXPORTADOS PARA O ARQUIVO " + file);
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
		JOptionPane.showMessageDialog(null, "PRODUTOS IMPORTADOS DO ARQUIVO " + file);
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
		JOptionPane.showMessageDialog(null, "PRODUTOS IMPORTADOS DO ARQUIVO " + file);
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

	public void SetModel(int modelo) {
		if (modelo == 1) {
			ProdutoModel model = new ProdutoModel(listaProduto);
			if (model != null)
				table.setModel(model);
		} else if (modelo == 2) {
			ClienteModel model = new ClienteModel(listaCliente);
			if (model != null)
				table.setModel(model);
		}
	}

	protected void removerSelecionado(int modelo) {
		if (modelo == 1) {
			Produto c = getProdutoSelecionadoNaTabela();
			if (c != null) {
				((ProdutoModel) table.getModel()).removerProduto(c);
				excluir(c);
				listaProduto = ((ProdutoModel) table.getModel()).getLista();
			}
		} else if (modelo == 2) {

		}
	}

	private void configuraTabela(int modelo) {
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 1) {
					if (modelo == 1) {
						Produto c = getProdutoSelecionadoNaTabela();
						if (c != null) {
							tfCodigo.setEditable(true);
							tfCodigo.setText("");
							tfDesc.setText("");
							tfPreco.setText("");
						}
					}

				} else if (e.getClickCount() == 2) {
					if (modelo == 1) {
						Produto c = getProdutoSelecionadoNaTabela();
						if (c != null) {
							tfCodigo.setText(Integer.toString(c.getId()));
							tfCodigo.setEditable(false);
							tfDesc.setText(c.getDescricao());
							tfPreco.setText(c.getPreco().toString());
						}
					}

				}
			}

		});
	}

	private SqlGenImplementation getImp() {
		if (imp == null) {
			try {
				setImp(new SqlGenImplementation());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return imp;
	}

	private void setImp(SqlGenImplementation imp) {
		this.imp = imp;
	}

	public void CriarTabela(Object obj) {
		String sql = getImp().getCreateTable(getImp().getCon(), obj);

		try (PreparedStatement ps = getImp().getCon().prepareStatement(sql)) {
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
		}
	}

	public void salvar(Object obj) {
		PreparedStatement inclusao = getImp().getSqlInsert(getImp().getCon(), obj);

		if (obj.getClass() == Produto.class) {
			Produto p = (Produto) obj;
			try {
				inclusao.setInt(1, p.getId());
				inclusao.setString(2, p.getDescricao());
				inclusao.setBigDecimal(3, p.getPreco());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
			}

		} else if (obj.getClass() == Cliente.class) {
			Cliente c = (Cliente) obj;
			try {
				inclusao.setInt(1, c.getId());
				inclusao.setString(2, c.getNome());
				inclusao.setString(3, c.getEndereco());
				inclusao.setInt(4, c.getNumero());
				inclusao.setString(5, c.getComplemento());
				inclusao.setString(6, c.getCidade());
				inclusao.setString(7, c.getEstado());
				inclusao.setInt(8, c.getCep());
				inclusao.setString(9, c.getTelefone());
				inclusao.setString(10, c.getCelular());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
			}
		}

		try {
			inclusao.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
		}
	}

	public int buscar(Object obj) {
		PreparedStatement buscar = getImp().getSqlSelectById(getImp().getCon(), obj);

		ResultSet exibir = null;
		int cont = 0;

		try {
			exibir = buscar.executeQuery();
			while (exibir.next()) {
				cont++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
		}

		return cont;
	}

	public void atualizar(Object obj) {
		PreparedStatement alterar = getImp().getSqlUpdateById(getImp().getCon(), obj);

		Cliente cliente = (Cliente) obj;

		int exibir = 0;

		try {
			alterar.setString(1, cliente.getNome());
			alterar.setString(2, cliente.getEndereco());
			alterar.setString(3, cliente.getTelefone());
			alterar.setInt(5, cliente.getId());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
		}

		try {
			exibir = alterar.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
		}
	}

	public void excluir(Object obj) {
		PreparedStatement excluir = getImp().getSqlDeleteById(getImp().getCon(), obj);

		try {
			excluir.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
		}

	}

	public void listarTodos(Object obj) {

		PreparedStatement listar = getImp().getSqlSelectAll(getImp().getCon(), obj);

		if (obj.getClass() == Produto.class) {
			ResultSet retorno = null;

			try {
				retorno = listar.executeQuery();
				while (retorno.next()) {
					Produto aux = new Produto();
					aux.setId(retorno.getInt("id"));
					aux.setDescricao(retorno.getString("descricao"));
					aux.setPreco(new BigDecimal(retorno.getString("preco")));

					listaProduto.add(aux);

				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
			}

		} else if (obj.getClass() == Cliente.class) {
			ResultSet retorno = null;

			try {
				retorno = listar.executeQuery();
				while (retorno.next()) {
					Cliente aux = new Cliente();
					aux.setId(retorno.getInt("id"));
					aux.setNome(retorno.getString("nome"));
					aux.setEndereco(retorno.getString("endereco"));
					aux.setNumero(retorno.getInt("numero"));
					aux.setComplemento(retorno.getString("complemento"));
					aux.setBairro(retorno.getString("bairro"));
					aux.setCidade(retorno.getString("cidade"));
					aux.setEstado(retorno.getString("estado"));
					aux.setCep(retorno.getInt("cep"));
					aux.setTelefone(retorno.getString("telefone"));
					aux.setCelular(retorno.getString("celular"));

					listaCliente.add(aux);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
			}
		}

		try {
			listar.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO NA CONEXAO COM O BANCO!");
		}

		if (listaProduto.size() > 0)
			JOptionPane.showMessageDialog(null, "REGISTROS IMPORTADOS DO BANCO DE DADOS!");
	}
}
