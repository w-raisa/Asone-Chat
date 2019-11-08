package chatBox;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class SignUpWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private boolean isGoodPassword = false;
	
	private ChatClient newUser;
	private String gender;
	private JTextField textField_3;
	private JTextField textField_4;


	/**
	 * Create the frame.
	 */
	public SignUpWindow() {
		setBackground(new Color(240, 248, 255));
		setTitle("Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 449);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Sign up with the latest Messenger!", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Sign up with the latest Messenger!", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(72, 209, 204)));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 43, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 88, SpringLayout.WEST, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First name:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 5, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, textField);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_1, 10, SpringLayout.SOUTH, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last name:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 5, SpringLayout.NORTH, textField_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, textField);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 64, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_2, -5, SpringLayout.NORTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				isGoodPassword = false;
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, passwordField, 1, SpringLayout.SOUTH, textField_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, passwordField, 9, SpringLayout.EAST, textField);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 11, SpringLayout.SOUTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Confirm Password:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 6, SpringLayout.SOUTH, passwordField);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_4);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					isGoodPassword = checkPw();
			    }
				else {
					isGoodPassword = false;
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, passwordField_1, -5, SpringLayout.NORTH, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.WEST, passwordField_1, 9, SpringLayout.EAST, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.EAST, passwordField_1, -226, SpringLayout.EAST, contentPane);
		contentPane.add(passwordField_1);
		
		JLabel lblGender = new JLabel("Gender:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblGender, 15, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblGender, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Male";
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnMale, -4, SpringLayout.NORTH, lblGender);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Female";
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnFemale, 165, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, rdbtnMale, -18, SpringLayout.WEST, rdbtnFemale);
		sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnFemale, 6, SpringLayout.SOUTH, textField_1);
		contentPane.add(rdbtnFemale);
		
		JRadioButton rdbtnPreferNotTo = new JRadioButton("Other");
		rdbtnPreferNotTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Other";
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnPreferNotTo, 111, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnPreferNotTo, 6, SpringLayout.EAST, rdbtnFemale);
		contentPane.add(rdbtnPreferNotTo);
		
		JRadioButton rdbtnPreferNotTo_1 = new JRadioButton("Prefer not to say");
		rdbtnPreferNotTo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Prefer not to say";
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, rdbtnPreferNotTo_1, 111, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, rdbtnPreferNotTo_1, 16, SpringLayout.EAST, rdbtnPreferNotTo);
		contentPane.add(rdbtnPreferNotTo_1);
		
		ButtonGroup bg1 = new ButtonGroup( );

		bg1.add(rdbtnMale);
		bg1.add(rdbtnFemale);
		bg1.add(rdbtnPreferNotTo);
		bg1.add(rdbtnPreferNotTo_1);
		
		JButton btnSignUp = new JButton("Sign Up");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSignUp, -10, SpringLayout.EAST, contentPane);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isGoodPassword) {
					String firstName = textField.getText();
					String lastName = textField_1.getText();
					String userName = textField_2.getText();
					String pword = passwordField_1.getText();
					String conPw = passwordField_1.getText();
					String securityQuestions = textField_3.getText();
					String securityAnswer = textField_4.getText();
					System.out.println("firstName: " + firstName);
					System.out.println("Password: " + pword);
					System.out.println("Confirm Password: " + conPw);
					System.out.println("Gender: " + gender);
					newUser = new ChatClient(firstName, lastName, gender, userName, pword, securityQuestions, securityAnswer);
					if (firstName.equals("") || lastName.equals("") || (gender == null) || userName.equals("") || pword.equals("") || conPw.equals("") || securityQuestions.equals("") || securityAnswer.equals("")) {
						PopUpWindow passwordValidity = new PopUpWindow("Empty fields present, please complete all fields before proceesing.");
					}
					else {
						dispose();
						PopUpWindow passwordValidity = new PopUpWindow("Successfully created account! Welcome to the community " + userName + "!\n Please sign in before proceeding!");
					}
				}
				else {
					PopUpWindow passwordValidity = new PopUpWindow("Complete all fields/Check password validity.");
				}
			}
		});
		contentPane.add(btnSignUp);
		
		JLabel lblMustContainOne = new JLabel("* Must contain one number, one capitial letter, and one special symbol.");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblMustContainOne, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblMustContainOne);
		
		JLabel label = new JLabel("*");
		sl_contentPane.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, passwordField);
		sl_contentPane.putConstraint(SpringLayout.WEST, label, 6, SpringLayout.EAST, passwordField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, label, 16, SpringLayout.NORTH, passwordField);
		sl_contentPane.putConstraint(SpringLayout.EAST, label, 12, SpringLayout.EAST, passwordField);
		contentPane.add(label);
		
		JButton btnCheckIfPasswords = new JButton("Check password validity");
		btnCheckIfPasswords.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					isGoodPassword = checkPw();
			    }
			}
		});
		btnCheckIfPasswords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isGoodPassword = checkPw();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblMustContainOne, 11, SpringLayout.SOUTH, btnCheckIfPasswords);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnCheckIfPasswords, -5, SpringLayout.NORTH, lblNewLabel_4);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCheckIfPasswords, 6, SpringLayout.EAST, passwordField_1);
		contentPane.add(btnCheckIfPasswords);
		
		JLabel lblMustHave = new JLabel("* Must have a minimum length of 6 characters.");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblMustHave, 7, SpringLayout.SOUTH, lblMustContainOne);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblMustHave, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblMustHave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					dispose();
			    }
			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnCancel, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSignUp, 0, SpringLayout.NORTH, btnCancel);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCancel, 0, SpringLayout.WEST, lblNewLabel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancel);
		
		JButton btnCheckAvailability = new JButton("Check availability");
		btnCheckAvailability.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					PopUpWindow popUp = new PopUpWindow("Function not available yet. The developer is fucking tired.");
			    }
			}
		});
		btnCheckAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopUpWindow valid = new PopUpWindow("Function not available yet. The developer is fucking tired.");
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnCheckAvailability, 0, SpringLayout.NORTH, textField_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCheckAvailability, 0, SpringLayout.WEST, rdbtnPreferNotTo);
		contentPane.add(btnCheckAvailability);
		
		JLabel lblSecurityQuestion = new JLabel("Security question:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSecurityQuestion, 12, SpringLayout.SOUTH, lblMustHave);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSecurityQuestion, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblSecurityQuestion);
		
		textField_3 = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_3, -5, SpringLayout.NORTH, lblSecurityQuestion);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_3, 7, SpringLayout.EAST, lblSecurityQuestion);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_3, 0, SpringLayout.EAST, lblMustContainOne);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAnswer = new JLabel("Answer:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAnswer, 12, SpringLayout.SOUTH, lblSecurityQuestion);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAnswer, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblAnswer);
		
		textField_4 = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_4, -5, SpringLayout.NORTH, lblAnswer);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_4, 6, SpringLayout.EAST, lblAnswer);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_4, -47, SpringLayout.EAST, contentPane);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		setVisible(true);
	}
	
	public boolean checkPw() {
		String password = passwordField.getText();
		String confirmPw = passwordField_1.getText();
		if (password.length() >= 6) {
			boolean isValidPw = isValidPassword(password);
			if (isValidPw) {
				boolean confirmedPw = confirmPassword(password, confirmPw);
				if (confirmedPw) {
					PopUpWindow valid = new PopUpWindow("Passwords match and are valid.");
					return true;
				}
				else {
					PopUpWindow noMatch = new PopUpWindow("Passwords do not match");
					return false;
				}
			}
			else {
				PopUpWindow noSpecialCharacter = new PopUpWindow("Your password must contain at least one special character, one number, and one uppercase letter.");
				return false;
			}
		}
		else {
			PopUpWindow badLength = new PopUpWindow("You must enter a password at least six characters long.");
			return false;
		}
	}
	
	public boolean isValidPassword(String password) {
		char[] chars = password.toCharArray();
		boolean hasDigit = false;
		boolean hasUpperCase = false;
		boolean hasSpecialCharacter = false;
		
		for(char c : chars) {
			if (Character.isDigit(c)) {
				hasDigit = true;
			}
			if (Character.isUpperCase(c)) {
				hasUpperCase = true;
			} 
			if (!(Character.isWhitespace(c)) && !(Character.isDigit(c)) && !(Character.isLetter(c))) {
				hasSpecialCharacter = true;
			}
		}
		
		if ((hasDigit) && (hasUpperCase) && (hasSpecialCharacter)) {
			return true;
		}
		return false;
	}
	
	public boolean confirmPassword(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return true;
		}
		return false;
	}

}
