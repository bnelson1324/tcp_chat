package tcp_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
	
	public ServerSocket serverSocket;
	ArrayList<ServerWorker> workerList = new ArrayList<ServerWorker>();
	
	public ChatServer() {
		startServer();
	}
	
	public void startServer() {
		new Thread( () -> {
			try {
				serverSocket = new ServerSocket(55555);
				// accepts any clients
				while(true) {
					Socket clientSocket = serverSocket.accept();
					distributeText("A user has connected.");
					System.out.println("user connected");
					workerList.add(new ServerWorker(clientSocket, this));  
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	// sends the text to all clients
	public void distributeText(String text) {
		for(ServerWorker worker : workerList) {
			PrintWriter pw = worker.printWriter;
			pw.println(text);
			pw.flush();
		}
	}
}
