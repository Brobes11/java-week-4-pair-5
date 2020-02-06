package com.techelevator;
import java.math.BigDecimal;

public interface VendableItems {
	public String slot();
	public String getName();
	public BigDecimal getPrice();
	public int getQuantity();
	public String dispense();
	
	
	

}
