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
		
	}
	
	public void display(double dispQty) {
		
	}
	
	public String toString() {
		return "";
	}
}
