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
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

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
	
	private static SecretKeySpec secretKey;
    private static byte[] key;
	
	protected void serverIn() throws Exception
    {
		
		try{
			while (true){
            	System.out.println("Server listening..");
            	if(isRunning!=true){
            	ssock = new ServerSocket(portIn);
            	isRunning=true;
            	}
            	
                client = ssock.accept();
                System.out.println(client.getInetAddress().getHostAddress() +" connected");
                
                PrintWriter out = new PrintWriter(client.getOutputStream(),true);
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;
                home cf = new home();
                cf.printMsg(client.getInetAddress().getHostAddress() +" has connected");
                while(true){
                	
                	DateFormat df = new SimpleDateFormat("h:mm:ss a");
                    Date dateobj = new Date();
                    System.out.println(df.format(dateobj));
                    
                	line=input.readLine();
                	System.out.println(line);
                	if(line.equalsIgnoreCase("gbye1738")){
                		String disconnectline=" has disconnected";
                		cf.printMsg(client.getInetAddress().getHostAddress()+disconnectline);
                		System.out.println(client.getInetAddress().getHostName()+" disconnected");
                		break;
                		}else if(line.equalsIgnoreCase("fsendnow")){
                		home hf = new home();
                		hf.getFrame().setAlwaysOnTop(false);
                		filereceive = new ServerSocket(portFileIn);
                        filesend = filereceive.accept();
                        
                		byte[] buffer = new byte[8192];

                		BufferedInputStream in = new BufferedInputStream(filesend.getInputStream());
                		try (DataInputStream d = new DataInputStream(in)) {
                			
                		    String fileName = d.readUTF();
                		    
                		    int reply = JOptionPane.showConfirmDialog(null, "Do you want to receive the file : "+fileName+" ?", "Incoming file", JOptionPane.YES_NO_OPTION);
                	        if (reply == JOptionPane.YES_OPTION) {
                	          JOptionPane.showMessageDialog(null, "Receiving file..");
                	          Files.copy(d, Paths.get(fileName));//specify path here under .get
                	          JOptionPane.showMessageDialog(null, "File Received");
                	          filereceive.close();
                	          hf.getFrame().setAlwaysOnTop(true);
                	        }else{
                	        	JOptionPane.showMessageDialog(null, "File was not Received");
                	        	filereceive.close();
                	        	hf.getFrame().setAlwaysOnTop(true);
                	        	}
                		 	}catch(Exception e){
                			//do nothing
                		}
                		
                		
                		}else if(line.contains("=") || line.contains("/")){ //means decrypted
                			/*BufferedInputStream in = new BufferedInputStream(client.getInputStream());
                    		try (DataInputStream d = new DataInputStream(in)) {
                    			
                    			String friendlyName =  d.readUTF();
                    			System.out.println(friendlyName);
                    		}catch(Exception e){
                    			
                    		}
                    		*/
                			String decrypted = decrypt(line, "mx6unB3MZNEZOgLiTrLC");
                			cf.printMsg(df.format(dateobj)+"    "+decrypted);
                			
                		}else{
                		cf.printMsg(df.format(dateobj)+"    "+line);
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
//////////////////////////////////////ENCRYPT STUFF//////////////////////////////////////////
public static void setKey(String myKey) 
{
    MessageDigest sha = null;
    try {
        key = myKey.getBytes("UTF-8");
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); 
        secretKey = new SecretKeySpec(key, "AES");
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
}

public static String decrypt(String strToDecrypt, String secret) 
{
    try
    {
        setKey(secret);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    } 
    catch (Exception e) 
    {
    	if(strToDecrypt.contains("=")||strToDecrypt.contains("/")){
    		return strToDecrypt;
    	}
        System.out.println("Error while decrypting: " + e.toString());
    }
    return null;
}
		
//////////////////////////////////////ENCRYPT STUFF//////////////////////////////////////////	


}
class serverOut extends Thread{
	
}
class serverIn extends Thread{
	
}

