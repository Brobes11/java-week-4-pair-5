package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
	private Map<String, VendableItem> inventory = new LinkedHashMap<>();
	
	private CashBox cashBox = new CashBox();
	private LogWriter logWriter= new LogWriter();
	public VendingMachine() {
	
		File sourceFile = new File("VendingMachine.txt");
		BigDecimal price;
		String slot;
		String name;
		String type;
		int quantity = 5;

		try (Scanner layoutScanner = new Scanner(sourceFile)) {

			while (layoutScanner.hasNextLine()) {
				String currentLine = layoutScanner.nextLine();
				String[] inventoryUpload = currentLine.split("\\|");
				slot = inventoryUpload[0];
				name = inventoryUpload[1];
				price = new BigDecimal(inventoryUpload[2]);
				type = inventoryUpload[3];
				if (type.equals("Chip")) {
					Chips c = new Chips(slot, name, price, quantity);
					inventory.put(slot, c);
				} else if (type.equals("Gum")) {
					Gum g = new Gum(slot, name, price, quantity);
					inventory.put(slot, g);
				} else if (type.equals("Drink")) {
					Beverage b = new Beverage(slot, name, price, quantity);
					inventory.put(slot, b);
				} else if (type.equals("Candy")) {
					Candy c = new Candy(slot, name, price, quantity);
					inventory.put(slot, c);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}

	public String displayItems() {
	String result="";
		String soldOutNote = "";

		for (String s : inventory.keySet()) {
			VendableItem currentItem = inventory.get(s);
			int itemQuantity = currentItem.getQuantity();
			if (itemQuantity == 0) {
				soldOutNote = " *SOLD OUT*";
			} else {
				soldOutNote = "";
			}
			 result += String.format("%-10s%-25s$%-15.02f%-10s\n",
					 currentItem.slot(),currentItem.getName(),currentItem.getPrice(),soldOutNote);
			
			
		}
		System.out.println("\n'~'-.,__,.-'~'-.Menu,__,.-'~'-.,__,.-'~'");
		return result;
		
	}

	public String purchase(String userSelection) {
		if (cashBox.getBalance().compareTo(BigDecimal.ZERO) == 0) {
			return "Please deposit money before making a selection";

		}

		String result = "Item not available";
		if (inventory.containsKey(userSelection)) {
			VendableItem selectedItem = inventory.get(userSelection);
			if (selectedItem.getQuantity() > 0 && (cashBox.getBalance().compareTo(selectedItem.getPrice()) > 0)) {
				result = selectedItem.dispense();
				cashBox.makePurchase(selectedItem);
				LocalDateTime timeStamp = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
				String formatStamp = timeStamp.format(formatter);
				logWriter.logTransaction(formatStamp,selectedItem.getName(),selectedItem.slot(), selectedItem.getPrice(),cashBox.getBalance());
			}
		}
		return result;

	}

	public BigDecimal getBalance() {

		return cashBox.getBalance();

	}

	public void makeDeposit(BigDecimal depositAmt) {
		cashBox.makeDeposit(depositAmt);
		LocalDateTime timeStamp = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
		String formatStamp = timeStamp.format(formatter);
		logWriter.logDeposit(formatStamp,depositAmt,cashBox.getBalance());
	}

	public String getChange() {
		BigDecimal change = getBalance();
		String message = cashBox.getChange();	
		LocalDateTime timeStamp = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
		String formatStamp = timeStamp.format(formatter);
		logWriter.logChange(formatStamp, change,cashBox.getBalance());
		return message;
		
	}

	public void salesReport() {
		int quantity = 5;
		File salesReportFile = new File("salesReport.txt");
		try (PrintWriter salesWriter = new PrintWriter(salesReportFile)) {
			for (String sr : inventory.keySet()) {
				VendableItem currentItem = inventory.get(sr);
				salesWriter.println(currentItem.getName() + " | " + (quantity - currentItem.getQuantity()));
			}
			salesWriter.println("");
			salesWriter.println("**TOTAL SALES** $" + cashBox.getSpent());
		} catch (FileNotFoundException e) {
			
			//add error message
		}
	}
}
