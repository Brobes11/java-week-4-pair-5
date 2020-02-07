package com.techelevator;

import java.math.BigDecimal;

public class CashBox {

	private BigDecimal balance;
	private BigDecimal change;
	
	public CashBox() {
		this.balance = new BigDecimal("0.00");
	}

	public BigDecimal getBalance() {
		return balance;
	}
	public String getChange() {
		String result = "";
		return result;
	}
	public void makeDeposit(BigDecimal depositAmt) {
		balance = balance.add(depositAmt);
	}
}