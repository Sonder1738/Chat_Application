
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class register {
	
	private JFrame frame;
	
	private JButton reg = new JButton("Register");
	private JLabel id = new JLabel("ID :");
	private JLabel pw = new JLabel("Password :");
	private JTextField idf = new JTextField(10);
	private JPasswordField pwf = new JPasswordField(10);
	boolean idValid = false;
	boolean passValid = false;
	
	public LinkedList<clients> clients = new LinkedList();
	Serialize serial = new Serialize();
	
	public class registerPane extends JPanel{
		public registerPane(){
			registerEv re = new registerEv();
			mouseEv me = new mouseEv();
			keyEv ke = new keyEv();
			
			reg.addActionListener(re);
			idf.addActionListener(re);
			pwf.addActionListener(re);
			idf.addMouseListener(me);
			idf.addKeyListener(ke);
			pwf.addMouseListener(me);
			pwf.addKeyListener(ke);
			reg.setEnabled(false);
			
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.EAST;
			gbc.gridx=0;
			gbc.gridy=0;
			add(id,gbc);
			gbc.gridy+=1;
			add(pw,gbc);
			gbc.gridx+=1;
			gbc.gridy-=1;
			add(idf,gbc);
			gbc.gridy+=1;
			add(pwf,gbc);
			gbc.gridx+=1;
			gbc.gridy--;
			gbc.gridheight=2;
			gbc.fill = GridBagConstraints.VERTICAL;
			add(reg,gbc);
		}
	}
	
	
	
	
	public void start() {
		LinkedList<clients> tempList = new LinkedList();
    	tempList =serial.deserialize("src/clients.ser");
    	System.out.println("DOS IT RUN");
    	if(tempList==null){
    		System.out.println("DOS 22222IT RUN");
			serial.serialize(clients, "src/clients.ser");
			tempList = serial.deserialize("src/clients.ser");
			System.out.println("decereal");
			clients=tempList;
			}else{
			
				clients=tempList;
		//do nothing
		//isNull=false; //use this or delete
		}
    	
    	
    	clients=tempList;
    	initialize();
		//from here
	}

	private void initialize() {
		frame = new JFrame("DERICK CHU FYP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(420, 320);
        frame.setContentPane(new registerPane());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public class registerEv implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==reg){
				String enteredId = idf.getText();
				String enteredPw = pwf.getText();
				
				clients.add(new clients(enteredId,enteredPw));
				serial.serialize(clients, "src/clients.ser");
				
				JOptionPane.showMessageDialog(null,
					    "Guy Registered");
				primary primaryFrame = new primary();
				frame.dispose();
			}
		}
	}
	public class mouseEv implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==idf){
				if(idf.getText().length()<5){
					idf.setToolTipText("Please enter a username of minimum 5 characters");
				}else{ //SETS THE TOOLTIP IF INVALID LENGTH
					idf.setToolTipText(null);
					idValid=true;
				}
			}
			if(e.getSource()==pwf){
				if(pwf.getText().length()<5 || !alphaNumeric(pwf.getText())){
					pwf.setToolTipText("Please enter an alpha numerical password");
				}else{
					pwf.setToolTipText(null);
					passValid=true;
				}
			}
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==idf){
				if(idf.getText().length()<5 && idValid==false){
					idf.setText("");
				}
			}
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class keyEv implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getSource()==pwf){
				if(pwf.getText().length()<5 || !alphaNumeric(pwf.getText()) || idf.getText().length()<5){
					reg.setEnabled(false);
				}else{
					reg.setEnabled(true);
				}
			}
			if(e.getSource()==idf){
				if(idf.getText().length()<5 || pwf.getText().length()<5 || !alphaNumeric(pwf.getText())){
					reg.setEnabled(false);
				}else{
					reg.setEnabled(true);
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		
	}
	
	public boolean alphaNumeric(String p){
		 	String a = ".*[0-9].*";
		 	String b = ".*[a-zA-Z].*";
		 	return p.matches(b) && p.matches(a); //only returns true if string matches both ie.alphanumerical
		
	}
	
	
	
}
