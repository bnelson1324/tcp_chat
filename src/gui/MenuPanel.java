package gui;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import tcp_client.ChatClient;
import tcp_server.ChatServer;

public class MenuPanel extends JPanel {
	
	private JTextField tfJoin;
	private JButton btnHost, btnJoin;
	private AppFrame parent;
	
	public MenuPanel(AppFrame parent) {
		this.parent = parent;
		
		btnHost = new JButton("Host chatroom");
		btnJoin = new JButton("Join chatroom by IP");
		setUpButtons();
		
		tfJoin = new JTextField();
		tfJoin.setToolTipText("Put chatroom IP here");
		tfJoin.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(btnHost)
					.addPreferredGap(ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
					.addComponent(btnJoin, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(78))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(410, Short.MAX_VALUE)
					.addComponent(tfJoin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(134))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(212)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnHost)
						.addComponent(btnJoin))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfJoin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(219, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	private void setUpButtons() {
		btnHost.addActionListener( (e) -> {
			ChatServer server = new ChatServer();
			while(server.serverSocket == null) {
				System.out.println("waiting for server to start");
			}
			System.out.println("server started");
		});
		
		btnJoin.addActionListener( (e) -> {
			try {
				parent.chatClient = new ChatClient(parent);
				String targetIP = tfJoin.getText();
				if(targetIP.equals("")) {
					targetIP = "127.0.0.1";
				}
				parent.chatClient.connectTo(InetAddress.getByName(targetIP));
				parent.chatClient.listenForServerInput();
				parent.tabbedPane.setSelectedComponent(parent.chatPanel);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
		});
	}
}
