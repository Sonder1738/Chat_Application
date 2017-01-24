
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.beans.PropertyChangeEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.UIManager;


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
		//do nothing
		//isNull=false; //use this or delete
		}
    	
    	
    	clients=tempList;
		
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
	    				homeFrame.start();
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
				frame.dispose();
				registerFrame.start();
			}
		}
		
	}
	public void printeverything(){
		for(int i=0;i<clients.size();i++){
			System.out.println("ID :"+clients.get(i).getId());
			System.out.println("PW :"+clients.get(i).getPassword());
		}
	}


	public static void start() {
		new primary();
	}
}


  		

	
