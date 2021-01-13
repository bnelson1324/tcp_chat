package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import tcp_client.ChatClient;

public class AppFrame extends JFrame {

	public MenuPanel menuPanel;
	public ChatPanel chatPanel;
	public ChatClient chatClient;
	
	public JTabbedPane tabbedPane;
	
	public static void main(String[] args) {
		new AppFrame();
	}
	
	public AppFrame() {
		// creates ui
		super("TCP Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
		
		tabbedPane = new JTabbedPane();
		add(tabbedPane);
		menuPanel = new MenuPanel(this);
		chatPanel = new ChatPanel(this);
		tabbedPane.add(menuPanel, "Menu");
		tabbedPane.add(chatPanel, "Chat");
	}
	
}
