import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class handshake implements Runnable{
	
	Thread t;
	public void shake() throws Exception {
		
			final  String INET_ADDR = "224.0.0.0";
			final int PORT = 8888;
		
		 
		        // Get the address that we are going to connect to.
		
		        InetAddress addr = InetAddress.getByName(INET_ADDR);
		
		      
		
		        // Open a new DatagramSocket, which will be used to send the data.
		while(true){
		        try (DatagramSocket serverSocket = new DatagramSocket()) {
		
		            
		        		InetAddress ip = InetAddress.getLocalHost();
		                String msg = "hi";
		                // Create a packet that will contain the data
		
		                // (in the form of bytes) and send it.
		
		                DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),msg.getBytes().length, addr, PORT);
		                serverSocket.send(msgPacket);
		                Thread.sleep(500);
		        	} catch (IOException ex) {
		
		            ex.printStackTrace();
		        	}

		
	}
	}
	public void start() {
		
		if (t == null) {
	         t = new Thread (this, "svr thrd");
	         t.start ();
	      }
		
	}

	@Override
	public void run() {
		try {
			shake();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
