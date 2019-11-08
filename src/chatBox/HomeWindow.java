package chatBox;
//import masterServer.MasterServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.event.ActionEvent;

public class HomeWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MasterClientThread runningClient;

	//private ChatClient client;
	//private static Socket socket;

	/**
	 * Create the frame.
	 */
	public HomeWindow(MasterClientThread runningClient, ChatClient chatClient) {
		this.runningClient = runningClient;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		System.out.println("In HomeWindow");
		
		//createButtons(chatClient);
		JButton btnConnectToA = new JButton("Hansel");
		//MultipleChatThread multipleChatThread = new MultipleChatThread(runningClient, chatClient, this, contentPane);
		//multipleChatThread.start();
		btnConnectToA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // VERY IMPORTANT NOTE: FOR EACH FRIEND YOU HAVE, YOU WILL GENERATE A NEW BUTTON, AND THEREBY, A NEW THREAD. SO NO NEED TO HANDLE MULTIPLE THREADS IN HERE.
				// create a new server for chatting.
				// Master Server needs to send a message to the client clicked on (can keep track thru usernames).
				// create a new class, a server that is specifically made for these clients to connect to and chat with each other. 
				//runningClient.resumeThread();
				runningClient.writeFriendNameToServer();
				//MultipleChatThread multipleChatThread = new MultipleChatThread(chatClient);
				//multipleChatThread.start();
				chatClient.chatSetUp();
			}
		});
		contentPane.add(btnConnectToA, BorderLayout.CENTER);
		
		
		
		setVisible(true);
		
		MultipleChatThread multipleChatThread = new MultipleChatThread(chatClient);
		multipleChatThread.start();
	}
	
	/*public void createButtons(ChatClient chatClient) {
		JButton btnConnectToA = new JButton("Hansel");
		btnConnectToA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runningClient.writeFriendNameToServer();
				

			}
		});
		contentPane.add(btnConnectToA, BorderLayout.CENTER);
		
	}*/

	public MasterClientThread getRunningClient() {
		return runningClient;
	}

	public void setRunningClient(MasterClientThread runningClient) {
		this.runningClient = runningClient;
	}
	
}
