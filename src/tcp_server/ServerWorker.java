package tcp_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerWorker {

	/** a ServerWorker is created by the server for each client that joins */
	
	private Socket clientSocket;
	public PrintWriter printWriter;
	
	private ChatServer serverMain;
	
	public ServerWorker(Socket clientSocket, ChatServer serverMain) {
		try {
			printWriter = new PrintWriter(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.clientSocket = clientSocket;
		this.serverMain = serverMain;		
		
		new Thread ( () -> {
			try {
				handleClientSocketInput(this.clientSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();;
	}
	
	private void handleClientSocketInput(Socket clientSocket) throws IOException {		
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String line;
		while((line = reader.readLine()) != null) {
			serverMain.distributeText(line);
			System.out.println("Server received: " + line);
		}
	}
	
}
