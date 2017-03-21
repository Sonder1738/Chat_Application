
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JTextField;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JOptionPane;
import javax.swing.JLabel;



public class primary{

	LinkedList<clients> clients= new LinkedList();
	Serialize serial = new Serialize();
	
	JFrame frame;
	JTextField id = new JTextField(10);
	JPasswordField pass = new JPasswordField(10);
	JButton login = new JButton("Login");
	JButton newuser = new JButton("New User? Click here to register");
	public static void main(String[] args) {
		start();
	}
	
	
	public primary(){
		//starts listener server first that listens for clients on the network??
		multicast mc = new multicast();
		mc.start();
		System.out.println("Ready to listen for connected clients on the network");
		
		LinkedList<clients> tempList = new LinkedList();
    	tempList =serial.deserialize("src/clients.ser");
    	
    	if(tempList==null){
			//isNull=true; //use this or delete
    		JOptionPane.showMessageDialog(null, "Welcome to the Chat Client!");
    		JOptionPane.showMessageDialog(null, "Please Register For first time use!");
    		
    		login.setEnabled(false);
			//do nothing
			}else{
				
				clients=tempList;
				serial.serialize(clients, "src/clients.ser");
		//do nothing
		//isNull=false; //use this or delete
		}
    	
		//printeverything();
		
		frame = new JFrame("DERICK CHU FYP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(420, 320);
        frame.setContentPane(new loginPane());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	
	public class loginPane extends JPanel{
		public loginPane(){
			
			loginev ev = new loginev();
			login.addActionListener(ev);
			id.addActionListener(ev);
			pass.addActionListener(ev);
			newuser.addActionListener(ev);
			
			pass.addActionListener(new ActionListener() {
				
				    public void actionPerformed(ActionEvent e) {
				
				        //login.doClick();
				        ActionEvent b = new ActionEvent(login,0,"alternative login"); //creating action event which login button creates
						ev.actionPerformed(b); // sending the action to the event listener
					}
				
				});

			
			
			
			newuser.setOpaque(false);
			newuser.setContentAreaFilled(false);
			newuser.setBorderPainted(false);
			
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.anchor = GridBagConstraints.EAST;
			
			gbc.insets = new Insets(3,3,3,3);
			
			add(new JLabel("ID: "),gbc); //initializes it here?
			gbc.gridy+=1;
			add(new JLabel("Password: "),gbc); //initializes it here?
			gbc.gridy=0;
			gbc.gridx+=1;
			gbc.fill =GridBagConstraints.HORIZONTAL;
			add(id,gbc);
			gbc.gridy+=1;
			add(pass,gbc);
			gbc.gridy+=1;
			gbc.gridx=1;
			add(newuser,gbc);
			gbc.gridx++;
			gbc.gridy--;
			gbc.gridy--;
			gbc.gridheight=4;
			gbc.fill = GridBagConstraints.VERTICAL;
			add(login,gbc);
			
			
			
		}
	}
	public class loginev implements ActionListener{

		home homeFrame = new home();
		register registerFrame = new register();
		boolean notLoggedIn;
		
		public void actionPerformed(ActionEvent e) {
			
			
			
			
			if(e.getSource()==login){
				
				for(int i=0;i<clients.size();i++){
					if(id.getText().equalsIgnoreCase(clients.get(i).getId()) && pass.getText().equalsIgnoreCase(clients.get(i).getPassword())){
						JOptionPane.showMessageDialog(null, "Logged in!","Success!",JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
	    				homeFrame.start(i);
	    				
	    				notLoggedIn=false;
	    				break;
					}else{
						notLoggedIn=true;
					}
				
			}
				
				
    				if(notLoggedIn){
    					JOptionPane.showMessageDialog(frame,
    						    "Wrong ID or Password",
    						    "Warning!",
    						    JOptionPane.WARNING_MESSAGE);
    							}
						}else{
					//do nothing
					
					}
				
			
			if(e.getSource()==newuser){
				Path folder = Paths.get("src");
				if(!Files.exists(folder)){
					try{
						Files.createDirectories(folder);
					}catch(Exception e2){
					}
				}
				
				frame.dispose();
				registerFrame.start();
			}
		}
		
	}
	public void printeverything(){//just to test if list has sthuf
		for(int i=0;i<clients.size();i++){
			System.out.println("ID :"+clients.get(i).getId());
			System.out.println("PW :"+clients.get(i).getPassword());
		}
	}


	public static void start() {
		new primary();
	}
}


  		

	
