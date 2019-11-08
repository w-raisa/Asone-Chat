package chatBox;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ChatServerThread extends Thread {

	private ChatServer sharedServer;
	private MasterClientThread clientRequesting;
	private String username;
	
	/*public ChatServerThread(MasterClientThread clientRequesting) { // for previous ideas.
		this.clientRequesting = clientRequesting;
	}*/
	
	public ChatServerThread(String username) { 
		this.username = username;
	}
	
	public void run() {
		sharedServer = new ChatServer(55103, this.username);
		sharedServer.execute();
	}

	public ChatServer getSharedServer() {
		return sharedServer;
	}

	public void setSharedServer(ChatServer sharedServer) {
		this.sharedServer = sharedServer;
	}
	
}
