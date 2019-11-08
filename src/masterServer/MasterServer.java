package masterServer;
import chatBox.ChatClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

//import chatBox.UserThread;

public class MasterServer {
    private int port;    
    
    Hashtable<MasterServerThread, String> h = new Hashtable<MasterServerThread, String>();
    
    /*private Set<String> userNames = new HashSet<>();
    private Set<MasterServerThread> userThreads = new HashSet<>();
    private Set<Socket> userSockets = new HashSet<>();
    private Set<ChatClient> masterClients = new HashSet<>();*/
    
    private Set<String> userNames = new LinkedHashSet<>();
    private Set<MasterServerThread> userThreads = new LinkedHashSet<>();
	private Set<Socket> userSockets = new LinkedHashSet<>();
    private Set<ChatClient> masterClients = new LinkedHashSet<>();
    private Socket masterSocket;

	public MasterServer(int port) {
        this.port = 55002;
    }
 
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Chat Server is listening on port " + port);
 
            while (true) {
                masterSocket = serverSocket.accept();
                
                System.out.println("New user connected");
 
                MasterServerThread newUser = new MasterServerThread(masterSocket, this); //allows us to accept multiple clients.
                userThreads.add(newUser);
                newUser.start();
            }
 
        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        /*if (args.length < 1) {
            System.out.println("Syntax: java ChatServer <port-number>");
            System.exit(0);
        }
 
        int port = Integer.parseInt(args[0]);*/
 
        MasterServer server = new MasterServer(55002);
        server.execute();
    }
 
    /**
     * Delivers a message from one user to others (broadcasting)
     */
    /*void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }*/
    
    /**
     * Stores username of the newly connected client.
     */
    public void addUserName(String userName) {
        userNames.add(userName);
        for (String un : userNames) {
        	System.out.println("In HashSet: " + un);
        }
    }
    
    public void addSocket(Socket socket) {
    	userSockets.add(socket);
    }
    
    public void addMasterClient(ChatClient masterClient) {
    	masterClients.add(masterClient);
    }
 
    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    public void removeUser(String userName, MasterServerThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
 
    public Set<String> getUserNames() {
        return this.userNames;
    }
    
    public void setUserNames(Set<String> userName) {
        this.userNames = userNames;
    }
    
    public Set<Socket> getUserSockets() {
		return userSockets;
	}

	public void setUserSockets(Set<Socket> userSockets) {
		this.userSockets = userSockets;
	}
	
	public Socket getSocket() {
		return this.masterSocket;
	}

	public void setSocket(Socket socket) {
		this.masterSocket = socket;
	}
	
    public Set<MasterServerThread> getUserThreads() {
		return userThreads;
	}

	public void setUserThreads(Set<MasterServerThread> userThreads) {
		this.userThreads = userThreads;
	}
 
    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    public boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}
