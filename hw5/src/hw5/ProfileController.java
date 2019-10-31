package hw5;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ProfileController {

	private JPanel profileView;
	private JPanel profileList;
	private boolean isNew;
	private String custId;

	public void initController()
	{
		((ProfileView)profileView).getSaveButton().addActionListener(l -> SaveData());
	}

	

	public void SaveData()
	{
		Profiles pro =  null;
		boolean exist = false;
		if (isNew)
		{
			for(Profiles d : Data.getProfileList()){
				if(d.getEmail() != null && 
						d.getEmail().equalsIgnoreCase(((ProfileView)profileView).getEmailTextField().getText()))
				{
					exist = true;
					break;
				}
			}
			if (!exist)
			{
				pro = new Profiles();
				pro.setProfileId(UUID.randomUUID().toString());
				pro.setFirstName(((ProfileView)profileView).getFirstNameTextField().getText());
				pro.setLastName(((ProfileView)profileView).getLastNameTextField().getText());
				pro.setMiddleName(((ProfileView)profileView).getMiddleNameTextField().getText());
				pro.setAddress(((ProfileView)profileView).getAddressTextField().getText());
				pro.setEmail(((ProfileView)profileView).getEmailTextField().getText());
				pro.setPassword(((ProfileView)profileView).getPasswordTextField().getText());
				pro.setUserType(UserType.Customer);
				Data.AddProfile(pro);
				isNew = false;		
				((ProfileView)profileView).setMsgLabel("Account is created, please log in");
			}
			else
			{
				((ProfileView)profileView).setMsgLabel("The email has been used, please use other email to create new record");
			}
		}
		else
		{
			if (custId == "")
			{
				pro = LoggedUser.getProfile() ;
			}
			else
			{
				for (Profiles p : Data.getProfileList())
				{
					if (p.getUserType() == UserType.Customer && p.getProfileId() == custId)
					{
						pro = p;
					}
				}
			}
			pro.setFirstName(((ProfileView)profileView).getFirstNameTextField().getText());
			pro.setLastName(((ProfileView)profileView).getLastNameTextField().getText());
			pro.setMiddleName(((ProfileView)profileView).getMiddleNameTextField().getText());
			pro.setAddress(((ProfileView)profileView).getAddressTextField().getText());
			pro.setEmail(((ProfileView)profileView).getEmailTextField().getText());
			pro.setPassword(((ProfileView)profileView).getPasswordTextField().getText());
			((ProfileView)profileView).setMsgLabel("The data has been saved");
		}
	}

	public ProfileController() {
		profileView = new ProfileView();
		profileList = new ProfileListView();
		
		initController();
		custId = ""; 
	}

	public void showLoggedUserProfile()
	{
		profileView.setVisible(true);		
		PanelData.setParentPanel(profileView);
		bindData();
	}

	public void ShowCustomerProfile(String id)
	{
		profileView.setVisible(true);		
		PanelData.setParentPanel(profileView);
		BindCustomerData(id);
	}

	private void BindCustomerData(String id)
	{
		for (Profiles p : Data.getProfileList())
		{
			if (p.getUserType() == UserType.Customer && p.getProfileId() == id)
			{
				((ProfileView)profileView).setFirstNameTextField(p.getFirstName());
				((ProfileView)profileView).setLastNameTextField(p.getLastName());
				((ProfileView)profileView).setMiddleNameTextField(p.getMiddleName());
				((ProfileView)profileView).setAddressTextField(p.getAddress());
				((ProfileView)profileView).setEmailTextField(p.getEmail());
				((ProfileView)profileView).setPasswordTextField(p.getPassword());
				custId = id;
				break;
			}
		}
	}

	private void bindData()
	{
		if (LoggedUser.getProfile() != null)
		{
			System.out.println(LoggedUser.getProfile().getFirstName());
			((ProfileView)profileView).setFirstNameTextField(LoggedUser.getProfile().getFirstName());
			((ProfileView)profileView).setLastNameTextField(LoggedUser.getProfile().getLastName());
			((ProfileView)profileView).setMiddleNameTextField(LoggedUser.getProfile().getMiddleName());
			((ProfileView)profileView).setAddressTextField(LoggedUser.getProfile().getAddress());
			((ProfileView)profileView).setEmailTextField(LoggedUser.getProfile().getEmail());
			((ProfileView)profileView).setPasswordTextField(LoggedUser.getProfile().getPassword());
		}
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public void ShowProfileList()
	{

		if (LoggedUser.getProfile() != null && LoggedUser.getProfile().getUserType() == UserType.BankManager)
		{
			//System.out.println(LoggedUser.getProfile().getFirstName());

			String col[] = {"Customer Id", "Customer Name", "Number of Account", "Number of Loan",  "Total Loan Amount", "Number of Account", "Total Balance"};
			DefaultTableModel tableModel = new DefaultTableModel(col, 0);

			for (Profiles p : Data.getProfileList())
			{
				if (p.getUserType() == UserType.Customer)
				{
					Object[] objs = {
							p.getProfileId(),
							p.toString(),
							p.getAccountList().size(),
							p.getLoanList().size(),
							p.getLoanList().stream().filter(o -> o.getAmount() > 0).mapToLong(i -> i.getAmount()).sum(),
							p.getAccountList().size(),
							p.getAccountList().stream().filter(o -> o.getAmount() > 0).mapToLong(i -> i.getAmount()).sum()
					};
					tableModel.addRow(objs);
				}
			}

			JTable table = new JTable(tableModel);
			table.setFillsViewportHeight(true);

			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent event) {
					if (table.getSelectedRow() > -1) {
						// print first column value from selected row
						String id = table.getValueAt(table.getSelectedRow(), 0).toString();
						ShowCustomerProfile(id);

					}
				}
			});

			((ProfileListView)profileList).setTable(table);




		}

		profileList.setVisible(true);		
		PanelData.setParentPanel(profileList);


	}


}
