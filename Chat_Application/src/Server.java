import java.io.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable{

	
	ServerSocket ssock;
    int portIn = 15676;
    private Thread t;
    Socket client;
	boolean isRunning=false;
	Thread server;


	
	protected void serverIn()
    {
		try
        {
            while (true){
            	System.out.println("Server listening..");
            	if(isRunning!=true){
            	ssock = new ServerSocket(portIn);
            	isRunning=true;
            	}
            	
                client = ssock.accept();
                System.out.println(client.getInetAddress().getHostAddress() +" connected ");
                
                PrintWriter out = new PrintWriter(client.getOutputStream(),true);
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;
                
                while(true){
                	line=input.readLine();
                	
                	System.out.println(client.getInetAddress().getHostAddress()+" : "+line);
                	if(line.equalsIgnoreCase("BYE")){
                		System.out.println(client.getInetAddress().getHostName()+" disconnected");
                		break;
                	}
                  
                  }
               
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.out.println(client.getInetAddress().getHostAddress()+" Disconnected");
        }
    }
	


	
	public void run(){
		serverIn();
		
		}
	
public void start() {
		
		System.out.println("Starting Server Thread");
	      if (t == null) {
	         t = new Thread (this, "svr thrd");
	         t.start ();
	      }
		
	}


	
		
		
}
class serverOut extends Thread{
	
}
class serverIn extends Thread{
	
}







