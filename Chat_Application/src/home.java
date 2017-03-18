
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import java.awt.Component;
public class home{

	
	DefaultTableModel dtm = new DefaultTableModel(0, 0);
	private static JFrame frame;
	int index;
	public JTable table;
	private InetAddress bla;
	static LinkedList<InetAddress> ipList = null;
	static LinkedList<InetAddress> oldList =null; //maybe make null
	static LinkedList<InetAddress> templist;
	static int i=0;
	
	
	
	private static SecretKeySpec secretKey;
    private static byte[] key;
	static JScrollBar autoScroll;
	JTextField friendlyNameField;
	JRadioButton rdbtnPrivate;
	private JTextField textField1;
	static JTextArea textArea = new JTextArea();
	JTextField ip;
	String friendlyName;
	
	JTextField chatBox;
	private JButton sendFile;
	private JButton saveChat;
	private JButton disconnect;
	
	boolean isPrivate;
	private JPanel panel_5;
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
		System.out.println(b); //prints user login id currently HERE IS WHER IT PRINTZ USERNAME
		
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
    	    	
    	        if(JOptionPane.showConfirmDialog(frame, 
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
        
        
        
        JButton btnConnect = new JButton("Connect");
        btnConnect.setBounds(0, 518, 208, 23);
        panel.add(btnConnect);
        btnConnect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		getFrame().setEnabled(false); //sets the background frame false
				//Client c = new Client();
        		//c.start();
        		//chatFrame cf = new chatFrame();
        		//cf.start();
        		
        		JPanel contentPane;
        		
        		
        		JFrame frame = new JFrame();
        		//getFrame().setEnabled(false);
        		
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 297, 177);
				frame.setLocationRelativeTo(null);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame.setContentPane(contentPane);
				contentPane.setLayout(null);
				
				frame.addWindowListener(new WindowAdapter()
				{
				    public void windowClosing(WindowEvent e)
				    {
				    	getFrame().setEnabled(true);
				    }
				});
				
				ip = new JTextField();
				ip.setBounds(10, 36, 178, 20);
				contentPane.add(ip);
				ip.setColumns(10);
				
				JLabel lblIpAddress = new JLabel("IP Address");
				lblIpAddress.setBounds(10, 11, 91, 14);
				contentPane.add(lblIpAddress);
				
				JButton btnChat = new JButton("Chat!");
				btnChat.setBounds(192, 36, 89, 78);
				contentPane.add(btnChat);
				
				rdbtnPrivate = new JRadioButton("Private Chat");
				rdbtnPrivate.setBounds(10, 119, 122, 23);
				contentPane.add(rdbtnPrivate);
				
				friendlyNameField = new JTextField();
				friendlyNameField.setBounds(10, 92, 178, 20);
				contentPane.add(friendlyNameField);
				friendlyNameField.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("Enter a friendly name");
				lblNewLabel.setBounds(10, 67, 146, 14);
				contentPane.add(lblNewLabel);
				
				
				btnChat.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						
						if(ip.getText().isEmpty() || friendlyNameField.getText().isEmpty()){
							JOptionPane.showMessageDialog(frame,
								    "Fields cannot be empty",
								    "Warning",
								    JOptionPane.WARNING_MESSAGE);
						}else{
							//Client startClient = new Client();
							friendlyName = friendlyNameField.getText();
							chatFrame chatFrame = new chatFrame();
							frame.dispose();
							getFrame().setEnabled(true);
							getFrame().setAlwaysOnTop(true);
							btnConnect.setEnabled(false);
							try {
								//chatFrame.start(textField_1.getText(), textField.getText(),rdbtnPrivate.isSelected());
								//getFrame().setEnabled(false);
								Client startCon = new Client(); //starts the client and send the IP and boolean private over
						        //GETS THE KEY FIRST BEFORE ESTABLISHING CHAT CONNECTION
								
						        if(rdbtnPrivate.isSelected()==true){
						        	int port =15680;
						        	startCon.start(ip.getText(), rdbtnPrivate.isSelected());
						        	
						        	chatBox.setEnabled(true);
						    	    sendFile.setEnabled(true);
						    	    saveChat.setEnabled(true);
						    	    disconnect.setEnabled(true);
						        }else{
						        	startCon.start(ip.getText(), rdbtnPrivate.isSelected());
						        	chatBox.setEnabled(true);
						    	    sendFile.setEnabled(true);
						    	    saveChat.setEnabled(true);
						    	    disconnect.setEnabled(true);
						        }
						        isPrivate = rdbtnPrivate.isSelected();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
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
        panel_2.setBounds(221, 0, 573, 541);
        frame.getContentPane().add(panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{573, 0};
        gbl_panel_2.rowHeights = new int[]{466, 29, 20, 0};
        gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.anchor = GridBagConstraints.NORTHWEST;
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.anchor = GridBagConstraints.EAST;
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 0;
        
        ////////////////////////////////////////////////////////////////////////////CHAT FRAME IMPORTZ//////////////////
        
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        panel_2.add(scrollPane, gbc_scrollPane);
        
        JPanel panel_11 = new JPanel();
        panel_5 = new JPanel();
        GridBagConstraints gbc_panel_11 = new GridBagConstraints();
        gbc_panel_11.anchor = GridBagConstraints.EAST;
        gbc_panel_11.fill = GridBagConstraints.VERTICAL;
        gbc_panel_11.insets = new Insets(0, 0, 5, 0);
        gbc_panel_11.gridx = 0;
        gbc_panel_11.gridy = 1;
        panel_2.add(panel_5, gbc_panel_11);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0, 89, 75, 0};
        gbl_panel_1.rowHeights = new int[]{41, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel_5.setLayout(gbl_panel_1);
        
        saveChat = new JButton("Save Chat");
        saveChat.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		getFrame().setAlwaysOnTop(false);
        		//save chat here
        		DateFormat df = new SimpleDateFormat("dd-MMM-YYYY");
                Date dateobj = new Date();
                String date = df.format(dateobj);
                
                try{
                	File log = new File(df.format(dateobj)+" "+ip.getText()+".txt") ;
                	FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
                	textArea.write(fw);
                	fw.close();
                	JOptionPane.showMessageDialog(null, df.format(dateobj)+" "+ip.getText()+".txt saved!");
				}catch (Exception e){
					System.out.println(e);
				}
             }
        });
        
        disconnect = new JButton("Disconnect");
        disconnect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		getFrame().setAlwaysOnTop(false);
        		Client c = new Client();
        		c.setMessage("gbye1738");
        		
        		 if(JOptionPane.showConfirmDialog(frame, 
         	            "Are you sure you want to disconnect?\nThis will erase the current session", "Disconnect?", 
         	            JOptionPane.YES_NO_OPTION,
         	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
         	        	
         	            try {
         	            	c.msgOut();
        					
        					textArea.setText("");
        					btnConnect.setEnabled(true);
        					chatBox.setEnabled(false);
        				    sendFile.setEnabled(false); //DISABLE ALL BUTTONS HERE ..ADD A DISCONNECT BUTTON TO DISABLE THE CHATZ
        				    saveChat.setEnabled(false);
        				    disconnect.setEnabled(false);
     					} catch (Exception e) {
     						// TODO Auto-generated catch block
     						e.printStackTrace();
     					}
         	        	
         	        }else{
         	        	System.out.println("OKAY");
         	        }
        		 
            	
        	}
        });
        GridBagConstraints gbc_disconnectBtn = new GridBagConstraints();
        gbc_disconnectBtn.fill = GridBagConstraints.BOTH;
        gbc_disconnectBtn.insets = new Insets(0, 0, 0, 5);
        gbc_disconnectBtn.gridx = 0;
        gbc_disconnectBtn.gridy = 0;
        panel_5.add(disconnect, gbc_disconnectBtn);
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_2.gridx = 1;
        gbc_btnNewButton_2.gridy = 0;
        panel_5.add(saveChat, gbc_btnNewButton_2);
        
        sendFile = new JButton("Send File");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 0;
        panel_5.add(sendFile, gbc_btnNewButton);
        
        JPanel panel_4 = new JPanel();
        GridBagConstraints gbc_panel1 = new GridBagConstraints();
        gbc_panel1.fill = GridBagConstraints.BOTH;
        gbc_panel1.gridx = 0;
        gbc_panel1.gridy = 2;
        panel_2.add(panel_4, gbc_panel1);
        
        //action//
        Action action = new AbstractAction()
    	{
    	    @Override
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	
				String myText = chatBox.getText();
    	    	DateFormat df = new SimpleDateFormat("h:mm:ss a");
                Date dateobj = new Date();
               
				
				if(isPrivate==true){
    	    		//System.out.println(clients.get(index).getPassword());
    	    		//setKey("mypass");
    	    		//System.out.println(encrypt(myText, "mypass2")); //CONTINUE THE ENCRYPTION SHIT!!!
    	    		//System.out.println(decrypt(encrypt(myText, "mypass2"), "mypass2"));
					//textField.setText("");
    	    		//String myText = textField.getText();
    	    		
    	    		
    	    		
        	    	textArea.append(df.format(dateobj)+"    "+friendlyName+": "+myText+"\n");
        	    	chatBox.setText("");
        	    	
        	    	String encrypted = encrypt(friendlyName+": "+myText, "mx6unB3MZNEZOgLiTrLC"); //enter something longer here
        	    	
        	    	Client c = new Client();
        	    	c.setMessage(encrypted);
        	    	
        	    	try {
        	    		
    					Thread.sleep(200);
    					c.msgOut();
    					autoScroll.setValue(autoScroll.getMaximum());
    				} catch (Exception b){
    				}
        	    	
        	    	
                	}else{
    	    		//String myText = textField.getText();
        	    	textArea.append(df.format(dateobj)+"    "+friendlyName+": "+myText+"\n");
        	    	chatBox.setText("");
        	    	
        	    	Client c = new Client();
        	    	c.setMessage(friendlyName+": "+myText);
        	    	try {
        	    		Thread.sleep(200);
    					c.msgOut();
    					autoScroll.setValue(autoScroll.getMaximum());
    				} catch (Exception b) {
    					
    				}
    	    	}
	    }
    	};
        
        ////////
        
    	chatBox = new JTextField();
        
    	chatBox.addActionListener(action);
        panel_4.setLayout(new BorderLayout(0, 0));
        chatBox.setEditable(true);
        panel_4.add(chatBox);
        chatBox.setColumns(50);
        sendFile.addActionListener(new ActionListener() {
        	Client c = new Client();
        	
        	public void actionPerformed(ActionEvent arg0) {
        		getFrame().setAlwaysOnTop(false);
        		JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter images = new FileNameExtensionFilter(
                    "Images", "jpg", "gif","png");
                FileNameExtensionFilter document = new FileNameExtensionFilter(
                        "Document", "docx", "doc","pdf","pptx","txt");
                
               	chooser.addChoosableFileFilter(images);
                chooser.addChoosableFileFilter(document);
                
                Component parent = null;
        		int returnVal = chooser.showOpenDialog(parent);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                	
                	File filesend = new File(chooser.getSelectedFile().getPath());
                	int count;
                    //OutputStream out;
                    byte[] buffer = new byte[8192];
					try {
						int port =15679;
						
						c.setMessage("fsendnow");
						c.msgOut();
						c.sendFile(port);
						
						
						BufferedOutputStream out = new BufferedOutputStream(c.getFileSendSocket().getOutputStream());
						JOptionPane.showMessageDialog(null, "Sending..");
						try (DataOutputStream d = new DataOutputStream(out)) {
						    d.writeUTF(chooser.getSelectedFile().getName());
						    Files.copy(chooser.getSelectedFile().toPath(), d);
						    JOptionPane.showMessageDialog(null, "Sent!");
						}
						
					}catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "File was not sent\n Something happened");
					}
                	System.out.println("Done.");	
				}
        	}
        });
        
        
