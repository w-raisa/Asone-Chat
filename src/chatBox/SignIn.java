package chatBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;
import javax.swing.JProgressBar;

public class SignIn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Scrollbar scrollbar;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnCreateAccount;
	private JLabel lblDontHaveAn;
	private JLabel lblForgotUsernamepassword;
	private JButton btnRecover;
	private String username;
	private String password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignIn() {
		setTitle("MSN Messenger Sign In");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 510);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "MSN Messenger", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblUsername = new JLabel("Username:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUsername, 173, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblUsername, -275, SpringLayout.EAST, contentPane);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 168, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblUsername);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -137, SpringLayout.EAST, contentPane);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
	               loginCredentials();
	            }
			}
		});
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPassword, 16, SpringLayout.SOUTH, lblUsername);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblUsername);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, passwordField, 200, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, -6, SpringLayout.NORTH, passwordField);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, lblPassword);
		sl_contentPane.putConstraint(SpringLayout.EAST, passwordField, -137, SpringLayout.EAST, contentPane);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
		           loginCredentials();
		        }
			}
		});
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Sign in");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, passwordField, -8, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 234, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -162, SpringLayout.EAST, contentPane);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginCredentials();
			}
		});
		contentPane.add(btnNewButton);
		
		btnCreateAccount = new JButton("Sign Up!");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnCreateAccount, -23, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnCreateAccount, -156, SpringLayout.EAST, contentPane);
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpWindow signUpWindow = new SignUpWindow(); 
				
			}
		});
		contentPane.add(btnCreateAccount);
		
		lblDontHaveAn = new JLabel("Don't have an account?");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblDontHaveAn, -255, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCreateAccount, 6, SpringLayout.EAST, lblDontHaveAn);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDontHaveAn, 5, SpringLayout.NORTH, btnCreateAccount);
		contentPane.add(lblDontHaveAn);
		
		lblForgotUsernamepassword = new JLabel("Forgot username/password?");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblForgotUsernamepassword, 286, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblForgotUsernamepassword, 0, SpringLayout.WEST, lblDontHaveAn);
		contentPane.add(lblForgotUsernamepassword);
		
		btnRecover = new JButton("Recover");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnRecover, 281, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, -18, SpringLayout.NORTH, btnRecover);
		btnRecover.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					PopUpWindow popUp = new PopUpWindow("Function not available yet. The developer is fucking tired.");
			    }
			}
		});
		btnRecover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopUpWindow popUp = new PopUpWindow("Function not available yet. The developer is fucking tired.");
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnRecover, 6, SpringLayout.EAST, lblForgotUsernamepassword);
		contentPane.add(btnRecover);
		
		
	}
	
	public void loginCredentials() {
		username = textField.getText();
		password = passwordField.getText();
		if (username.equals("") && password.equals("")) {
			popup_alert("Username and Password fields empty");
		}
		else if (username.equals("")) {
			popup_alert("Username field empty");
		}
		else if (password.equals("")) {
			popup_alert("Password field empty");
		}
		else {
			login(username, password);
		}
	}
	
	public void popup_alert(String alert) {
		PopUpWindow popUp = new PopUpWindow(alert);
	}
	
	public void login(String name, String password) { 
		// before disposing the window, verify login credentials
		// if verified, dispose.
		// if wrong password or wrong username, pop up alert box saying so, and do not dispose.
		// login_verification();
		dispose();
		//ClientWindow clientWindow = new ClientWindow(name);
		
		//ChatServer chatServer = new ChatServer(55000);
		// create a new thread, and then do chatServer.execute(); because we run the server, and then server will block to listen for clients.s
		//chatServer.execute();
		//clientWindow.letClientExecute();
		
		ChatClient chatClient = new ChatClient(username);
		MasterClientThread runningClient = new MasterClientThread(chatClient); // through this MasterClientThread object...
		// ... send a message to the master server this client thread will connect to, and that message will be the user's 
		// username and id (MCT will have username and id values of the client because MCT gets passed in the ChatClient object
		// who has these values). Then, MCT will write these values to the Master Server, and MasterServer will read them in,
		// by having a separate reading function.
		runningClient.start();
		
		
		//chatClient.chatSetUp();
		
		HomeWindow homeWindow = new HomeWindow(runningClient, chatClient);
		
	}
}
