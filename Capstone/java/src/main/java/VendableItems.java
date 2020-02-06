import java.math.BigDecimal;

public interface VendableItems {
	public String getName();
	public BigDecimal getPrice();
	public int getQuantity();
	public String dispense();
	

}
