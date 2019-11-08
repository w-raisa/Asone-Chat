package chatBox;

import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This is the chat server program.
 * Press Ctrl + C to terminate the program.
 *
 * @author www.codejava.net
 */
public class ChatServer {
    private int port;
    private Set<String> userNames = new LinkedHashSet<>();
    private Set<UserThread> userThreads = new LinkedHashSet<>();
    private String username;
    private UserThread newUser;

	public ChatServer(int port, String username) {
        this.port = 55103;
        this.username = username;
        addUserName(username);
    }
 
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Chat Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
                
                for (String username : userNames) {
                	System.out.println("Username in userNames: " + username);
                }
 
                newUser = new UserThread(socket, this, this.username, true);
                System.out.println("created a UserThread object with name: " + this.username);
                userThreads.add(newUser);
                newUser.start();
 
            }
 
        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    /*public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntax: java ChatServer <port-number>");
            System.exit(0);
        }
 
        int port = Integer.parseInt(args[0]);
 
        ChatServer server = new ChatServer(port);
        server.execute();
    }*/
 
    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void broadcast(String message) {
        for (UserThread aUser : userThreads) {
            //if (aUser != excludeUser) {
                //aUser.sendMessage(message);
        	if (newUser.isPermission()) {
                aUser.getChatWindow().console("\n" + message);
        	}
            //}
        }
    }
 
    /**
     * Stores username of the newly connected client.
     */
    void addUserName(String userName) {
        userNames.add(userName);
    }
 
    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
 
    public Set<UserThread> getUserThreads() {
		return userThreads;
	}

	public void setUserThreads(Set<UserThread> userThreads) {
		this.userThreads = userThreads;
	}

	public void setUserNames(Set<String> userNames) {
		this.userNames = userNames;
	}
    
    Set<String> getUserNames() {
        return this.userNames;
    }
 
    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}