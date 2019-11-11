package bank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bank.Controller.LoginController;
import bank.MySql.DBManager;

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
	public WindowsBuilder(DBManager dbManager) {
		initialize(dbManager);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(DBManager dbManager) {
		
		
        frame = new JFrame();
        frame.setVisible(true);
		PanelData.InitiatePanelData(frame, dbManager);
        LoginController loginController = new LoginController();	
        
	}

}
