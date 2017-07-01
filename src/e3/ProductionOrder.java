package e3;

public class ProductionOrder implements Observer, DisplayElement {

    private static int orderSequence;
    protected int ID;
    protected double minQuantity;
    protected Observable inventory;

    public ProductionOrder(int minQty, Observable inventory) {
        this.minQuantity = minQty;
        this.inventory = inventory;
        
        // Auto increment by one each time we make a ProductionOrder
        ID = orderSequence++;
        
        // Register the observer for the current inventory
        Inventory currentInventory = (Inventory) this.inventory;
        currentInventory.registerObserver(this);
    }

    /**
     * If the availableQuantity in our inventory is greater than or equal to
     * the quantity we ordered then we update the inventories quantities
     *
     * @param availQty Available quantity in our inventory
     * @param ordQty   Order quantity we make
     */
    public void update(double availQty, double ordQty) {
    	Inventory currentInventory = (Inventory) this.inventory;
    	
    	if (minQuantity > (ordQty - availQty)){
    		ordQty = 0;
    		availQty = 0;
    		currentInventory.updateQuantities(ordQty - availQty, 0.0);
    	}
        currentInventory.updateQuantities(ordQty - availQty, 0.0);
        display(ordQty - availQty);
        System.out.println(this.toString());
    }

    public void display(double dispQty) {
    	System.out.println("Production Order# " + "" + ID + " to " + "" + 
				   		   "item: " + "" + ((Inventory)inventory).product + " " + 
				   		   "Quantity: " + "" + dispQty);
    }

    public String toString() {
		Inventory inv = (Inventory) this.inventory;
		return ("[" + ID + " " + inv.product + " " + minQuantity + "]");
	}
}
