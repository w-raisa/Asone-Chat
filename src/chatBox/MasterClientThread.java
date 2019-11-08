package chatBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

public class MasterClientThread extends Thread {

	private PrintWriter writer;
    private ChatClient client;
    private boolean suspended;
    
	private Socket socket;
	private Socket masterSocket;

	private OutputStream ostream;
	
    public MasterClientThread(ChatClient client) {
    	this.client = client;
    }
    
    public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ChatClient getClient() {
		return client;
	}

	public void setClient(ChatClient client) {
		this.client = client;
	}
	
	public Socket getMasterSocket() {
		return masterSocket;
	}

	public void setMasterSocket(Socket masterSocket) {
		this.masterSocket = masterSocket;
	}
    
    public void run() {
    	this.client.masterExecute();
    	this.masterSocket = this.client.getMasterSocket();	
    	writeUsernameToServer();
    }
    
    public void writeUsernameToServer() {
    	String username = this.client.getUsername();
    	
        ostream = null;
		try {
			ostream = (this.masterSocket).getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}                 
        DataOutputStream dos = new DataOutputStream(ostream);                                
	    try {
	    	dos.writeBytes(username + "\r\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    public void writeFriendNameToServer() {
    	String friendName = "Hansel";
    	
        ostream = null;
		try {
			ostream = (this.masterSocket).getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}                 
        DataOutputStream dos = new DataOutputStream(ostream);                                
	    try {
	    	dos.writeBytes(friendName + "\r\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    
    
}

