import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;

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
import javax.swing.JProgressBar;

/*TO DO LIST
FIX CHATFRAME BAR CENTERING, MAKE IT DYNAMICALLY UPDATE SIZE --
ADD NAMES MAYBE? --
ADD NOTICE WHEN SENDING FILE --
MAYBE ADD ...is typing?
SCROLL TO BOTTOM WHEN USER RECEIVE A MESSAGE --
ADD CONNECTED MESSAGE --
*/




public class chatFrame implements Runnable{

	static JFrame frame; //Making it static could cause problems
	private JTextField textField;
	static JTextArea textArea = new JTextArea();
	private JButton btnNewButton;
	private JPanel panel_1;
	
	private static SecretKeySpec secretKey;
    private static byte[] key;
    
    LinkedList<clients> clients= new LinkedList();
	Serialize serial = new Serialize();
	private JButton btnNewButton_2;
	
	static JScrollBar autoScroll;
	
	/**
	 * @param b 
	 * @param string 
	 * @throws Exception 
	 * @wbp.parser.entryPoint
	 */
	public void start(String friendlyName,String string, boolean isPrivate) throws Exception {
		if(isPrivate ==true){
			JOptionPane.showMessageDialog(null, "This chat session is encrypted with AES");
		}else{
			JOptionPane.showMessageDialog(null, "This chat session is unsecured");
		}
		
		frame = new JFrame("DERICK CHU FYP");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(434, 404);
        frame.setLocationRelativeTo(null);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{418, 0};
        gridBagLayout.rowHeights = new int[]{298, 46, 23, 0};
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
        
        autoScroll = scrollPane.getVerticalScrollBar(); //autoscroll to bottom
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
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
        
        

        Action action = new AbstractAction()
    	{
    	    @Override
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	String myText = textField.getText();
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
        	    	textField.setText("");
        	    	
        	    	String encrypted = encrypt(myText, "mx6unB3MZNEZOgLiTrLC"); //enter something longer here
        	    	
        	    	Client c = new Client();
        	    	c.setMessage(friendlyName+": "+encrypted);
        	    	try {
    					Thread.sleep(200);
    					c.msgOut();
    					autoScroll.setValue(autoScroll.getMaximum());
    				} catch (Exception b){
    				}
        	    	
        	    	
                	}else{
    	    		//String myText = textField.getText();
        	    	textArea.append(df.format(dateobj)+"    "+friendlyName+": "+myText+"\n");
        	    	textField.setText("");
        	    	
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
        
        panel_1 = new JPanel();
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
        });
        
        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 2;
        frame.getContentPane().add(panel, gbc_panel);
        
        
        
        textField = new JTextField();
        
        textField.addActionListener(action);
        panel.setLayout(new BorderLayout(0, 0));
        textField.setEditable(true);
        panel.add(textField);
        textField.setColumns(50);
        frame.setVisible(true);
        
        	
        Client startCon = new Client(); //starts the client and send the IP and boolean private over
        //GETS THE KEY FIRST BEFORE ESTABLISHING CHAT CONNECTION
        if(isPrivate==true){
        	int port =15680;
        	
        	startCon.start(string, isPrivate);
        	
        	
        }else{
        	startCon.start(string, isPrivate);
        }
        
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

	public void printMsg(String line) throws InterruptedException {
		
		textArea.append(line+"\n"); //message from server INSTEAD
		try{
			autoScroll.setValue(autoScroll.getMaximum());
		}catch(Exception e){
			System.out.println("Wut");
		}
	}

	
// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ENCRYPTION@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	    	
	
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

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ENCRYPTION@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	  


}
