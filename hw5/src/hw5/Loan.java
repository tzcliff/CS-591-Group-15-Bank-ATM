package hw5;

import java.util.Date;

public class Loan {
	
	private String LoanId;
	private long Amount;
	private LoanType Type;
	private Date StartDate;
	private double SRP;
	private LoanStatus Status;
	private String Comment;
	private String Collateral;
	private double Term;
	private double Interest;
	private Profiles profile;
	
	public LoanType getType() {
		return Type;
	}
	public void setType(LoanType type) {
		Type = type;
	}
	public LoanStatus getStatus() {
		return Status;
	}
	public void setStatus(LoanStatus status) {
		Status = status;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getLoanId() {
		return LoanId;
	}
	public void setLoanId(String loanId) {
		LoanId = loanId;
	}
	public long getAmount() {
		return Amount;
	}
	public void setAmount(long amount) {
		Amount = amount;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public double getSRP() {
		return SRP;
	}
	public void setSRP(double sRP) {
		SRP = sRP;
	}
	public String getCollateral() {
		return Collateral;
	}
	public void setCollateral(String collateral) {
		Collateral = collateral;
	}
	public double getTerm() {
		return Term;
	}
	public void setTerm(double term) {
		Term = term;
	}
	public double getInterest() {
		return Interest;
	}
	public void setInterest(double interest) {
		Interest = interest;
	}
	public Profiles getProfile() {
		return profile;
	}
	public void setProfile(Profiles profile) {
		this.profile = profile;
	}
	
	

}
