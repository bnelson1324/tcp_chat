package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatPanel extends JPanel {
	
	private JTextField tfUserInput;
	public JTextArea taChatBox;
	private AppFrame parent;
	
	public ChatPanel(AppFrame parent) {
		this.parent = parent;
		
		taChatBox = new JTextArea();
		taChatBox.setEnabled(true);
		taChatBox.setEditable(false);
		taChatBox.setText("");
		
		tfUserInput = new JTextField();
		// handle text from tfUserInput
		tfUserInput.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// send text to server if user presses enter and text box isn't empty
				if(e.getKeyCode() == KeyEvent.VK_ENTER && !tfUserInput.getText().contentEquals("")) {
					parent.chatClient.submitText(tfUserInput.getText());
					tfUserInput.setText("");
				}
			}
		});
		
		tfUserInput.setToolTipText("Say something");
		tfUserInput.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(taChatBox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
						.addComponent(tfUserInput, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
					.addGap(208))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(88)
					.addComponent(taChatBox, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(tfUserInput, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(58))
		);
		setLayout(groupLayout);
	}
	
	public void displayText(String text) {
		taChatBox.append(text + '\n');
	}
	
	
	
	
}
