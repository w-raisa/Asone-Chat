package chatBox;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	
	private String firstName;
	private String lastName;
	private String gender;
	private String password;
	private String username;
	private String securityQuestion;
	private String securityAnswer;
	private String availability;
	private String status;
	private String[] friends;

	private Socket socket;
	private Socket masterSocket;
	
	public ChatClient(String username) {
		this.username = username;
	}
	
	public ChatClient(String firstName, String lastName, String gender, 
			String username, String password, String securityQuestion, String securityAnswer) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.password = password;
		this.username = username;
		this.securityQuestion = securityQuestion; 
		this.securityAnswer = securityAnswer;
	}
	
	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Socket getMasterSocket() {
		return masterSocket;
	}

	public void setMasterSocket(Socket masterSocket) {
		this.masterSocket = masterSocket;
	}
	
	public String[] getFriends() {
		return friends;
	}

	public void setFriends(String[] friends) {
		this.friends = friends;
	}
	
    public void execute() {
        try {
            socket = new Socket("127.0.0.1", 55103);
            System.out.println("(in regular execute) Connected to the chat server");
            //ChatWindow chatWindow = new ChatWindow(this.username);
 
            //new UserThread(socket, this, this.username).start();
            
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();
 
        } catch (UnknownHostException ex) {
            System.out.println("(in regular execute) Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("(in regular execute) I/O Error: " + ex.getMessage());
        }
 
    }
    
    public void masterExecute() {
    	try {
			this.masterSocket = new Socket("127.0.0.1", 55002);
			System.out.println("(In masterExecute) Connected to the chat server");
			
			// create and run a thread.
			// in the thread, the client will listen at all times to the Master Server for 
			// incoming chat requests.
			
		} catch (UnknownHostException ex) {
            System.out.println("(In masterExecute) Server not found: " + ex.getMessage());
        } catch (IOException ex) {
        	System.out.println("(In masterExecute) I/O Error: " + ex.getMessage());
		}
        
    }
    
    public void chatSetUp() {
    	// always listen to MasterServerThread speaking.
    	// if you receive a message from MasterServerThread, then run execute().
    	System.out.println("in ChatSetUp");
    	InputStream istream = null;
		try {
			System.out.println(" before 1");
			istream = (this.masterSocket).getInputStream();
			System.out.println("1");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	    DataInputStream dstream = new DataInputStream(istream);
	    String request = null;
	    System.out.println("2");
	    try {
	    	System.out.println("in try block 2");
	    	request = dstream.readLine();
	    	System.out.println("3");
	    	System.out.println("after reading");
	    	System.out.println("recieved... : " + request);
	    	execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
}
