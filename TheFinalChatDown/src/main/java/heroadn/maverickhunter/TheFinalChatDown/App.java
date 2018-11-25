package heroadn.maverickhunter.TheFinalChatDown;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import heroadn.maverickhunter.TheFinalChatDown.GUI.ClienteLoginGUI;
import heroadn.maverickhunter.TheFinalChatDown.GUI.GUI;
import heroadn.maverickhunter.TheFinalChatDown.GUI.ServidorGUI;

import javax.swing.JLabel;

public class App extends GUI {

	private JPanel contentPane;
	private JButton buttonServidor;
	private JButton btnNewButton_1;
	private JLabel sair_label;
	
	private Color in = new Color(0,0,0);
	private Color out = new Color(255,255,255);
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		buttonServidor = new JButton("Servidor");
		buttonServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ServidorGUI frame = new ServidorGUI();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		buttonServidor.setBounds(175, 82, 91, 23);
		contentPane.add(buttonServidor);
		
		btnNewButton_1 = new JButton("Cliente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ClienteLoginGUI frame = new ClienteLoginGUI();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton_1.setBounds(175, 153, 91, 23);
		contentPane.add(btnNewButton_1);
		
		sair_label = new JLabel("X");
		sair_label.setBounds(426, 11, 14, 14);
		sair_label.addMouseListener(
				exitButton(sair_label,in,out)
				);
		contentPane.add(sair_label);
	}
}
