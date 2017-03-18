import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class multicast implements Runnable{

	private Thread t;
	
	
	LinkedList<InetAddress> ipList = new LinkedList<InetAddress>();
	LinkedList<InetAddress> tempList = new LinkedList<InetAddress>();
	boolean duplicate;
	@Override
	public void run() {
		try {
			listener();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listener() throws UnknownHostException{
		
		final String INET_ADDR = "224.0.0.0";
			final String farewell = "bye";
		    final int PORT = 8888;
		    // Get the address that we are going to connect to.
		
		        InetAddress address = InetAddress.getByName(INET_ADDR);
		        // Create a buffer of bytes, which will be used to store
		
		        // the incoming bytes containing the information from the server.
		
		        // Since the message is small here, 256 bytes should be enough.
		
		        byte[] buf = new byte[256];
		
		         
		
		        // Create a new Multicast socket (that will allow other sockets/programs
		
		        // to join it as well.
		
		        try (MulticastSocket clientSocket = new MulticastSocket(PORT)){
		
		            //Joint the Multicast group.
		
		            clientSocket.joinGroup(address);
		            
		            while (true) {
		
		                // Receive the information and print it.
		            	duplicate=false;
		                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
		                InetAddress localip = InetAddress.getLocalHost();
		                clientSocket.receive(msgPacket);
		                //String msg = new String(buf, 0, buf.length); //gets the entire buffer length..so NOPE
		                String msg = new String(msgPacket.getData(), msgPacket.getOffset(), msgPacket.getLength());
		                
		                if(msgPacket.getAddress().equals(localip)){
		                	
		                	//System.out.println("This is conencted to itself"); always gets printed
		                	
		                }else{
		                	if(msg.equals(farewell)){
		                		System.out.println(msgPacket.getAddress()+" SEE YA");
		                		try{
		                			ipList.removeFirst(); //MAYBE MODIFY THIS TO REMOVE SPECIFIC IP DISCONNECTED
		                		}catch(Exception e){
		                			
		                		}
		                		
		                		home h = new home();
						        h.setList(ipList);
		                	}else{
		                	//System.out.println(msgPacket.getAddress()+" Connected"); //always runs waiting for connections
					        //testing popup
		                	for(int i=0;i<ipList.size();i++){
		                		if(msgPacket.getAddress().equals(ipList.get(i))){
		                			duplicate=true;
		                		}else{
		                			duplicate=false;
		                		}
		                	}
		                	if(duplicate==false){
		                		System.out.println("Only once?");
		                		ipList.add(msgPacket.getAddress());
						        home h = new home();
						        h.setList(ipList);
		                	}
					        
				            }
		                }
		                
		               
		                }
		        	}catch(IOException ex) {
		        		ex.printStackTrace();
		        		}
		
		
	}
	
	
	public void start() {
		if (t == null) {
	         t = new Thread (this, "svr thrd");
	         t.start ();
	      }
		
	}

}


/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ USER ONLINE DYNAMIC TABLE @@@@@@@@@@@@@@@@@@@@@@@
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.Random;
public class JListTest {

    
    public static void main(String args[]) throws InterruptedException {
    	 // create object of table and table model
    	 JTable tbl = new JTable();
    	 DefaultTableModel dtm = new DefaultTableModel(0, 0);
    	 JFrame frame;
    	 frame = new JFrame("DERICK CHU FYP");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLayout(new BorderLayout());
         frame.setSize(420, 320);
         
         frame.setLocationRelativeTo(null);
         frame.setVisible(true);
    	// add header of the table
    	String header[] = new String[] {""};

    	// add header in table model     
    	 dtm.setColumnIdentifiers(header);
    	  //set model into the table object
    	       tbl.setModel(dtm);
    	       frame.add(tbl);
    	       
    	     // add row dynamically into the table      
    	      

    	       Random rand = new Random();

    	      
    	       
    	       	for(int i =0;i<50;i++){
    	       	 int  n = rand.nextInt(2) + 1;
    	       	 Thread.sleep(1000);
    	       	 System.out.println(n);
    	       		if(n==1){
    	       			System.out.println("HERE"+n);
    	       			dtm.addRow(new Object[] {"Data"});
    	       			
    	       		}
    	       	}
    	     
    	        
    	 
    }
}
*/