/*panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.anchor = GridBagConstraints.EAST;
        gbc_panel_1.fill = GridBagConstraints.VERTICAL;
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;
        frame.getContentPane().add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{89, 75, 0};
        gbl_panel_1.rowHeights = new int[]{41, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);
        
        btnNewButton_2 = new JButton("Save Chat");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		//save chat here
        		DateFormat df = new SimpleDateFormat("dd-MMM-YYYY");
                Date dateobj = new Date();
                String date = df.format(dateobj);
                
                try{
                	File log = new File(df.format(dateobj)+".txt") ;
                	FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
                	textArea.write(fw);
                	fw.close();
                	JOptionPane.showMessageDialog(null, df.format(dateobj)+".txt saved!");
				}catch (Exception e){
					System.out.println(e);
				}
             }
        });
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_2.gridx = 0;
        gbc_btnNewButton_2.gridy = 0;
        panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
        
        btnNewButton = new JButton("Send File");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 0;
        panel_1.add(btnNewButton, gbc_btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
        	
        	Client c = new Client();
        	
        	
        	
        	public void actionPerformed(ActionEvent arg0) {
        		JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter images = new FileNameExtensionFilter(
                    "Images", "jpg", "gif","png");
                FileNameExtensionFilter document = new FileNameExtensionFilter(
                        "Document", "docx", "doc","pdf","pptx","txt");
                
               	chooser.addChoosableFileFilter(images);
                chooser.addChoosableFileFilter(document);
                
                Component parent = null;
        		int returnVal = chooser.showOpenDialog(parent);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                	
                	File filesend = new File(chooser.getSelectedFile().getPath());
                	int count;
                    //OutputStream out;
                    byte[] buffer = new byte[8192];
					try {
						int port =15679;
						
						c.setMessage("fsendnow");
						c.msgOut();
						c.sendFile(port);
						
						
						BufferedOutputStream out = new BufferedOutputStream(c.getFileSendSocket().getOutputStream());
						JOptionPane.showMessageDialog(null, "Sending..");
						try (DataOutputStream d = new DataOutputStream(out)) {
						    d.writeUTF(chooser.getSelectedFile().getName());
						    Files.copy(chooser.getSelectedFile().toPath(), d);
						    JOptionPane.showMessageDialog(null, "Sent!");
						}
						
					}catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "File was not sent\n Something happened");
					}
                	System.out.println("Done.");	
				}
        	}
        });*/
        
        
        
        
        
        autoScroll = scrollPane.getVerticalScrollBar(); //autoscroll to bottom
       
        
        /*
        frame.addWindowListener(new java.awt.event.WindowAdapter() { //add disconnect button here???
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    	    	Client c = new Client();
    	        if (JOptionPane.showConfirmDialog(frame, 
    	            "Are you sure you want to close the chat?", "Exit Chat?", 
    	            JOptionPane.YES_NO_OPTION,
    	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
    	        	
    	            try {
    	            	home h = new home();
    	            	h.getFrame().setEnabled(true); //re-enable it
    	            	c.setMessage("gbye1738");
    	            	c.msgOut();
    	            	}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	        	frame.dispose();
    	        }
    	    }
    	});
        */
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        chatBox.setEnabled(false);
	    sendFile.setEnabled(false); //DISABLE ALL BUTTONS HERE ..ADD A DISCONNECT BUTTON TO DISABLE THE CHATZ
	    saveChat.setEnabled(false);
	    disconnect.setEnabled(false);
	    
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
        frame.setVisible(true);
	}


	
	public JFrame getFrame() {
		return frame;
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


	/////////////////////////////////////////////////////////////////ENCRYPTION///////////////////////////////
	public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
	
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
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
////////////////////////////////////////////////////////////////////////////////////////////

public void printMsg(String line) throws InterruptedException {
		
		textArea.append(line+"\n"); //message from server INSTEAD
		try{
			autoScroll.setValue(autoScroll.getMaximum());
		}catch(Exception e){
			System.out.println("Wut");
		}
	}
}
