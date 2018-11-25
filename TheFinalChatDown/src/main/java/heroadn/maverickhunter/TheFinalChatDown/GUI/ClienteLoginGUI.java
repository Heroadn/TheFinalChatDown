package heroadn.maverickhunter.TheFinalChatDown.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import heroadn.maverickhunter.TheFinalChatDown.Cliente.Cliente;
import heroadn.maverickhunter.TheFinalChatDown.Util.Convert;
import heroadn.maverickhunter.TheFinalChatDown.Util.Data;
import heroadn.maverickhunter.TheFinalChatDown.Util.FontUtils;
import heroadn.maverickhunter.TheFinalChatDown.Util.FrameMovable;
import heroadn.maverickhunter.TheFinalChatDown.Util.Path;

@SuppressWarnings("serial")
public class ClienteLoginGUI extends GUI {

	private JPanel contentPane;
	private JButton acessar;
	
	private JTextField host_field;
	private JTextField porta_field;
	private JTextField nome_field;
	
	private JLabel host_label;
	private JLabel porta_label;
	private JLabel nome_label;
	private JLabel sair_label;
	
	private JSeparator porta_separator;
	private JSeparator nome_separator;
	private JSeparator host_separator;
	
	private JLabel perfil;
	
	private Color in = new Color(0,0,0);
	private Color out = new Color(255,255,255);
	
	/**
	 * Create the frame.
	 */
	public ClienteLoginGUI() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 266, 440);
		setTitle("Formulario Cliente");
		contentPane = new JPanel();
		
		//Deixando a borda redonda
		contentPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0,200),20,true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		//Botao
		acessar = new JButton("Acessar");
		acessar.setBounds(33, 349, 196, 43);
		acessar.setBackground(new Color(60, 179, 113));
		acessar.setForeground(new Color(255,255,255));
		acessar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		acessar.setBorder(null);
		contentPane.add(acessar);
		acessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() { 
						Socket socket = null;
						
						String host = host_field.getText();
						int porta = Integer.parseInt(porta_field.getText());
						
						try {
							socket = getSocket(
									host, 
									porta
							);
							iniciarChat(socket);
						}
						catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(
									null, 
									"Verifique as informa�oes digitadas.", 
									"Error:", 
									JOptionPane.ERROR_MESSAGE
							);								
						}
						
					}//END Run
				});
			}//END Acessar Listener
		});
		
		//B
		sair_label = new JLabel("X");
		sair_label.setForeground(out);
		sair_label.addMouseListener(
				exitButton(sair_label,in,out)
				);
		sair_label.setBounds(237, 23, 19, 14);
		contentPane.add(sair_label);
		
		//Field
		nome_field = new JTextField();
		nome_field.setBounds(33, 146, 236, 20);
		nome_field.setBackground(null);
		nome_field.setForeground(null);
		nome_field.setCaretColor(new Color(255,255,255));
		nome_field.setOpaque(false);
		nome_field.setBorder(null);
		contentPane.add(nome_field);
		
		host_field = new JTextField();
		host_field.setBounds(33, 215, 236, 20);
		host_field.setBackground(null);
		host_field.setForeground(null);
		host_field.setCaretColor(new Color(255,255,255));
		host_field.setBorder(null);
		host_field.setOpaque(false);
		contentPane.add(host_field);
		
		porta_field = new JTextField();
		porta_field.setBounds(33, 284, 236, 20);
		porta_field.setBackground(null);
		porta_field.setForeground(null);
		porta_field.setCaretColor(new Color(255,255,255));
		porta_field.setOpaque(false);
		porta_field.setBorder(null);
		contentPane.add(porta_field);
		
		//Labels
		nome_label = new JLabel("Nome");
		nome_label.setBackground(new Color(255,255,255));
		nome_label.setBounds(33, 121, 65, 14);		
		nome_label.setForeground(new Color(57,113,117));
		nome_label.setFont(FontUtils.loadFont("Montserrat-Medium.ttf",12));
		contentPane.add(nome_label);
		
		host_label = new JLabel("Endere\u00E7o do Servidor");
		host_label.setBounds(33, 190, 140, 14);
		host_label.setFont(FontUtils.loadFont("Montserrat-Medium.ttf",12));
		host_label.setForeground(new Color(57,113,117));
		contentPane.add(host_label);
		
		
		porta_label = new JLabel("Porta do Servidor");
		porta_label.setBounds(33, 259, 140, 14);
		porta_label.setFont(new Font("Montserrat-Medium.ttf", Font.PLAIN, 12));
		porta_label.setForeground(new Color(57,113,117));
		contentPane.add(porta_label);
		
		//Separadores
		nome_separator = new JSeparator();
		nome_separator.setForeground(new Color(255,255,255));
		nome_separator.setBounds(33, 177, 196, 2);
		contentPane.add(nome_separator);
		
		host_separator = new JSeparator();
		host_separator.setForeground(new Color(255,255,255));
		host_separator.setBounds(33, 246, 196, 2);
		contentPane.add(host_separator);
		
		porta_separator = new JSeparator();
		porta_separator.setForeground(new Color(255,255,255));
		porta_separator.setBounds(33, 315, 196, 3);
		contentPane.add(porta_separator);
		
		perfil = new JLabel("");
		perfil.setIcon(new ImageIcon((Path.img_path+"perfil.png")));
		perfil.setBounds(96, 35, 67, 75);
		contentPane.add(perfil);
		
		//Imagens
		JLabel chat_img = new JLabel("");
		chat_img.setIcon(null);
		chat_img.setBounds(23, 165, 202, 72);
		contentPane.add(chat_img);
		
		JLabel background_img = new JLabel("");
		background_img.setIcon(new ImageIcon(Path.img_path+"blur.jpeg"));
		background_img.setBounds(0, 0, 361, 440);
		background_img.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0,250),0,true));
		contentPane.add(background_img);
		
		FrameMovable fm = new FrameMovable(this);
		addMouseListener(fm);
		addMouseMotionListener(fm);	
	}
	
	public void iniciarChat(Socket socket) {
		try {
			Cliente cliente = new Cliente(
					socket, 
					nome_field.getText(), 
					socket.getInetAddress().getHostName(), 
					Convert.toBytes(Path.img_path+"perfil.png")	
				);
			new Data(socket).send(cliente);;
			
			ClienteGUI frame = new ClienteGUI(cliente);
			frame.setVisible(true);
            dispose();
            
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					null, 
					"Servidor N�o Encontrado", 
					"Error: ", 
					JOptionPane.ERROR_MESSAGE
			);
		}
	}
	
	public Socket getSocket(String host,int porta) {
		Socket socket = null;
		
		try {
			if(host.equalsIgnoreCase("localhost")) {
				host = "127.0.0.1";
			}
			socket = new Socket(host,porta);
		} catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(
					null, 
					"Host n�o encontrado.", 
					"Error:", 
					JOptionPane.ERROR_MESSAGE
			);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(
					null, 
					""+e1.getMessage(), 
					"Error:", 
					JOptionPane.ERROR_MESSAGE
			);
		}
		return socket;
	}
	
}
