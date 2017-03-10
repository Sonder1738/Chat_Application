import java.awt.Component;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


//http://mrbool.com/file-transfer-between-2-computers-with-java/24516


class Client implements Runnable{
	private Thread t;
	int portOut = 15678;
	static Socket MyClient;
	static Socket fileSendSocket;
	static String ip;
	boolean priv;
	String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	protected void clientIn(String ipOut, boolean b) throws IOException{
		
		
			MyClient = new Socket(ipOut, portOut);
			
			System.out.println("Connected to "+MyClient.getInetAddress().getHostAddress());
			
			//BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
			//String s;
			
			
			
				//System.out.print("Client : ");
				//s=stdin.readLine();
				//out.println(message); //fix this enabling user to chat ACROSS AND TO ITSELF
			
				
				
				
					
			
			
		
			
		
		
	   }
	public void run(){
		try {
			clientIn(ip,priv);
		} catch (IOException e) {
			Component frame = null;
			JOptionPane.showMessageDialog(frame,
				    "Unable to connect to "+ip,
				    "",
				    JOptionPane.PLAIN_MESSAGE);
			
			chatFrame cf = new chatFrame();
			cf.getMainFrame().dispose();
		}
		}
	
	
	
	
	public static Socket getMyClient() {
		return MyClient;
	}
	public static void setMyClient(Socket myClient) {
		MyClient = myClient;
	}
	
	
	public static Socket getFileSendSocket() {
		return fileSendSocket;
	}
	public static void setFileSendSocket(Socket fileSendSocket) {
		Client.fileSendSocket = fileSendSocket;
	}
	
	
	
	public void start(String string, boolean b) {
		this.ip=string;
		this.priv=b;
		System.out.println("Connecting..");
	      if (t == null) {
	         t = new Thread (this, "svr thrd");
	         t.start ();
	      }
		
	}
	public void msgOut() throws IOException {
		PrintStream out=new PrintStream(MyClient.getOutputStream());
		out.println(message);
		
	}
	public void sendFile(int port) throws Exception {
		
		fileSendSocket = new Socket(ip, port);
		System.out.println("sending file to "+fileSendSocket.getInetAddress().getHostAddress());
	}
	
}


/*

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;


//http://mrbool.com/file-transfer-between-2-computers-with-java/24516

public class Client {

	public static void main(String[] args) throws IOException{
			System.out.println("Starting server..");
			Server run = new Server();
			run.createSocketServer();
		try{
			System.out.println("Connecting..");
			Socket MyClient;
		    MyClient = new Socket("127.0.0.1", 15676);
		    System.out.println("Connected to " + MyClient.getInetAddress().getHostAddress());
		    BufferedReader sin=new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
			PrintStream sout=new PrintStream(MyClient.getOutputStream());
			BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
			String s;
			while (  true )
			{
				MyClient = new Socket("127.0.0.1", 15676);
				System.out.print("Client : ");
				s=stdin.readLine();
				sout.println(s);
				s=sin.readLine();
				System.out.print("Server : "+s+"\n");
				
				
	  			if ( s.equalsIgnoreCase("BYE") )
	 			   break;
			}
		}catch (Exception e){
			System.out.println("No server online");
			System.out.println("Client closing..");
		}
		
		
	}
	
}

*/


