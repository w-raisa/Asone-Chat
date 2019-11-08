package chatBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEnterMessage;
	private JTextArea chatHistoryArea;
	private String nameOfChatter;
	
	private UserThread userThread;

	//private ChatClient client;
	//private static Socket socket;
	
	/**
	 * Create the frame.
	 */
	public ChatWindow(UserThread userThread, String username) {
		createWindow(username); // to keep constructor clear
		//this.name = name;
		this.userThread = userThread;
		//client = new ChatClient(name);
		//client.execute();
	}
	
	public ChatWindow(String username) {
		createWindow(username);
	}
	
	public void createWindow(String username) {
		setTitle("Messenger");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		
		txtEnterMessage = new JTextField(); // HERE!!!!!!!!
		txtEnterMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if (!((txtEnterMessage.getText()).equals(""))) {
						//console(name + ": " + txtEnterMessage.getText() + "\n");
						userThread.sendMessage(nameOfChatter + ": " + txtEnterMessage.getText());
						txtEnterMessage.setText("");
					}
			    }
				
			}
		});
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtEnterMessage, 308, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtEnterMessage, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtEnterMessage, 357, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtEnterMessage, 435, SpringLayout.WEST, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		chatHistoryArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(chatHistoryArea);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scroll, 303, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scroll, 435, SpringLayout.WEST, contentPane);
		contentPane.add(scroll);
		contentPane.add(txtEnterMessage);
		txtEnterMessage.setColumns(10);
		
		txtEnterMessage.requestFocusInWindow(); // makes the blink cursor blink on the place we will type to send messages.
		
		JButton btnSend = new JButton("Send");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSend, 308, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSend, 440, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSend, 357, SpringLayout.NORTH, contentPane);
		btnSend.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if (!((txtEnterMessage.getText()).equals(""))) {
						//console(nameOfChatter + ": " + txtEnterMessage.getText() + "\n");
						userThread.sendMessage(nameOfChatter + ": " + txtEnterMessage.getText());
						txtEnterMessage.setText("");
					}
			    }
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!((txtEnterMessage.getText()).equals(""))) {
					//console(nameOfChatter + ": " + txtEnterMessage.getText() + "\n");
					userThread.sendMessage(nameOfChatter + ": " + txtEnterMessage.getText());
					txtEnterMessage.setText("");
				}
				txtEnterMessage.requestFocusInWindow();
			}
		});
		contentPane.add(btnSend);
		setVisible(true);
		
		txtEnterMessage.requestFocusInWindow();

	}
	
	/*public void letClientExecute() {
		client.execute();
	}*/
	
	public String getNameOfChatter() {
		return nameOfChatter;
	}

	public void setNameOfChatter(String nameOfChatter) {
		this.nameOfChatter = nameOfChatter;
	}

	public JTextArea getChatHistoryArea() {
		return chatHistoryArea;
	}

	public void setChatHistoryArea(JTextArea chatHistoryArea) {
		this.chatHistoryArea = chatHistoryArea;
	}

	public void console(String message) {
		chatHistoryArea.append(message);
	}
}
