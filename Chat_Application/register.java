import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;

public class register {
	
	private JFrame frame;
	
	private JButton reg = new JButton("Register");
	private JLabel id = new JLabel("ID :");
	private JLabel pw = new JLabel("Password :");
	private JTextField idf = new JTextField(10);
	private JPasswordField pwf = new JPasswordField(10);
	
	public LinkedList<clients> clients = new LinkedList();
	
	public class registerPane extends JPanel{
		public registerPane(){
			registerEv re = new registerEv();
			reg.addActionListener(re);
			idf.addActionListener(re);
			pwf.addActionListener(re);
			
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
		initialize();
		LinkedList<clients> templist = new LinkedList();
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
				System.out.println("Register something");
			}
		}
		
	}
}
