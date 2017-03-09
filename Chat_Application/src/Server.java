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
    int portIn = 15678;
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
                chatFrame cf = new chatFrame();
                
                while(true){
                	line=input.readLine();
                	
                	if(line.equalsIgnoreCase("gbye1738")){
                		String disconnectline=" has disconnected";
                		cf.printMsg(client.getInetAddress().getHostAddress()+disconnectline);
                		System.out.println(client.getInetAddress().getHostName()+" disconnected");
                		break;
                	}else if(line.equalsIgnoreCase("fsendnow")){
                		
                		byte[] buffer = new byte[8192];

                		FileOutputStream fos = new FileOutputStream("a.png");
                		BufferedOutputStream out2 = new BufferedOutputStream(fos);
                		
                		int count;
                		InputStream in = client.getInputStream();
                		while((count=in.read(buffer)) >0){
                			fos.write(buffer, 0, count);
                		}
                		fos.close();
                		System.out.println("DONE?");
                		}else{
                		cf.printMsg(client.getInetAddress().getHostAddress()+": "+line);
                	}
                	
                	
                	//System.out.println(client.getInetAddress().getHostAddress()+" : "+line); //sends to the FRAME TO UPDATE?
                	
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
	
public void start() { //doesnt get called??
		
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







