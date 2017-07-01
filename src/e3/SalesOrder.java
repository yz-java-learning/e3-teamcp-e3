package e3;

public class SalesOrder implements Observer, DisplayElement {

	private static int orderSequence; // Isn't this supposed to be static?
	protected int ID;
	protected Customer customer;
	protected double quantity;
	protected Observable inventory;
	
	public SalesOrder(Customer customer, double quantity, Observable inventory) {
		this.customer = customer;
		this.quantity = quantity;
		this.inventory = inventory;
		
		// Auto increment by one each time we make a SalesOrder
		ID = orderSequence++;
		
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
		System.out.println(this.toString());
	}
	
	public void display(double displayQuantity) {
		System.out.println("Shipping Order# " + "" + ID + " to " + "" + customer + " " + 
						   "Product: " + "" + ((Inventory)inventory).product + " " + 
						   "Quantity: " + "" + displayQuantity);
	}
	
	private boolean ship(double availableQuantity) {
		// immediately ships, if there exists a back order on a product
		Inventory stock = (Inventory) inventory;
		// Check if SalesOrder quantity is less than current inventory
		if (this.quantity <= availableQuantity){
			// Update inventory quantities
			// stock.backorderedQuantity -= this.quantity;
			stock.availableQuantity -= this.quantity;
			stock.updateQuantities(stock.availableQuantity,stock.backorderedQuantity);
			display(availableQuantity);
			return true;
		}
		 // Register as Observer as SalesOrder quantity is greater than current inventory
		 stock.registerObserver(this);
		 stock.backorderedQuantity += this.quantity;
		 return false;
	}
	
	public String toString() {
		return ("" + ID + "");
	}
}
