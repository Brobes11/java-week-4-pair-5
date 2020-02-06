package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
	private Map<String, VendableItems> inventory = new LinkedHashMap<>();

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
		}
	}

	public String displayItems() {
		String result="";
		for (String s : inventory.keySet()) {
			VendableItems currentItem = inventory.get(s);
			result += (currentItem.slot()+" " +currentItem.getName()+" "
			+"$" +currentItem.getPrice()+ " "+ "\n " );
		
		}
		return result;
		
	}
}
