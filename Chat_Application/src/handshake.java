import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class handshake {

	public void shake() throws Exception {
		DatagramSocket ds = new DatagramSocket();  
	    String str = "nooooo";  
	    InetAddress ip = InetAddress.getByName("127.0.0.1");  
	    int a = 10;
	    DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 3000);  
	    
	    ds.send(dp);  
	    ds.close();
		
	}

}
