import java.math.BigDecimal;

public class Beverage implements VendableItems {
	private String name;
	private BigDecimal price;
	private int quantity;

	public Beverage (String name, BigDecimal price, int quantity) {

		this.name = name;
		this.price = price;
		this.quantity = quantity;
				
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}
	@Override
	public String dispense() {
		String vendMsg= "Glug Glug Yum !!!!";
		quantity= getQuantity()-1;
		return vendMsg;
		
	}
	
}