package bank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bank.Controller.LoginController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class WindowsBuilder {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public WindowsBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
        frame = new JFrame();
        frame.setVisible(true);
		PanelData.InitiatePanelData(frame);		
        LoginController loginController = new LoginController();
        
	}

}
