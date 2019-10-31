package hw5;

import java.util.UUID;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LoanController {
	
	private JPanel loanListView;
	private JPanel loanAppilcationView;
	private JPanel parentPanel;
	
	
	public LoanController() {
		loanAppilcationView = new LoanAppilcationView();
		loanListView = new LoanListView();
		loanListView.setVisible(true);

		PanelData.setParentPanel(loanListView);
		bindData();
		initController();
	}
	
	public void initController()
	{
		((LoanListView)loanListView).getNewLoanButton().addActionListener(l -> NewLoan());
		((LoanAppilcationView)loanAppilcationView).getSaveButton().addActionListener(l -> AddNewLoan());
	}
	
	public void NewLoan()
	{
		System.out.print("masuk");
		loanAppilcationView.setVisible(true);
		PanelData.setParentPanel(loanAppilcationView);
	}
	
	private void AddNewLoan()
	{
		System.out.print("masuk");
		
		if (LoggedUser.getProfile() != null)
		{
			Loan acc = new Loan();
			acc.setLoanId(UUID.randomUUID().toString());
			acc.setAmount(0);
			acc.setType(((LoanType)((LoanAppilcationView)loanAppilcationView).getLoanTypeDd().getSelectedItem()));
			acc.setAmount(Long.parseLong(((LoanAppilcationView)loanAppilcationView).getAmountTextField().getText()));
			acc.setTerm(Long.parseLong(((LoanAppilcationView)loanAppilcationView).getTermTextField().getText()));
			acc.setCollateral(((LoanAppilcationView)loanAppilcationView).getColateralTextField().getText());
			acc.setStatus(LoanStatus.Pending);
			acc.setProfile(LoggedUser.getProfile());
			LoggedUser.getProfile().AddLoan(acc);
			
			Data.AddLoanList(acc);
			
			((LoanAppilcationView)loanAppilcationView).setMsgLabel("Your appliacation has been submmitted. It is being reviewed by our bank manager");
		}		
	}
	
	private void bindData()
	{
		if (LoggedUser.getProfile() != null && LoggedUser.getProfile().getUserType() == UserType.Customer)
		{
			//System.out.println(LoggedUser.getProfile().getFirstName());
			
			String col[] = {"Loan Number","Loan Type","Amount", "Interest (%/year)", "Collateral", "Status", "Start Date", "Term in Years"};
			DefaultTableModel tableModel = new DefaultTableModel(col, 0);
                        
			for (int i = 0; i < LoggedUser.getProfile().getLoanList().size(); i++)
			{
			Object[] objs = {
					LoggedUser.getProfile().getLoanList().get(i).getLoanId(),
					LoggedUser.getProfile().getLoanList().get(i).getType(), 
					LoggedUser.getProfile().getLoanList().get(i).getAmount(),
					LoggedUser.getProfile().getLoanList().get(i).getInterest(),
					LoggedUser.getProfile().getLoanList().get(i).getCollateral(), 
					LoggedUser.getProfile().getLoanList().get(i).getStatus(),
					LoggedUser.getProfile().getLoanList().get(i).getStartDate(),
					LoggedUser.getProfile().getLoanList().get(i).getTerm()
			};
			tableModel.addRow(objs);
			}
			
			JTable table = new JTable(tableModel);
			table.setFillsViewportHeight(true);
			((LoanListView)loanListView).setTable(table);
						
		}else if (LoggedUser.getProfile() != null && LoggedUser.getProfile().getUserType() == UserType.BankManager)
		{
			//System.out.println(LoggedUser.getProfile().getFirstName());
			
			String col[] = {"Requestor", "Loan Number","Loan Type","Amount", "Interest (%/year)","Collateral", "Status", "Term in Years"};
			DefaultTableModel tableModel = new DefaultTableModel(col, 0);
                        
			for (int i = 0; i < Data.getLoanList().size(); i++)
			{
			Object[] objs = {
					Data.getLoanList().get(i).getProfile().toString(),
					Data.getLoanList().get(i).getLoanId(),
					Data.getLoanList().get(i).getType(), 
					Data.getLoanList().get(i).getAmount(),
					Data.getLoanList().get(i).getInterest(),
					Data.getLoanList().get(i).getCollateral(),
					Data.getLoanList().get(i).getStatus(), 
					Data.getLoanList().get(i).getTerm()
			};
			tableModel.addRow(objs);
			}
			
			JTable table = new JTable(tableModel);
			table.setFillsViewportHeight(true);
			((LoanListView)loanListView).setTable(table);
						
		}
	}

}
