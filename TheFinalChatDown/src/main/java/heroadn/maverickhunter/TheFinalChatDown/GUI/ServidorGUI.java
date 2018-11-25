package heroadn.maverickhunter.TheFinalChatDown.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import heroadn.maverickhunter.TheFinalChatDown.Cliente.ClienteTabelaModelo;
import heroadn.maverickhunter.TheFinalChatDown.Servidor.Servidor;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ServidorGUI extends GUI {

	private JPanel contentPane;
	private int porta;
	
	private String msg_server = "Iniciar Servidor";
	private JTextField porta_field;
	
	private JTable table;
	private JButton msg;
	
	private Servidor servidor;
	private Thread thread;
	
	private JLabel porta_label;
	private JLabel servidor_label;
	
	private JScrollPane scrollPane;
	private ClienteTabelaModelo modelo;
	private JLabel sair_label;
	
	private Color in = new Color(0,0,0);
	private Color out = new Color(255,255,255);
	
	public ServidorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 272);	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		servidor_label = new JLabel("Servidor");
		servidor_label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		servidor_label.setBounds(20, 37, 169, 30);
		contentPane.add(servidor_label);
		
		porta_field = new JTextField();
		porta_field.setBounds(20, 137, 169, 20);
		contentPane.add(porta_field);
		porta_field.setColumns(10);
		
		porta_label = new JLabel("Digite a Porta do Servidor: ");
		porta_label.setBounds(20, 113, 169, 14);
		contentPane.add(porta_label);
		
		msg = new JButton(msg_server);
		msg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Alerta se for de um tipo diferente
				try {
					porta = (Integer.parseInt(porta_field.getText()));
				}catch (NumberFormatException ex) {
					ex.getMessage();
				}
				
				if(porta != 0) {	
					if(servidor == null) 
						servidor = new Servidor(porta, modelo);
				}else {
					JOptionPane.showMessageDialog(null, 
							"Digite um numero ex: > 1024 ", 
							"Error", 
							JOptionPane.ERROR_MESSAGE);
				}
				
				//Servidor Start/Stop
				if(servidor.isRunning()) {
						servidor.stopServer();
						msg.setText("IniciarServidor");
				}else {
					servidor.startServer();
					thread = new Thread(servidor);
					thread.start();
					msg.setText("Encerrar Servidor");
				}
			}
		});
		msg.setBounds(20, 180, 169, 23);
		contentPane.add(msg);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(199, 37, 230, 166);
		contentPane.add(scrollPane);
		
		//Tabela
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//Modelo da Tabela
		modelo = new ClienteTabelaModelo();
		table.setModel(modelo);
		
		sair_label = new JLabel("X");
		sair_label.setBounds(423, 11, 17, 14);
		sair_label.setForeground(out);
		sair_label.addMouseListener(
				exitButton(sair_label,in,out)
				);
		contentPane.add(sair_label);
	}
}
