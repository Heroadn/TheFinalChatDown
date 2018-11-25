package heroadn.maverickhunter.TheFinalChatDown.Servidor;

import heroadn.maverickhunter.TheFinalChatDown.Util.Connection;


/*
 *Nessa classe o servidor gerencia o cliente criando um thread
 **/
public class UserThread implements Runnable{
	
	private Connection connection;
	private Servidor servidor;
	private Thread t;
	private boolean alive;

	public UserThread(Connection connection,Servidor servidor) {
		this.connection = connection;
		this.servidor = servidor;
		this.alive = false;
	}

	public synchronized void startSession() {
		
		if(alive) {return;}
		
		alive = true;
		t = new Thread(this);
		t.start();
	}
	
	public synchronized void stopSession(){
		if(!alive) {return;}
		
		alive = false;
		
		
		try {
			connection.close();
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(connection.isAlive()) {
			String in = connection.read();
			if(in != null) {
				System.out.println(in);
				for(UserThread c : servidor.getClientes()) {
					c.send(in);
				}
			}
		}
	}
	
	public void send(String msg) {
		connection.write(msg+"\n");
		connection.flush();
	}
}
