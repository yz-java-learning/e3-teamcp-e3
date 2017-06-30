package e3;

public class SalesOrder implements Observer, DisplayElement {

	private int orderSequence; // Isn't this supposed to be static?
	protected int ID;
	protected Customer customer;
	protected double quantity;
	protected Observable inventory;
	
	public SalesOrder(Customer customer, double quantity, Observable inventory) {
		this.customer = customer;
		this.quantity = quantity;
		this.inventory = inventory;
		
		// To ship and register each observer
		this.ship(((Inventory)inventory).availableQuantity);
	}
	
	public void update(double availQty, double ordQty) {
		// Still need to do something with availQty
		// ((Inventory)inventory).availableQuantity = availQty; 
		Inventory stock = (Inventory) inventory;
		ship(availQty);
		stock.removeObserver(this);
		display(ordQty);
	}
	
	public void display(double displayQuantity) {
		displayQuantity = this.quantity;
		System.out.println("Quantity: " + displayQuantity);
	}
	
	private boolean ship(double availableQuantity) {
		// immediately ships, if there exists a back order on a product
		Inventory stock = (Inventory) inventory;
		//Check if SalesOrder quantity is less than current inventory
		if (this.quantity <= availableQuantity){
			//Update inventory quantities
			stock.backorderedQuantity -= this.quantity;
			stock.availableQuantity -= this.quantity;
			stock.updateQuantities(stock.availableQuantity,stock.backorderedQuantity);
			System.out.println(this.toString());
			return true;
		}
		 //Register as Observer as SalesOrder quantity is greater than current inventory
		 stock.registerObserver(this);
		 stock.backorderedQuantity += this.quantity;
		 return false;
	}
	
	public String toString() {
		return ("Customer ID: " + this.customer + ", " + 
				"Quantity: " + this.quantity + ", " + 
				"Inventory: " + this.inventory);
	}
}
