package e3;

public class SalesOrder implements Observer, DisplayElement {

	private int orderSequence;
	protected int ID;
	protected Customer customer;
	protected double quantity;
	protected Observable inventory;
	
	public SalesOrder(Customer customer, double quantity, Observable inventory) {
		this.customer = customer;
		this.quantity = quantity;
		this.inventory = inventory;
	}
	
	public void update(double availQty, double ordQty) {
		// Still need to do something with availQty
		((Inventory)inventory).availableQuantity = availQty;
		this.quantity = ordQty;
		display(ordQty);
		
	}
	
	public void display(double displayQuantity) {
		displayQuantity = this.quantity;
		System.out.println("Quantity: " + displayQuantity);
	}
	
	private boolean ship(double availableQuantity) {
		// immediately ships, if there exists a back order on a product
		return true;
	}
	
	public String toString() {
		return ("Customer ID: " + this.customer + ", " + 
				"Quantity: " + this.quantity + ", " + 
				"Inventory: " + this.inventory);
	}
}
