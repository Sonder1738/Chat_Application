
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
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
import javax.swing.JOptionPane;
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
		Server s = new Server(); //starts server when logged in. CAUSE user can only connect with the connect button rather than auto
		s.start();
		handshake hs = new handshake();
		try {
			hs.start();
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
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(new homePane()); //is this even needed?
        frame.setLocationRelativeTo(null);
        
        String header[] = new String[] {""};

        //add header in table model     
    	dtm.setColumnIdentifiers(header);
    	//dtm.addRow(new Object[] {"ASDFGG-PC/ 192.168.1.1"}); add stuff here
    	
    	//table timer check periodically
    	multicast asdf = new multicast();
    	
    	frame.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    	    	
    	        if (JOptionPane.showConfirmDialog(frame, 
    	            "Are you sure you want to exit?", "Exit Program?", 
    	            JOptionPane.YES_NO_OPTION,
    	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
    	        	
    	            try {
						hs.sendFarewell();
						System.out.println("bye");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	        	System.exit(0);
    	        }
    	    }
    	});
    	
    	
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
        		//chatFrame cf = new chatFrame();
        		//cf.start();
        		JPanel contentPane;
        		JTextField textField;
        		
        		JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 297, 123);
				frame.setLocationRelativeTo(null);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame.setContentPane(contentPane);
				contentPane.setLayout(null);
				
				textField = new JTextField();
				textField.setBounds(10, 36, 178, 20);
				contentPane.add(textField);
				textField.setColumns(10);
				
				JLabel lblIpAddress = new JLabel("IP Address");
				lblIpAddress.setBounds(10, 11, 91, 14);
				contentPane.add(lblIpAddress);
				
				JButton btnChat = new JButton("Chat!");
				btnChat.setBounds(191, 35, 89, 23);
				contentPane.add(btnChat);
				
				JRadioButton rdbtnPrivate = new JRadioButton("Private");
				rdbtnPrivate.setBounds(10, 63, 109, 23);
				contentPane.add(rdbtnPrivate);
				
				btnChat.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						System.out.println(rdbtnPrivate.isSelected());
						System.out.println(textField.getText());
						
						if(textField.getText().isEmpty()){
							System.out.println("Here");
							JOptionPane.showMessageDialog(frame,
								    "Field cannot be empty",
								    "Warning",
								    JOptionPane.WARNING_MESSAGE);
						}else{
							//Client startClient = new Client();
							chatFrame chatFrame = new chatFrame();
							frame.dispose();
							chatFrame.start(textField.getText(),rdbtnPrivate.isSelected());
							
						}
					}
				});
				
				
				
				
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
        
        JButton btnNewButton = new JButton("New button"); //test button
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
	
	public void run() { //never called?
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
