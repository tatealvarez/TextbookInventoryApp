package alvarez;

import java.io.Serializable;

public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public int SKU;
	String title;
	double price;
	int quantity;
	
	public Item(int SKU, String title, double price, int quantity) {
		this.SKU = SKU;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String toString() {
		return String.format("SKU: %d, Title: '%s', Price: $%.2f, Quantity: %d", SKU, title, price, quantity);
	}
}
