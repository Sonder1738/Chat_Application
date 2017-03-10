import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;

import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;


public class chatFrame implements Runnable{

	static JFrame frame; //Making it static could cause problems
	private JTextField textField;
	static JTextArea textArea = new JTextArea();
	private JButton btnNewButton;
	

	/**
	 * @param b 
	 * @param string 
	 * @wbp.parser.entryPoint
	 */
	public void start(String string, boolean b) {
		frame = new JFrame("DERICK CHU FYP");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(434, 404);
        frame.setLocationRelativeTo(null);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{418, 0};
        gridBagLayout.rowHeights = new int[]{296, 33, 19, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);
        
        
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        frame.getContentPane().add(scrollPane, gbc_scrollPane);
        
        JScrollBar autoScroll = scrollPane.getVerticalScrollBar(); //autoscroll to bottom
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
    	    @Override
    	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
    	    	Client c = new Client();
    	        if (JOptionPane.showConfirmDialog(frame, 
    	            "Are you sure you want to close the chat?", "Exit Chat?", 
    	            JOptionPane.YES_NO_OPTION,
    	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
    	        	
    	            try {
    	            	c.setMessage("gbye1738");
    	            	c.msgOut();
						System.out.println("bye"); //del
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	        	frame.dispose();
    	        }
    	    }
    	});
        
        
        
        
        
        Action action = new AbstractAction()
    	{
    	    @Override
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	
    	    	String myText = textField.getText();
    	    	textArea.append(myText+"\n");
    	    	textField.setText("");
    	    	
    	    	Client c = new Client();
    	    	c.setMessage(myText);
    	    	try {
					Thread.sleep(200);
					c.msgOut();
					autoScroll.setValue(autoScroll.getMaximum());
				} catch (Exception b) {
					
				}
    	    	
    	    	
    	    	
    	    	
    	    }
    	};
        
        btnNewButton = new JButton("Send File");
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
						try (DataOutputStream d = new DataOutputStream(out)) {
						    d.writeUTF(chooser.getSelectedFile().getName());
						    Files.copy(chooser.getSelectedFile().toPath(), d);
						}
					    c.getFileSendSocket().close(); //closes the filesend sockets
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
            		
            		
					
								
						System.out.println("Done.");	
						
					
                	
                   
                }
        	}
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.VERTICAL;
        gbc_btnNewButton.anchor = GridBagConstraints.EAST;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 1;
        frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
        
        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.VERTICAL;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 2;
        frame.getContentPane().add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{414, 0};
        gbl_panel.rowHeights = new int[]{18, 0};
        gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        
        
        textField = new JTextField();
        
        textField.addActionListener(action);
        textField.setEditable(true);
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 0;
        panel.add(textField, gbc_textField);
        textField.setColumns(50);
        frame.setVisible(true);
        
        	
        Client startCon = new Client(); //starts the client and send the IP and boolean private over
        startCon.start(string, b);
		//startCon.clientIn(string, b);
	}

	public class textListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public final JFrame getMainFrame(){
        return frame;
    }

	public void printMsg(String line) {
		textArea.append(line+"\n"); //message from server INSTEAD
	}

	



}
