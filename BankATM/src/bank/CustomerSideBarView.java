package bank;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CustomerSideBarView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton profileButton;
	private JButton accListButton;
	private JButton newAccButton;
	private JButton loanListButton;
	private JButton newLoanButton;
	private JButton logOutButton;
	
	public CustomerSideBarView()
	{
		//parentPanel = jP;
		
		profileButton = new JButton("My Profile");
		accListButton = new JButton("Account List");
		newAccButton = new JButton("Create New Account");
		loanListButton = new JButton("Loan List");
		newLoanButton = new JButton("Apply New Loan");
		logOutButton = new JButton("Log out");
		
		this.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
	    		
		
		this.add(profileButton, gbc);
		this.add(accListButton, gbc);
		this.add(newAccButton, gbc);
		this.add(loanListButton, gbc);
		this.add(newLoanButton, gbc);
		this.add(logOutButton, gbc);
		initialize();
	}
	
	private void initialize()
	{
		
		
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//JPanel pa =  new ProfileView();		    	
		        //ProfileController proController = new ProfileController();
		        //proController.showLoggedUserProfile();
				
			}
		});
		
		accListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    	
				AccountController accController = new AccountController();
		        accController.accountList();
				
			}
		});
		
		loanListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	    	
				//LoanController loanController = new LoanController();
		        
				
			}
		});
		newAccButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountController accController = new AccountController();
		        accController.newAccount();
		        
				
			}
		});
		newLoanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//JPanel pa =  new ProfileView();		    	
				//LoanController loanController = new LoanController();
				//loanController.NewLoan();
		        
				
			}
		});
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    	
				LoginController loginController = new LoginController();
			}
		});
	}
}
