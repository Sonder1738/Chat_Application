
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class home{

	
	
	private JFrame frame;

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		Serialize serial = new Serialize();
		LinkedList<clients> clients= new LinkedList();
		LinkedList templist = serial.deserialize("src/clients.ser");
		clients = templist;
		String b = clients.get(0).getId();
		System.out.println(b);
		
		frame = new JFrame("DERICK CHU FYP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(new homePane());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("HELLO");
        lblNewLabel.setBounds(102, 56, 619, 431);
        lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 99));
        lblNewLabel.setText("HELLO "+b);
        frame.getContentPane().add(lblNewLabel);
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



	public void start() {
		initialize();
		
	}
}
