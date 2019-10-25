package hw5;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TransactionController{

	private static final long serialVersionUID = 1L;
	private JPanel transactionView;


	public TransactionController()
	{
		transactionView = new TransactionViewList();

	}

	public void transactionList()
	{
		transactionView.setVisible(true);
		PanelData.setParentPanel(transactionView);
		bindData();	
	}

	public void transactionList(String id)
	{
		transactionView.setVisible(true);
		PanelData.setParentPanel(transactionView);
		bindData(id);	
	}

	private void bindData()
	{
		if (LoggedUser.getProfile() != null && Data.getTransactionList() != null)
		{
			//System.out.println(LoggedUser.getProfile().getFirstName());

			String col[] = {"Transaction Number","Customer Name","Amount", "Transaction Type", "Fee","Date",};
			DefaultTableModel tableModel = new DefaultTableModel(col, 0);


			for (int i = 0; i < Data.getTransactionList().size(); i++)
			{
				Object[] objs = {Data.getTransactionList().get(i).getTransactionId(),
						Data.getTransactionList().get(i).getTransactionBy().getLastName(), 
						Data.getTransactionList().get(i).getAmount(),
						Data.getTransactionList().get(i).getType(),
						Data.getTransactionList().get(i).getFee(),
						Data.getTransactionList().get(i).getTransactionDate()
				};
				tableModel.addRow(objs);
			}

			JTable table = new JTable(tableModel);
			table.setFillsViewportHeight(true);
			((TransactionViewList)transactionView).setTable(table);


		}
	}

	private void bindData(String id)
	{
		if (LoggedUser.getProfile() != null)
		{
			//System.out.println(LoggedUser.getProfile().getFirstName());
			ArrayList<Transaction> aT = new ArrayList<Transaction>();
			String col[] = {"Transaction Number","Customer Name","Amount", "Transaction Type","Fee", "Date"};
			DefaultTableModel tableModel = new DefaultTableModel(col, 0);

			for (var aL : LoggedUser.getProfile().getAccountList())
			{
				if (aL.getAccountId() == id)
				{
					aT = aL.getTransactionList();
					break;

				}
			}

			if (aT != null)
			{
				for (int i = 0; i < aT.size(); i++)
				{
					Object[] objs = {aT.get(i).getTransactionId(),
							aT.get(i).getTransactionBy().getLastName(), 
							aT.get(i).getAmount(),
							aT.get(i).getType(),
							aT.get(i).getFee(),
							aT.get(i).getTransactionDate()
					};
					tableModel.addRow(objs);
				}
			}

			JTable table = new JTable(tableModel);
			table.setFillsViewportHeight(true);
			((TransactionViewList)transactionView).setTable(table);


		}
	}

}
