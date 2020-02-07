package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class LogWriter {

	File logFile = new File("log.txt");
	
	public void addToFile(String line) {
		try(PrintWriter logWrite = new PrintWriter(new FileOutputStream(logFile, true))){
			logWrite.println(line);
		} catch (FileNotFoundException e) {
			//
		}
	}

	public void logDeposit(BigDecimal depositAmt, BigDecimal balance) {
		String logDeposit ="FEED MONEY: "+  depositAmt + balance;
		addToFile(logDeposit);
	}
	public void logChange(BigDecimal change, BigDecimal balance) {
		String logChange = "GIVE CHANGE: " + change + balance;
		addToFile(logChange);
	}
	public void logTransaction(String name, String slot, BigDecimal price, BigDecimal balance) {
		String logTrans= name + slot + price + balance;
		addToFile(logTrans);
	}
}
