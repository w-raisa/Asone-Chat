package chatBox;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MultipleChatThread extends Thread {
	
	private ChatClient chatClient;
	private HomeWindow homeWindow;
	private MasterClientThread mct;
	private JPanel contentPane;
	
	public MultipleChatThread(ChatClient chatClient) {
		this.chatClient = chatClient;
		/*this.mct = mct;
		this.homeWindow = homeWindow;
		this.contentPane = contentPane;*/
	}
	
	public void run() {
		/*JButton btnConnectToA = new JButton("Hansel");
		btnConnectToA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // VERY IMPORTANT NOTE: FOR EACH FRIEND YOU HAVE, YOU WILL GENERATE A NEW BUTTON, AND THEREBY, A NEW THREAD. SO NO NEED TO HANDLE MULTIPLE THREADS IN HERE.
				// create a new server for chatting.
				// Master Server needs to send a message to the client clicked on (can keep track thru usernames).
				// create a new class, a server that is specifically made for these clients to connect to and chat with each other. 
				//runningClient.resumeThread();
				mct.writeFriendNameToServer();
				//MultipleChatThread multipleChatThread = new MultipleChatThread(chatClient);
				//multipleChatThread.start();
				chatClient.chatSetUp();
				

			}
		});
		contentPane.add(btnConnectToA, BorderLayout.CENTER);*/
		
		System.out.println("in run version of chatSetUp");
    	InputStream istream = null;
		try {
			System.out.println(" before 1 run");
			istream = (this.chatClient.getMasterSocket()).getInputStream();
			System.out.println("1 run");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	    DataInputStream dstream = new DataInputStream(istream);
	    String request = null;
	    System.out.println("2");
	    try {
	    	System.out.println("in try block 2 run");
	    	request = dstream.readLine();
	    	System.out.println("3 run");
	    	System.out.println("after reading run");
	    	System.out.println("run recieved... : " + request);
	    	if (request!=null) {
	    		this.chatClient.execute();
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

	public ChatClient getChatClient() {
		return chatClient;
	}

	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}
	
}
