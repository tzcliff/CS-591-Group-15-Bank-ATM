package hw5;

import java.util.Date;

public class Transaction {

	private String TransactionId;
	private TransactionType Type;
	private Date TransactionDate; 
	private long Amount;
	private Profiles TransactionBy;
	private Account AccountUsed;
	private long Fee;
	private long Interest;
	private String Description;
	
	public String getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}
	public TransactionType getType() {
		return Type;
	}
	public void setType(TransactionType type) {
		Type = type;
	}
	public Date getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		TransactionDate = transactionDate;
	}
	public long getAmount() {
		return Amount;
	}
	public void setAmount(long amount) {
		Amount = amount;
	}
	public Profiles getTransactionBy() {
		return TransactionBy;
	}
	public void setTransactionBy(Profiles transactionBy) {
		TransactionBy = transactionBy;
	}
	public Account getAccountUsed() {
		return AccountUsed;
	}
	public void setAccountUsed(Account accountUsed) {
		AccountUsed = accountUsed;
	}
	public long getFee() {
		return Fee;
	}
	public void setFee(long fee) {
		Fee = fee;
	}
	public long getInterest() {
		return Interest;
	}
	public void setInterest(long interest) {
		Interest = interest;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
}
