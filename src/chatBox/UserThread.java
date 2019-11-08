package chatBox;

import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This thread handles connection for each connected client, so the server
 * can handle multiple clients at the same time.
 *
 * @author www.codejava.net
 */
public class UserThread extends Thread {
    private Socket serverSideSocket;
    private Socket clientSideSocket;
    private ChatServer server;
    private ChatClient client;
    private PrintWriter writer;
    private ChatWindow chatWindow;
    private boolean permission;
 
    public UserThread(Socket socket, ChatServer server, String username, boolean permission) {
        this.serverSideSocket = socket;
        this.server = server;
        this.permission = permission;
        //String user = null;
        if ((permission) && (username!=null) && (!(username.equals("null")))) {
        	System.out.println("usser name is: " + username);
        	chatWindow = new ChatWindow(this, username);
        }
        System.out.println("in first UserThread constructor");
    }
    
    /*public UserThread(Socket socket, ChatClient client, String username) {
    	this.clientSideSocket = socket;
    	this.client = client;
    	chatWindow = new ChatWindow(this, username);
    	System.out.println("in second UserThread constructor");
    }*/
 
    public void run() {
    	//if (this.server != null) {
	        try {
	            InputStream input = serverSideSocket.getInputStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	 
	            OutputStream output = serverSideSocket.getOutputStream();
	            writer = new PrintWriter(output, true);
	 
	            printUsers();
	 
	            String userName = reader.readLine();
	            server.addUserName(userName);
	 
	            String serverMessage = "New user connected: " + userName;
	            server.broadcast(serverMessage);
	            if (permission) {
	            	chatWindow.setNameOfChatter(userName);
	            	System.out.println("username frm readline: " + userName);
	            }
	 
	            String clientMessage;
	 
	            do {
	                clientMessage = reader.readLine();
	                serverMessage = "[" + userName + "]: " + clientMessage;
	                server.broadcast(serverMessage);
	 
	            } while (!clientMessage.equals("bye"));
	 
	            server.removeUser(userName, this);
	            serverSideSocket.close();
	 
	            serverMessage = userName + " has quitted.";
	            server.broadcast(serverMessage);
	 
	        } catch (IOException ex) {
	            System.out.println("Error in UserThread: " + ex.getMessage());
	            ex.printStackTrace();
	        }
    	//}
    	/*else if (this.client != null) {
    		try {
	            InputStream input = clientSideSocket.getInputStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	 
	            OutputStream output = clientSideSocket.getOutputStream();
	            writer = new PrintWriter(output, true);
	 
	            printUsers();
	 
	            String userName = reader.readLine();
	            server.addUserName(userName);
	 
	            String serverMessage = "New user connected: " + userName;
	            server.broadcast(serverMessage);
	            chatWindow.setNameOfChatter(userName);
	 
	            String clientMessage;
	 
	            do {
	                clientMessage = reader.readLine();
	                serverMessage = "[" + userName + "]: " + clientMessage;
	                server.broadcast(serverMessage);
	 
	            } while (!clientMessage.equals("bye"));
	 
	            server.removeUser(userName, this);
	            clientSideSocket.close();
	 
	            serverMessage = userName + " has quitted.";
	            server.broadcast(serverMessage);
	 
	        } catch (IOException ex) {
	            System.out.println("Error in UserThread: " + ex.getMessage());
	            ex.printStackTrace();
	        }
    	}*/
    }
    
    public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public ChatWindow getChatWindow() {
		return chatWindow;
	}

	public void setChatWindow(ChatWindow chatWindow) {
		this.chatWindow = chatWindow;
	}

	/**
     * Sends a list of online users to the newly connected user.
     */
    void printUsers() {
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }
 
    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        //writer.println(message);
        //chatWindow.console(message);
    	server.broadcast(message);
    }
}