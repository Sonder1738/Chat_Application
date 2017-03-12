import java.awt.Component;
import java.io.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	
	
	
	protected void serverIn() throws Exception
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
                        
                		byte[] buffer = new byte[8192];

                		BufferedInputStream in = new BufferedInputStream(filesend.getInputStream());
                		try (DataInputStream d = new DataInputStream(in)) {
                			
                		    String fileName = d.readUTF();
                		    Files.copy(d, Paths.get(fileName)); //specify path here under .get
            		        
                			}catch(Exception e){
                			//do nothing
                		}
                		
                		filereceive.close();
                		}else{
                		cf.printMsg(client.getInetAddress().getHostAddress()+": "+line);
                		}
                	
                	/*
                		int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
          JOptionPane.showMessageDialog(null, "HELLO");
        }
        else {
           JOptionPane.showMessageDialog(null, "GOODBYE");
           System.exit(0); //incase i need the dialog confirmation for receiving file
        }*/
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
		try {
			serverIn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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







