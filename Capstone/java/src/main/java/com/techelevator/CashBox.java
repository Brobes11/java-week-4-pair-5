package com.techelevator;

import java.math.BigDecimal;

public class CashBox {

	private BigDecimal balance;
	private BigDecimal spent;

	public CashBox() {
		this.balance = new BigDecimal("0.00");
		this.spent = new BigDecimal("0.00");
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getChange() {
		int dollar = 0;
		int quarter = 0;
		int dime = 0;
		int nickel = 0;
		String dollarLabel ="dollar(s)";
		String quarterLabel ="quarter(s)";
		String dimeLabel="dime(s)";
		String nickelLabel="nickel(s)";
		
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
				nickel++;
			}
		}

		
		String result = "\nYour change is: \n"+ dollar +" "+ dollarLabel+ ", "+ quarter +" "+ quarterLabel+", "
		+ dime +" "+ dimeLabel +", " + nickel + " "+ nickelLabel;
		
		return result;
		
		}
	

	public void makeDeposit(BigDecimal depositAmt) {
		if (depositAmt.compareTo(BigDecimal.ZERO) > 0){
		balance = balance.add(depositAmt);
		} else {
			System.out.println("Please deposit a valid amount");
		}
	}

	public void makePurchase(VendableItem selectedItem) {
		balance = balance.subtract(selectedItem.getPrice());
		spent = spent.add(selectedItem.getPrice());
	}
	public BigDecimal getSpent() {
		return spent;
	}
}