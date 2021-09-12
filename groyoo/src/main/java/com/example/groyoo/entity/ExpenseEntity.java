package com.example.groyoo.entity;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseEntity {

	String mainUserId;
	BigDecimal expense;
	boolean PorA;
	int totalPayees;
	List<String> payeeId;
	List<BigDecimal> contribution;
	public String getMainUserId() {
		return mainUserId;
	}
	public void setMainUserId(String mainUserId) {
		this.mainUserId = mainUserId;
	}
	public BigDecimal getExpense() {
		return expense;
	}
	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}
	public boolean isPorA() {
		return PorA;
	}
	public void setPorA(boolean porA) {
		PorA = porA;
	}
	public int getTotalPayees() {
		return totalPayees;
	}
	public void setTotalPayees(int totalPayees) {
		this.totalPayees = totalPayees;
	}
	public List<String> getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(List<String> payeeId) {
		this.payeeId = payeeId;
	}
	public List<BigDecimal> getContribution() {
		return contribution;
	}
	public void setContribution(List<BigDecimal> contribution) {
		this.contribution = contribution;
	}

}
