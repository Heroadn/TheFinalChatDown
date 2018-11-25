package heroadn.maverickhunter.TheFinalChatDown.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import heroadn.maverickhunter.TheFinalChatDown.Cliente.Cliente;

public class Data {
	private Socket socket;

	public Data(Socket socket) {
		super();
		this.socket = socket;
	}

	public void send(Cliente cliente) {
		try {
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream ois = new ObjectOutputStream(os);
			ois.writeObject(cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Cliente receive() {
		Cliente cliente = null;
		
		try {
			InputStream input = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(input);
			cliente = (Cliente)ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cliente;
	}
	

	public Socket getSocket() {
		return socket;
	}
	

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
