package hw5;

import java.util.ArrayList;
import java.util.Date;

public class Account {

	private String AccountId;
	private long Amount;
	private AccountType Type;
	private ArrayList<Transaction> transactionList;
	private Date TransactionDate;
	private String FriendlyName;
	private CurrencyType Currency;
	
	public String getAccountId() {
		return AccountId;
	}
	public void setAccountId(String accountId) {
		AccountId = accountId;
	}
	public long getAmount() {
		return Amount;
	}
	public void setAmount(long amount) {
		Amount = amount;
	}
	public AccountType getType() {
		return Type;
	}
	public void setType(AccountType type) {
		Type = type;
	}
	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	public void addTransactionList(Transaction transactionList) {
		if (this.transactionList == null)
		{
			this.transactionList = new ArrayList<Transaction>();
		}
		this.transactionList.add(transactionList);
	}
	public Date getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		TransactionDate = transactionDate;
	}
	public String getFriendlyName() {
		return FriendlyName;
	}
	public void setFriendlyName(String friendlyName) {
		FriendlyName = friendlyName;
	}
	public String toString() {
        return getType()+"-"+getFriendlyName() + "-"+getAmount();
    }
	
	public boolean equals(Account o) { 
		if (o.getAccountId() == AccountId )
			return true;
		
		return false;
	}
	public CurrencyType getCurrency() {
		return Currency;
	}
	public void setCurrency(CurrencyType currency) {
		Currency = currency;
	}
	
	
}
