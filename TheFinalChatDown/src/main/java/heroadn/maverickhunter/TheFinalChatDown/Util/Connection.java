package heroadn.maverickhunter.TheFinalChatDown.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Connection {
	
	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	private boolean alive;
	
	public Connection(Socket socket) {
		this.socket = socket;
		 
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		alive = true;
	}
	
	public String read() {
		try {
			if(in.ready()) {
				return in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void write(String data) {
		try {
			out.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void flush() {
		try {
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean close() {
		
		boolean result = true;
		
		try {
			socket.close();
			in.close();
			out.close();
			alive = false;
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public Boolean isAlive() {
		return this.alive;
	}
}
