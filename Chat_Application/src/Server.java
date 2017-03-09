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
	Socket client;
    int portIn = 15678;
    private Thread t;
    boolean isRunning=false;
	Thread server;
	
	ServerSocket filereceive;
	Socket filesend;
	int portFileIn = 15679;
	
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
                		
                		
                		filereceive = new ServerSocket(portFileIn);
                        filesend = filereceive.accept();
                        System.out.println(filesend.getInetAddress().getHostAddress() +" connected ");
                		
                		byte[] buffer = new byte[8192];

                		FileOutputStream fos = new FileOutputStream("b.png");
                		BufferedOutputStream out2 = new BufferedOutputStream(fos);
                		
                		int count;
                		InputStream in = filesend.getInputStream();
                		while((count=in.read(buffer)) >0){
                			fos.write(buffer, 0, count);
                		}
                		fos.close();
                		System.out.println("DONE?");
                		filereceive.close();
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







