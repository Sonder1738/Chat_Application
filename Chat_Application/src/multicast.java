import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class multicast implements Runnable{

	private Thread t;

	@Override
	public void run() {
		listener();
	}

	private void listener() {
		
		try{
			while(true){
		DatagramSocket ds = new DatagramSocket(3000);  
        byte[] buf = new byte[1024];  
        DatagramPacket dp = new DatagramPacket(buf, 1024);  
        ds.receive(dp);  
        System.out.println(dp.getAddress());
        String str = new String(dp.getData(), 0, dp.getLength());  
        if(str.equals("hi")){
        	System.out.println("HELLO");
        }
        System.out.println(str);  
        ds.close();  
			}
    	
	}catch(Exception e){
		System.out.println(e); //fix issue with same bind?
	}
	}
	
	public void start() {
		if (t == null) {
	         t = new Thread (this, "svr thrd");
	         t.start ();
	      }
		
	}

}
