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
		
	}
	
	public void display(double displayQuantity) {
		
	}
	
	private boolean ship(double availableQuantity) {
		return true;
	}
	
	public String toString() {
		return "";
	}
}
