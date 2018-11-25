package heroadn.maverickhunter.TheFinalChatDown.Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import heroadn.maverickhunter.TheFinalChatDown.Cliente.Cliente;
import heroadn.maverickhunter.TheFinalChatDown.Cliente.ClienteTabelaModelo;
import heroadn.maverickhunter.TheFinalChatDown.Util.Connection;
import heroadn.maverickhunter.TheFinalChatDown.Util.Data;
import heroadn.maverickhunter.TheFinalChatDown.Util.Log;

public class Servidor extends Thread{
	
	private int porta;
	private List<UserThread> usuarios;
	private ClienteTabelaModelo modelo;
	private ServerSocket serverSocket;
    
    private int userId = 0;	
    private int linha = 0;
    private boolean running = false;
    
	public Servidor(int porta, ClienteTabelaModelo modelo) {
		this.porta = porta;
		this.modelo = modelo;
		this.usuarios = new ArrayList<>();
	}
	
	//Iniciar o Servidor
	public void run() {	
		try {
			serverSocket = new ServerSocket(porta);
			while(running){
				Socket socket = serverSocket.accept();
				String host = socket.getInetAddress().getHostName();
				System.out.println("Usuario Conectado: "+host+" #"+(userId++)+".");
				
				Data data = new Data(socket);
				Cliente cliente = data.receive();
				cliente.setId(userId);
				modelo.addCliente(cliente);
				
				Connection connection = new Connection(socket);		
				UserThread user = new UserThread(connection, this);
				usuarios.add(user);
				new Thread(user).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	
    /*
     * Metodo responsavel por imprimir log do servidor
     * */
    public void serverLog(Socket socket,String message) {
    	System.out.print(linha+": ");
    	String log = Log.regLog(socket.getInetAddress().getHostName()+": "+message,"log/log.txt");
        System.out.println(log);
    }
	
	public List<UserThread> getClientes() {
		return usuarios;
	}

	public void setClientes(List<UserThread> clientes) {
		this.usuarios = clientes;
	}

	public void setID(int id) {
		this.userId = id;
	}

	public int getID() {
		return this.userId;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public synchronized void startServer() {
		running = true;
	}
	
	public synchronized void stopServer() {
		running = false;
		try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
