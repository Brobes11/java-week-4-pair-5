package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
	private Map<String, VendableItem> inventory = new LinkedHashMap<>();
	private CashBox cashBox = new CashBox();

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
		String result = "";
		String soldOutNote = "";

		for (String s : inventory.keySet()) {
			VendableItem currentItem = inventory.get(s);
			int itemQuantity = currentItem.getQuantity();
			if (itemQuantity == 0) {
				soldOutNote = " *SOLD OUT*";
			}
			result += (currentItem.slot() + " " + currentItem.getName() + " " + "$" + currentItem.getPrice()
					+ soldOutNote + "\n ");
		}
		return result;
	}

	public String purchase(String userSelection) {
		String result = "Item not available";
		if (inventory.containsKey(userSelection)) {
			VendableItem selectedItem = inventory.get(userSelection);
			if (selectedItem.getQuantity() > 0 && (cashBox.getBalance().compareTo(selectedItem.getPrice()) > 0)) {
				result = selectedItem.dispense();
				cashBox.makePurchase(selectedItem);
			}
		}
		return result;

	}

	public BigDecimal getBalance() {

		return cashBox.getBalance();

	}

	public void makeDeposit(BigDecimal depositAmt) {
		cashBox.makeDeposit(depositAmt);
	}

}
