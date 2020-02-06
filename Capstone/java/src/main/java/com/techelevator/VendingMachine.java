package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
	private Map<String, VendableItems> inventory = new HashMap<>();

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
				if (inventoryUpload[3].equals("Chip")) {
					Chips c = new Chips(slot, name, price, quantity);
					inventory.put(inventoryUpload[0], c);
				}

			}
		} catch (FileNotFoundException e) {
		}
	}
	public String slotA1contents() {
		return inventory.get("A1").toString();
	}
}
