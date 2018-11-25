package heroadn.maverickhunter.TheFinalChatDown.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import org.json.JSONObject;

import com.google.gson.JsonObject;

import heroadn.maverickhunter.TheFinalChatDown.Cliente.Cliente;
import heroadn.maverickhunter.TheFinalChatDown.Cliente.Sessao;
import heroadn.maverickhunter.TheFinalChatDown.Util.Path;

@SuppressWarnings("serial")
public class ClienteGUI extends GUI {

	private JPanel contentPane;
    
    private JTextField output;
	private JScrollPane scrollPane;
    
    private JTextPane textPane;
    private StyledDocument document;
    private JLabel sair_label;
    private JLabel background_image;
    
    private Color colorIn = new Color(0,0,0);
	private Color colorOut = new Color(255,255,255);
	
	private BufferedReader in;
	private BufferedWriter out;
	
	private List<Sessao> clientes;

	public ClienteGUI(final Cliente cliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 267, 440);
		setTitle("Formulario Cliente");
		contentPane = new JPanel();
		clientes = new ArrayList<>();

		
		//Comunicaçao do cliente
		try {
			in = new BufferedReader(new InputStreamReader(cliente.getSocket().getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(cliente.getSocket().getOutputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Deixando a borda redonda
		contentPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0,200),20,true));
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setContentPane(contentPane);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 49, 245, 304);
        contentPane.add(scrollPane);
        
        sair_label = new JLabel("X");
        sair_label.setBounds(249, 22, 18, 14);
        sair_label.setForeground(colorOut);
        sair_label.addMouseListener(
				(exitButton(sair_label,colorIn,colorOut))
				);
        contentPane.add(sair_label);
        
        //Painel de texto
        textPane = new JTextPane();
        textPane.setEditable(false);
        scrollPane.setViewportView(textPane);
       
        //Recebendo Mensagem
        Thread input = new Thread(() -> {
			try {
				String msg;
				while((msg = in.readLine()) != null) {
					String[] mensagem = msg.split("&&&");
					println("<"+mensagem[0]+"> "+mensagem[1]);
					
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		input.start();
		
		output = new JTextField();
        output.setBounds(10, 364, 245, 29);
        contentPane.add(output);
        output.setColumns(10);
        
        //((int) (Math.random() * 200))
        output.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
		        output.selectAll();
		        String userName = cliente.getNome();
				String msg      = output.getText();
				
				
				try {
					//Mensagem do usuario
			        out.write((userName+"&&&"
									   +msg
									   +"&&&"
									   +Path.font_path+"perfil.png"
									   +"&&&"
									   +cliente.getId())+"\n"
			        		);
			        //Fim da Mensagem
			        
			        out.flush();
			        output.setText("");
				} catch (Exception e1) {
				    e1.printStackTrace();
				}
            }
        });
        
        document = textPane.getStyledDocument();
        background_image = new JLabel();
        background_image.setIcon(new ImageIcon(Path.img_path+"blur.jpeg"));
        background_image.setBounds(0, 11, 265, 418);
        contentPane.add(background_image);
	}
	
	public void println(String msg) {
		Style style = textPane.addStyle("Style", null);
		try {
			document.insertString(document.getLength(), msg + "\n", style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
