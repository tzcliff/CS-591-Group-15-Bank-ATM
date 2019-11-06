package bank;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class LoginController {

	private LoginView loginView;
	private JPanel parentPanel;
	private CustomerSideBarView custSideView;
	private ManagerSideView managerSideView;
	private MainSideView mainSideView;

	public LoginController() {
		loginView =  new LoginView();
		custSideView = new CustomerSideBarView();
		managerSideView = new ManagerSideView();
		mainSideView = new MainSideView();
		loginView.setVisible(true);
		//new LoginModel();
		parentPanel = PanelData.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(loginView, BorderLayout.CENTER);
		//LoggedUser.ClearData();
		PanelData.setSidePanel(new JPanel());
		PanelData.setSidePanel(mainSideView);



		initController();
	}

	public void initController()
	{
		loginView.getLoginButton().addActionListener(l -> Login());
		loginView.getNewButton().addActionListener(l -> AddUser());
		loginView.getLoginManagerButton().addActionListener(l -> loginManager());
	}

	public void Login()
	{
		Person p = new Person(loginView.getfNAmeTextField().getText(), loginView.getlNameTextField().getText());
		
		var loggedUser = Data.getBank().loginCustomer(p);
		if (loggedUser != null)
		{
			custSideView.setVisible(true);	
			PanelData.setSidePanel(custSideView);
			ProfileController proController = new ProfileController();
			proController.showLoggedUserProfile(loggedUser);
		}
		else
		{
			loginView.setMsgLabel("No User");
		}
		
	}

	public void AddUser()
	{
		ProfileController proController = new ProfileController();
		proController.setNew(true);
		proController.showNewUser();
		
	}

	public void loginManager()
	{
		managerSideView.setVisible(true);		
		PanelData.setSidePanel(managerSideView);
		
		ProfileController proController = new ProfileController();
		proController.ShowProfileList();
	}

}
