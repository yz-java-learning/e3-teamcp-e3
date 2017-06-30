package e3;

public class ProductionOrder implements Observer, DisplayElement {

	private int orderSequence; // Isn't this supposed to be static?
	protected int ID;
	protected double minQuantity;
	protected Observable inventory;
	
	public ProductionOrder(int minQty, Observable inventory) {
		this.minQuantity = minQty;
		this.inventory = inventory;		
	}
	
	public void update(double availQty, double ordQty) {
		if(ordQty >= this.minQuantity){
			Inventory inventory = (Inventory) this.inventory;
			inventory.updateQuantities(ordQty, 0);
		}
	}
	
	public void display(double dispQty) {
		System.out.println("Quantity: " + dispQty);
	}
	
	public String toString() {
		return ("Minimum Quantity: " + this.minQuantity + ", " +  
				"Inventory: " + this.inventory);
	}
}
