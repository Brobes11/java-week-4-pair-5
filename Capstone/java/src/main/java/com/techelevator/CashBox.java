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
		int dollar = 0;
		int quarter = 0;
		int dime = 0;
		int nickle = 0;
		int penny;
		int newBalance;
		BigDecimal pennies = new BigDecimal("100");
		BigDecimal centsBalance = balance.multiply(pennies);
		int c = centsBalance.intValue();
		while (c > 0) {
			if (c > 100) {
				c -= 100;
				dollar++;
			}
			if (c < 100 && c >= 25) {
				c -= 25;
				quarter++;
			}
			if (c < 25 && c >= 10) {
				c -= 10;
				dime++;
			}
			if ( c >0 && c <=5) {
				c -=5;
				nickle++;
			}
		}
		String result = "";
		return result;
	}

	public void makeDeposit(BigDecimal depositAmt) {
		balance = balance.add(depositAmt);
	}

	public void makePurchase(VendableItem selectedItem) {
		balance = balance.subtract(selectedItem.getPrice());
	}
}