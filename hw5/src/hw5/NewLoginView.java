package hw5;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class NewLoginView  extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;	
	private JButton loginButton;
	private JButton newButton;
	private JLabel msgLabel;
	private JLabel headerLabel;
	
	public NewLoginView()
	{
		headerLabel = new JLabel("Please log in");
		emailLabel = new JLabel("Email", JLabel.TRAILING);		
		emailTextField = new JTextField(20);
		emailLabel.setLabelFor(emailTextField);
		passwordLabel = new JLabel("Password", JLabel.TRAILING);
		passwordTextField = new JPasswordField(20);
		passwordLabel.setLabelFor(passwordTextField);
		loginButton = new JButton("Sign in");
		newButton = new JButton("New User");
		msgLabel = new JLabel("");
		
		JPanel jp = new JPanel(new SpringLayout());

		this.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
	    
		jp.add(emailLabel);		
		jp.add(emailTextField);
		jp.add(passwordLabel);
		jp.add(passwordTextField);
		//jp.add(loginButton);
		//jp.add(msgLabel);
		
		//this.add(calcPanel);
		
		SpringUtilities.makeCompactGrid(jp, 2, 2, //rows, cols
		        6, 6, //initX, initY
		        6, 6); //xPad, yPad
		this.add(headerLabel, gbc);
		this.add(jp, gbc);
		this.add(msgLabel, gbc);
		this.add(loginButton,gbc);
		this.add(newButton,gbc);
		
	}
	
	public JLabel getEmailLabel() {
		return emailLabel;
	}


	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
	}


	public JLabel getPasswordLabel() {
		return passwordLabel;
	}


	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}


	public JTextField getEmailTextField() {
		return emailTextField;
	}


	public void setEmailTextField(JTextField emailTextField) {
		this.emailTextField = emailTextField;
	}


	public JLabel getMsgLabel() {
		return msgLabel;
	}

	public void setMsgLabel(String msgLabel) {
		this.msgLabel.setText(msgLabel);
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}


	public JButton getLoginButton() {
		return loginButton;
	}


	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public JButton getNewButton() {
		return newButton;
	}

	public void setNewButton(JButton newButton) {
		this.newButton = newButton;
	}
	
}
