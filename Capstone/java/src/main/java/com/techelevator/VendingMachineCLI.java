package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_QUIT = "Exit";
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT,PURCHASE_MENU_OPTION_FINISH_TRANSACTION };
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_QUIT };

	private Menu menu;
	private VendingMachine vendingMachine;
	private CashBox cashBox;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		vendingMachine = new VendingMachine();
		}

	public void run() {
		boolean done = false;
		while (done ==false) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(vendingMachine.displayItems());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean subDone = false;
				while (subDone == false) {
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					System.out.println("Balance: " + cashBox.getBalance());
					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						//do deposit
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						//do selection
					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						subDone = true;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_QUIT)) {
				done = true;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
