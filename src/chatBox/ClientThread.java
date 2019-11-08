package chatBox;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread {
	private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
 
    public ClientThread(ChatClient client) {
    	this.client = client;
    	this.socket = socket;
    }
    
    public void run() {
    	this.client.masterExecute();
    }
}
