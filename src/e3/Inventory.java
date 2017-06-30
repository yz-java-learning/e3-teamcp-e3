package e3;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements Observable {

	protected List<Observer> observers;
	protected Product product;
	protected double availableQuantity;
	protected double backorderedQuantity;
	
	public Inventory(Product product) {
		this.product = product;
		observers = new ArrayList<Observer>();
	}
	
	protected void updateQuantities(double stock, double backord) {
		// Quantities have been updated so we notify all observers
		availableQuantity = stock;
		backorderedQuantity = backord;
		notifyObserver();
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	public void notifyObserver() {
		observers.forEach((observer) -> {
			// Check if observer is instance of SalesOrder
		if (observer.getClass().equals(SalesOrder.class)) {
			SalesOrder requestSales = (SalesOrder) observer;
			
			// Check if current inventory is sufficient to satisfy SalesOrder
			if (this.availableQuantity >= requestSales.quantity) {
				requestSales.update(availableQuantity, backorderedQuantity);
			 }
			// Check if observer is instance of ProductionOrder
		} else if (observer.getClass().equals(ProductionOrder.class)) {
			ProductionOrder requestProd = (ProductionOrder) observer;
			
			// Check if inventory backorder is larger than the minimum quantity
			if (this.backorderedQuantity > requestProd.minQuantity) {
				requestProd.update(availableQuantity, backorderedQuantity);
				 }
			}
		});
	}
	
	public String toString() {
		String stringOutput = "";
        for (Observer o : observers){
        	if (o.getClass().equals(ProductionOrder.class)){
        		stringOutput += (((product.ID + " " + product + " " + availableQuantity + " " + backorderedQuantity)));
        	}
        }
        return (stringOutput);
    }
}
