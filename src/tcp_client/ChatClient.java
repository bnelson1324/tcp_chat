package tcp_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import gui.AppFrame;

public class ChatClient {

	private Socket socket;
	public PrintWriter printWriter;
	private AppFrame parent;
	
	public ChatClient(AppFrame parent) {
		this.parent = parent;
	}
	
	public void connectTo(InetAddress address) {
		try {
			socket = new Socket(address, 55555);
			printWriter = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// submits text to server
	public void submitText(String text) {
		printWriter.println(text);
		printWriter.flush();
		System.out.println("Client sent: " + text);
	}
	
	// listens for any text to display from server
	public void listenForServerInput() {
		 new Thread( () -> {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while(true) {
					String line = reader.readLine();
					if(line == null) {
						continue;
					}
					parent.chatPanel.displayText(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
