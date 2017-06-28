package e3;

public class ProductionOrder implements Observer, DisplayElement {

	private int orderSequence;
	protected int ID;
	protected double minQuantity;
	protected Observable inventory;
	
	public ProductionOrder(int minQty, Observable inventory) {
		this.minQuantity = minQty;
		this.inventory = inventory;		
	}
	
	public void update(double availQty, double ordQty) {
		this.minQuantity = 1000.00;
		if (ordQty < this.minQuantity){
			/* "there is a minimum amount of wallpaper that can be 
			 * printed in one production run (1000 yards for most
			 * common types of commercial wallpaper)." - e3.pdf
			 */
			ordQty = 0.0;
		}
		// Please check if this is correct.
		this.minQuantity = ordQty;
	}
	
	public void display(double dispQty) {
		System.out.println("Quantity: " + dispQty);
	}
	
	public String toString() {
		return ("Minimum Quantity: " + this.minQuantity + ", " +  
				"Inventory: " + this.inventory);
	}
}
