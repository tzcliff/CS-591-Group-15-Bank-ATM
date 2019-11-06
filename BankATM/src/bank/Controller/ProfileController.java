package bank.Controller;

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

import bank.CustomerAccount;
import bank.Data;
import bank.PanelData;
import bank.Person;
import bank.View.ProfileListView;
import bank.View.ProfileView;

public class ProfileController {

	private JPanel profileView;
	private JPanel profileList;
	private boolean isNew;
	//private String custId;

	public void initController()
	{
		//profileView =  new ProfileView();
		((ProfileView)profileView).getSaveButton().addActionListener(l -> SaveData());
	}

	

	public void SaveData()
	{
		
		if (isNew)
		{
			Person p = new Person(((ProfileView)profileView).getFirstNameTextField().getText(), ((ProfileView)profileView).getLastNameTextField().getText());
			Data.getBank().registerNewCustomer(p);
			((ProfileView)profileView).setMsgLabel("Data Saved. Please log in");
		}else
		{
			
		}
		
	}

	public ProfileController() {
		profileView = new ProfileView();
		profileList = new ProfileListView();
		
		initController();
		//custId = ""; 
	}

	public void showLoggedUserProfile(CustomerAccount ca)
	{
		profileView.setVisible(true);		
		PanelData.setParentPanel(profileView);
		BindCustomerData(ca);
	}
	
	public void showNewUser()
	{
		profileView.setVisible(true);		
		PanelData.setParentPanel(profileView);
		
	}

	

	private void BindCustomerData(CustomerAccount ca)
	{
				((ProfileView)profileView).setFirstNameTextField(ca.getPerson().toString());
				
				
		
	}

	private void bindData()
	{
		/*
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
		*/
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public void ShowProfileList()
	{

		
		
			//System.out.println(LoggedUser.getProfile().getFirstName());

			String col[] = { "Customer Name"};
			DefaultTableModel tableModel = new DefaultTableModel(col, 0);

			for (CustomerAccount p : Data.getBank().getCustomerAccounts())
			{
				
					Object[] objs = {
							p.toString(),
												};
					tableModel.addRow(objs);
				
			}

			JTable table = new JTable(tableModel);
			table.setFillsViewportHeight(true);

			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent event) {
					if (table.getSelectedRow() > -1) {
						// print first column value from selected row
						//String id = table.getValueAt(table.getSelectedRow(), 0).toString();
						//ShowCustomerProfile(id);

					}
				}
			});

			((ProfileListView)profileList).setTable(table);



		profileList.setVisible(true);		
		PanelData.setParentPanel(profileList);
		
		


	}


}
