
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

public class home{

	
	
	private JFrame frame;
	int index;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		
		Serialize serial = new Serialize();
		LinkedList<clients> clients= new LinkedList();
		LinkedList templist = serial.deserialize("src/clients.ser");
		clients = templist;
		String b = clients.get(index).getId(); // CHANGE TO LOOP
		System.out.println(b);
		
		frame = new JFrame("DERICK CHU FYP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(new homePane()); //is this even needed?
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{4, 608, 483, 0, 40, 52, 172, 237, -66, 0};
        gbl_panel.rowHeights = new int[]{31, 180, 382, 76, 20, 0};
        gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.gridwidth = 2;
        gbc_textField_1.fill = GridBagConstraints.BOTH;
        gbc_textField_1.insets = new Insets(0, 0, 5, 5);
        gbc_textField_1.gridx = 2;
        gbc_textField_1.gridy = 2;
        panel.add(textField_1, gbc_textField_1);
        textField_1.setColumns(10);
        
        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 3;
        panel.add(textField, gbc_textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("New button");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
        gbc_btnNewButton.gridx = 3;
        gbc_btnNewButton.gridy = 3;
        panel.add(btnNewButton, gbc_btnNewButton);
        
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
}
