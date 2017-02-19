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


//http://mrbool.com/file-transfer-between-2-computers-with-java/24516


class Client implements Runnable{
	private Thread t;
	int portOut = 15678;
	Socket MyClient;
	
	
	protected void clientIn() throws IOException{
		
		try{
			MyClient = new Socket("127.0.0.1", portOut);
		}catch(Exception e){
			System.out.println("No server available");
		}
		System.out.println("Connected to "+MyClient.getInetAddress().getHostAddress());
		PrintStream out=new PrintStream(MyClient.getOutputStream());
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (  true )
		{
			//System.out.print("Client : ");
			s=stdin.readLine();
			
			out.println(s);
			
			if(s.equalsIgnoreCase("BYE")){
				System.out.println("A"); //server is still running thats why it doesnt end
				break;
			}
				
		}
	   }
	public void run(){
		try {
			clientIn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public void start() {
		System.out.println("Connecting..");
	      if (t == null) {
	         t = new Thread (this, "svr thrd");
	         t.start ();
	      }
		
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


