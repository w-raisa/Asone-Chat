package masterServer;
import chatBox.ChatClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Set;
import chatBox.ChatServerThread;
import chatBox.ChatWindow;

//import chatBox.ChatServer;

public class MasterServerThread extends Thread {

	private Socket socket;
	private MasterServer masterServer;
	private ChatClient masterClient = null;
	private String username;
	
	public MasterServerThread(Socket socket, MasterServer masterServer) {
		this.socket = socket;
		this.masterServer = masterServer;
	}

	public MasterServer getMasterServer() {
		return masterServer;
	}

	public void setMasterServer(MasterServer masterServer) {
		this.masterServer = masterServer;
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// update home page here accordingly
		// status, availability n all.
		//this.masterServer.iterateDict();
		InputStream istream = null;
		try {
			istream = (this.socket).getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    DataInputStream dstream = new DataInputStream(istream);
	 
	    username = null;
	    try {
	    	username = dstream.readLine();
	    	System.out.println(username + " on server side! (username)");
	    	this.masterServer.addUserName(username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // pause thread to read in from client.
		String friendUsername = null;
	    try {
	    	friendUsername = dstream.readLine();
	    	System.out.println(friendUsername + " on server side!!!!! (friendUsername)");
	    	findFriendsThread(friendUsername);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
			dstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	    try {
			istream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    //this.masterServer.addUserName(username);
	    
	}
	
	public void findFriendsThread(String friendsUsername) {
    	int index = 0;
    	System.out.println("In findFriendsThread");
    	Set<String> userNames = this.masterServer.getUserNames();
    	for (String connectedUser : userNames) {
    		System.out.println("username: " + userNames);
    		if (connectedUser.equals(friendsUsername)) {
    			findThread(index);
    			break;
    		}
    		index++;
    	}
    }
	
	public void findThread(int index) {
    	Set<MasterServerThread> userThreads = this.masterServer.getUserThreads();
    	int i = 0;
    	System.out.println("In findThread");
    	for (MasterServerThread thread : userThreads) {
    		if (i == index) {
    			Socket friendsSocket = thread.getSocket();
    			setUpServerSide();
    			setUpClientSide(friendsSocket);
    			break;
    		}
    		i++;
    	}
	}
	
	public void setUpServerSide() {
		System.out.println("In setUpServerSide");
		ChatServerThread cst = new ChatServerThread(username);
		cst.start();
		//ChatWindow chatWindow = new ChatWindow(username);
		
	}
	
	public void setUpClientSide(Socket clientSideSocket) {
		OutputStream ostream = null;
		try {
			ostream = (clientSideSocket).getOutputStream();
			System.out.println("In set up client side");
		} catch (IOException e) {
			e.printStackTrace();
		}
        DataOutputStream dos = new DataOutputStream(ostream);                                
	    try {
	    	System.out.println("writing in set up client side");
	    	dos.writeBytes("request" + "\r\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
}
