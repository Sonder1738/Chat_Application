import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class home{

	
	
	private JFrame frame;

	private void initialize() {
		
		frame = new JFrame("DERICK CHU FYP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        frame.setContentPane(new homePane());
        frame.setLocationRelativeTo(null);
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
