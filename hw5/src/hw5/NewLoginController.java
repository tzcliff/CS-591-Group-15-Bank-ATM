package hw5;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class NewLoginController {

	private NewLoginView loginView;
	private LoginModel loginModel;
	private JPanel parentPanel;
	private CustomerSideBarView custSideView;
	private ManagerSideView managerSideView;

	public NewLoginController() {
		loginView =  new NewLoginView();
		custSideView = new CustomerSideBarView();
		managerSideView = new ManagerSideView();
		loginView.setVisible(true);
		this.loginModel = new LoginModel();
		parentPanel = PanelData.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(loginView, BorderLayout.CENTER);
		LoggedUser.ClearData();
		PanelData.setSidePanel(new JPanel());
		initController();
	}

	public void initController()
	{
		loginView.getLoginButton().addActionListener(l -> Login());
		loginView.getNewButton().addActionListener(l -> AddUser());
	}

	public void Login()
	{
		loginModel.setEmail(loginView.getEmailTextField().getText());
		loginModel.setPassword(loginView.getPasswordTextField().getText());

		System.out.println(loginModel.getEmail());
		System.out.println(loginModel.getPassword());
		Boolean isLogged = false;
		Boolean isManager = false;

		for (int i = 0;  i < Data.getProfileList().size(); i++)
		{
			System.out.println(Data.getProfileList().get(i).getEmail());
			System.out.println(Data.getProfileList().get(i).getPassword());

			if (Data.getProfileList().get(i).getEmail().equals(loginModel.getEmail()) &&
					Data.getProfileList().get(i).getPassword().equals(loginModel.getPassword()))
			{
				isLogged = true;
				LoggedUser.setProfile(Data.getProfileList().get(i));
				if (LoggedUser.getProfile().getUserType() == UserType.BankManager)
				{
					isManager = true;
				}
				break;
			}			
		}

		if (isLogged)
		{
			if (!isManager)
			{
				custSideView.setVisible(true);		
				PanelData.setSidePanel(custSideView);
				ProfileController proController = new ProfileController();
				proController.showLoggedUserProfile();
			}else
			{
				managerSideView.setVisible(true);		
				PanelData.setSidePanel(managerSideView);
				ProfileController poController = new ProfileController();
				poController.ShowProfileList();
			}
			

		}else
		{
			loginView.setMsgLabel("Wrong email or password");
		}



	}

	public void AddUser()
	{
		ProfileController proController = new ProfileController();
		proController.setNew(true);
		proController.showLoggedUserProfile();
	}

}
