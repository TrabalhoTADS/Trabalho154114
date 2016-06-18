package br.univel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 3723660287073253901L;

	protected JPanel contentPane;

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
		setTelaPrincipalBack();

		JButton btnClientes = new JButton("CLIENTES");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCliente();
			}
		});
		GridBagConstraints gbc_btnClientes = new GridBagConstraints();
		gbc_btnClientes.insets = new Insets(10, 10, 10, 10);
		gbc_btnClientes.gridx = 0;
		gbc_btnClientes.gridy = 0;
		contentPane.add(btnClientes, gbc_btnClientes);

		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCliente();
			}
		});
		GridBagConstraints gbc_btnProdutos = new GridBagConstraints();
		gbc_btnProdutos.insets = new Insets(10, 10, 10, 10);
		gbc_btnProdutos.gridx = 1;
		gbc_btnProdutos.gridy = 0;
		contentPane.add(btnProdutos, gbc_btnProdutos);

		JButton btnVendas = new JButton("Adiciona");
		btnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCliente();
			}
		});
		GridBagConstraints gbc_btnVendas = new GridBagConstraints();
		gbc_btnVendas.insets = new Insets(10, 10, 10, 10);
		gbc_btnVendas.gridx = 2;
		gbc_btnVendas.gridy = 0;
		contentPane.add(btnVendas, gbc_btnVendas);

	}

	private void setTelaPrincipalBack() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0 };
		contentPane.setLayout(gbl_contentPane);

	}
}
