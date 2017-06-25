package e3;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements Observable {

	protected List<Observer> observers;
	protected Product product;
	protected double availableQuantity;
	protected double backrderedQuantity;
	
	public Inventory(Product product) {
		this.product = product;
	}
	
	protected void updateQuantities(double stock, double backord) {
		
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	public void notifyObserver() {
		
	}
	
	public String toString() {
		return "";
	}
}