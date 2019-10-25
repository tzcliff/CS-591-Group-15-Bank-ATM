package hw5;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class AccountController {
	private JPanel accountListView;
	private JPanel depositWView;
	private JPanel addAccountView;
	

	public AccountController() {
		accountListView = new AccountListView();
		addAccountView = new AddAccountView();
		depositWView = new DepositWithdrawView();
		initController();
	}

	public void accountList()
	{
		accountListView.setVisible(true);
		PanelData.setParentPanel(accountListView);
		bindData();	
	}


	public void initController()
	{
		((AccountListView)accountListView).getNewAccountButton().addActionListener(l -> newAccount());
		((AccountListView)accountListView).getTransactionButton().addActionListener(l -> newTransaction());
		((AddAccountView)addAccountView).getSaveButton().addActionListener(l -> AddNewAccount());
		((DepositWithdrawView)depositWView).getSaveButton().addActionListener(l -> AddTransaction());
	}

	public void newAccount() {
		System.out.print("masuk");
		addAccountView.setVisible(true);
		PanelData.setParentPanel(addAccountView);
	}
	private void newTransaction() {
		if (LoggedUser.getProfile() != null && LoggedUser.getProfile().getAccountList() != null)
		{
			System.out.print("masuk");
			depositWView.setVisible(true);
			PanelData.setParentPanel(depositWView);
		}
	}

	private void AddTransaction() {

		boolean isAllowed = true;
		for (Account acc : LoggedUser.getProfile().getAccountList()) {

			if (acc.equals(((DepositWithdrawView)depositWView).getAccNameDd().getSelectedItem()))
			{
				if (((DepositWithdrawView)depositWView).getTransTypeDd().getSelectedItem() == TransactionType.Deposit)
				{
					acc.setAmount(acc.getAmount() + 
							Long.parseLong(((DepositWithdrawView)depositWView).getAmountTextField().getText()));
				}else
				{
					if (acc.getAmount() >= (Long.parseLong(((DepositWithdrawView)depositWView).getAmountTextField().getText())
							+ FeesAndInterest.Withdrawalfee(acc.getType())))
					{
						acc.setAmount(acc.getAmount() - 
								(Long.parseLong(((DepositWithdrawView)depositWView).getAmountTextField().getText())
										+ FeesAndInterest.Withdrawalfee(acc.getType())));
					}else
					{
						((DepositWithdrawView)depositWView).setMsgLabel("Your transaction is unsuccessfull. Your balance is not enough");
						isAllowed  = false;
					}
				}
				if (isAllowed)
				{
					Transaction trans = new Transaction();
					trans.setAmount(Long.parseLong(((DepositWithdrawView)depositWView).getAmountTextField().getText()));
					trans.setTransactionDate(new Date());
					trans.setTransactionBy(LoggedUser.getProfile());
					trans.setTransactionId(UUID.randomUUID().toString());
					trans.setType((TransactionType)((DepositWithdrawView)depositWView).getTransTypeDd().getSelectedItem());
					trans.setAccountUsed((Account)((DepositWithdrawView)depositWView).getAccNameDd().getSelectedItem());
					trans.setFee(FeesAndInterest.Withdrawalfee(acc.getType()));
					Data.AddTransaction(trans);
					acc.addTransactionList(trans);
					
					((DepositWithdrawView)depositWView).setMsgLabel("Your transaction is successfull");
				}

				break;
			}
		}

	}

	private void AddNewAccount()
	{
		//System.out.print("Masuk add account");
		if (LoggedUser.getProfile() != null)
		{
			Account acc = new Account();
			acc.setAccountId(UUID.randomUUID().toString());
			acc.setAmount(0);
			acc.setType((AccountType)((AddAccountView)addAccountView).getAccTypeDd().getSelectedItem());
			acc.setFriendlyName(((AddAccountView)addAccountView).getFriendlyNameTextField().getText());
			acc.setCurrency((CurrencyType)((AddAccountView)addAccountView).getCurrTypeDd().getSelectedItem());
			LoggedUser.getProfile().AddAccount(acc);
		}
	}

	private void bindData()
	{
		if (LoggedUser.getProfile() != null && LoggedUser.getProfile().getAccountList() != null)
		{
			//System.out.println(LoggedUser.getProfile().getFirstName());

			String col[] = {"Account Number","Friendly Name","Account Type","Currency","Amount"};
			DefaultTableModel tableModel = new DefaultTableModel(col, 0);


			for (int i = 0; i < LoggedUser.getProfile().getAccountList().size(); i++)
			{
				Object[] objs = {LoggedUser.getProfile().getAccountList().get(i).getAccountId(),
						LoggedUser.getProfile().getAccountList().get(i).getFriendlyName(), 
						LoggedUser.getProfile().getAccountList().get(i).getType(), 
						LoggedUser.getProfile().getAccountList().get(i).getCurrency(),
						LoggedUser.getProfile().getAccountList().get(i).getAmount()};
				tableModel.addRow(objs);
			}

			JTable table = new JTable(tableModel);
			table.setFillsViewportHeight(true);
			
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent event) {
					if (table.getSelectedRow() > -1) {
						// print first column value from selected row
						String id = table.getValueAt(table.getSelectedRow(), 0).toString();
						TransactionController tc = new TransactionController();
						tc.transactionList(id);
						//ShowCustomerProfile(id);

					}
				}
			});
			
			((AccountListView)accountListView).setTable(table);


		}
	}
}
