package bank;

import javax.swing.JFrame;

import bank.Controller.LoginController;
import bank.MySql.DBManager;

import java.util.ArrayList;

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

//		Data.getStockMarket().stocks = (ArrayList<Stock>) dbManager.readStocks();
//		for(Stock stock : Data.getStockMarket().stocks){
//			System.out.println(stock.getName());
//		}
//		for(CheckingAccount checkingAccount : dbManager.readCheckingAccounts("1", "1")){
//			System.out.println(checkingAccount.getAccountNumber());
//		}
//
//		for(SavingsAccount savingsAccount : dbManager.readSavingAccounts("1", "1")){
//			System.out.println(savingsAccount.getAccountNumber());
//		}
//
//		for(SecurityAccount securityAccount : dbManager.readSecurityAccounts("1", "1")){
//			System.out.println(securityAccount.getAccountNumber());
//		}
//		for(BoughtStock boughtStock : dbManager.readBoughtStocks(new SecurityAccount(0.0f, 1,
//				10000, true, new Currency("USD"), 0.0f, 0.0f))){
//			System.out.println(boughtStock.getStock().getTotalShares());
//		}
//		for(Deposit deposit : dbManager.readDeposits()){
//			System.out.println("Deposit");
//			System.out.println(deposit.amount);
//		}
//		for(Withdrawal withdrawal : dbManager.readWithdrawals()){
//			System.out.println("Withdrawal");
//			System.out.println(withdrawal.amount);
//		}
//		for(Transfer transfer: dbManager.readTransfers()){
//			System.out.println("Transfer");
//			System.out.println(transfer.amount);
//		}





		
        frame = new JFrame();
        frame.setVisible(true);
		PanelData.InitiatePanelData(frame, dbManager);
        LoginController loginController = new LoginController();

//        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		frame.addWindowListener(new java.awt.event.WindowAdapter() {
//			@Override
//			public void windowClosing(java.awt.event.WindowEvent e) {
//				System.out.println("Uncomment following to open another window!");
//
//				dbManager.deleteAccount();
//				dbManager.deletePerson();
//				dbManager.deleteBoughtStock();
//				dbManager.deleteStock();
//				dbManager.deleteLoan();
//				dbManager.deleteTransaction();
//				for(Stock stock : Data.getStockMarket().stocks){
//					dbManager.addStock(stock);
//				}
//
//				for(CustomerAccount customerAccount : Data.getBank().getCustomerAccounts()){
//					dbManager.addPerson(customerAccount);
//					for(SavingsAccount savingsAccount : customerAccount.getSavingsAccounts()){
//						dbManager.addSavingAccount(savingsAccount, customerAccount);
//					}
//					for(CheckingAccount checkingAccount : customerAccount.getCheckingAccounts()){
//						dbManager.addCheckingAccount(checkingAccount, customerAccount);
//					}
//
//					dbManager.addSecurityAccount( customerAccount.getSecurityAccount(), customerAccount);
//
//					for(Loan loan : customerAccount.getLoans()){
//						dbManager.addLoan(loan, customerAccount);
//					}
//
//					for(BoughtStock boughtStock : customerAccount.getSecurityAccount().getBoughtStocks()){
//						dbManager.addBoughtStock(boughtStock, customerAccount.getSecurityAccount());
//					}
//
//				}
//
//				dbManager.addDeposit(new Deposit(100f, new Currency("USD"), 0, 0));
//				dbManager.addWithdrawal(new Withdrawal(100f, new Currency("USD"), 0, 0));
//				dbManager.addTransfer(new Transfer(100f, new Currency("USD"), 0, 0, 0, 1));
//				e.getWindow().dispose();
//				System.out.println("JFrame Closed!");
//				dbManager.close();
//
//			}
//		});
        
	}

}
