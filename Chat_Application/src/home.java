
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import java.awt.GridLayout;

public class home{

	
	DefaultTableModel dtm = new DefaultTableModel(0, 0);
	private JFrame frame;
	int index;
	public JTable table;
	private InetAddress bla;
	static LinkedList<InetAddress> ipList = null;
	static LinkedList<InetAddress> oldList =null; //maybe make null
	static LinkedList<InetAddress> templist;
	static int i=0;
	/**
	 * @wbp.parser.entryPoint
	 */
	
		void initialize() {
		
		
		/*
		 * 
		 * 
		 * Server s = new Server();
		s.start();
		Thread.sleep(5000);
		Client c = new Client();
		c.start();
		 */
		//Server s = new Server(); //starts server when logged in? or when want to chat??
		//s.start();
		handshake hs = new handshake();
		try {
			hs.shake();
			}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	    
	    
		Serialize serial = new Serialize();
		LinkedList<clients> clients= new LinkedList();
		LinkedList templist = serial.deserialize("src/clients.ser");
		clients = templist;
		String b = clients.get(index).getId(); // CHANGE TO LOOP
		System.out.println(b); //prints user login id currently
		
		frame = new JFrame("DERICK CHU FYP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(new homePane()); //is this even needed?
        frame.setLocationRelativeTo(null);
        
        String header[] = new String[] {""};

        //add header in table model     
    	dtm.setColumnIdentifiers(header);
    	//dtm.addRow(new Object[] {"ASDFGG-PC/ 192.168.1.1"}); add stuff here
    	
    	//table timer check periodically
    	multicast asdf = new multicast();
    	
    	ActionListener actListner = new ActionListener() 
	    {
	        public void actionPerformed(ActionEvent event) 
	        {
	        	//RETAINS VALUE INTERESTING
	        	//HOW TO CHECK FOR DUPLICATE
	        	//MAYBE DONT NEED SINCE ONLY ONE CONNECTS?
	        	//CHECK FOR DISCONNECT INSTEAD?
	        	
	        	//templist
	        	ipList = getList();
	        	
	        	DefaultTableModel asd = new DefaultTableModel(0, 0);
	        	String header[] = new String[] {""};
	        	asd.setColumnIdentifiers(header);
	        	if(ipList==null){
	        	
	        	}else{
	        		for(int i =0;i<ipList.size();i++){
		        		asd.addRow(new Object[] {ipList.get(i)});
	        	}
	        	}
	        	table.setModel(asd);
	        	
	        	
	        	
	        	//ADD LOGIC NO DUPLICATES
	        	
	        	//if(asdf.getIpList()==null){
	        		//System.out.println(ipList);
	        		//ipList=asdf.getIpList();
	        	//}else{
	        		//System.out.println("ACTION"+ipList);
	        	//}
	        	
	        	
	        	/*
	        	 * if(ipList==null){
	        		System.out.println("NULL ONCE");
	        		ipList =repaint(templist);
	        		}else{
	        		
	                		
	                	}
	            	}
	        	}
	        	 */
	        	
	        }
	    };
    	
	    Timer timer = new Timer(500, actListner);
	    timer.start();
    	
    	
        frame.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 208, 541);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JButton btnNewButton_1 = new JButton("Connect");
        btnNewButton_1.setBounds(0, 518, 208, 23);
        panel.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//Client c = new Client();
        		//c.start();
        		
        	}
        });
        
        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 24, 208, 494);
        panel.add(panel_3);
        panel_3.setLayout(new GridLayout(0, 1, 0, 0));
        
        table = new JTable();
        //set model into the table object
    	table.setModel(dtm);
    	panel_3.add(table);
        
       
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 208, 24);
        panel.add(panel_1);
        
        JLabel lblConnectedUsers = new JLabel("Connected Users");
        panel_1.add(lblConnectedUsers);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(607, 0, 99, 541);
        frame.getContentPane().add(panel_2);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dtm.addRow(new Object[] {"HI"});
        	}
        });
        panel_2.add(btnNewButton);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("New menu");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
        mnNewMenu.add(mntmNewMenuItem);
        
        JMenu mnNewMenu_1 = new JMenu("New menu");
        mnNewMenu.add(mnNewMenu_1);
        
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("click me");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(arg0.getSource()==mntmNewMenuItem_2){
        			System.out.println("This is clicked");
        		}
        	}
        });
        mnNewMenu_1.add(mntmNewMenuItem_2);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
        mnNewMenu.add(mntmNewMenuItem_1);
        
        JMenu mnNewMenu_2 = new JMenu("New menu");
        mnNewMenu.add(mnNewMenu_2);
        
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
        mnNewMenu_2.add(mntmNewMenuItem_3);
        frame.setVisible(true);
	}

	
	
	
	
	
	public class homePane extends JPanel{
		public homePane(){
		}
	}
	
	public void run() {
		try {
			home window = new home();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void start(int index) {
		this.index=index;
		initialize();
		
	}
	public void setList(LinkedList<InetAddress> ipList2) {
		// TODO Auto-generated method stub
		templist = ipList2;
		//System.out.println("HereDown"+templist2);
	}
	public LinkedList<InetAddress> getList(){
		
		//System.out.println("HereDownasdf"+ipList);
		return templist;
	}
	
	
	
}
