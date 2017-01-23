import java.awt.EventQueue;

import javax.swing.JFrame;

public class home {

	private JFrame frame;

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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
