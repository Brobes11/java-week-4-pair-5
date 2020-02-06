import java.math.BigDecimal;

public class Candy {
	private String name;
	private BigDecimal price;
	private int quantity;

	public Candy(String name, BigDecimal price, int quantity) {

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
	public String dispense() {
		String vendMsg= "Munch Munch Yum!!!!! ";
		quantity= getQuantity()-1;
		return vendMsg;
		
	}
	
}