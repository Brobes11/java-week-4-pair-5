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
		String dollarLabel ="";
		String quarterLabel ="";
		String dimeLabel="";
		String nickelLabel="";
		
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
		if (dollar >1) {
			dollarLabel= "dollars";
		} else if (dollar == 1){
			dollarLabel = "dollar";
		}
		if (quarter >1) {
			quarterLabel = "quarters";
		} else if (quarter ==1) {
			quarterLabel = "quarter";
		}
		if (dime >1) {
			dimeLabel = "dimes";
		} else if (dime ==1) {
			dimeLabel = "dime";
		}
		if (nickel >1) {
			nickelLabel = "nickels";
		} else if (quarter ==1) {
			nickelLabel = "nickel";
		}
		
		
		
		String result = "Your change is "+ dollar + dollarLabel+ ", "+ quarter + quarterLabel+", "
		+ dime + dimeLabel +", " + nickel + nickelLabel;
		
		return result;
		
		}
	

	public void makeDeposit(BigDecimal depositAmt) {
		balance = balance.add(depositAmt);
		
	}

	public void makePurchase(VendableItem selectedItem) {
		balance = balance.subtract(selectedItem.getPrice());
		spent = spent.add(selectedItem.getPrice());
	}
	public BigDecimal getSpent() {
		return spent;
	}
}